package com.tonapps.security

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.security.crypto.EncryptedSharedPreferences
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object Security {

    fun pref(context: Context, keyAlias: String, name: String): SharedPreferences {
        KeyHelper.createIfNotExists(keyAlias)

        return EncryptedSharedPreferences.create(
            name,
            keyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun generatePrivateKey(keySize: Int): SecretKey {
        return try {
            val generator = KeyGenerator.getInstance("AES")
            val random = secureRandom()
            generator.init(keySize * 8, random)
            generator.generateKey()
        } catch (e: Throwable) {
            SecretKeySpec(randomBytes(keySize), "AES")
        }
    }

    fun calcVerification(input: ByteArray, size: Int): ByteArray {
        val messageDigest = MessageDigest.getInstance("SHA-1")
        messageDigest.update(input)
        val digest = messageDigest.digest()
        val verification = ByteArray(size)
        digest.copyInto(verification, 0, 0, size)
        digest.clear()
        return verification
    }

    fun argon2Hash(password: CharArray, salt: ByteArray): ByteArray? {
        return Sodium.argon2IdHash(password, salt, 32)
    }

    fun randomBytes(size: Int): ByteArray {
        val bytes = ByteArray(size)
        secureRandom().nextBytes(bytes)
        return bytes
    }

    fun secureRandom(): SecureRandom {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SecureRandom.getInstanceStrong()
        } else {
            SecureRandom()
        }
    }

    fun isAdbEnabled(context: Context): Boolean {
        return isAdbEnabled1(context) || isAdbEnabled2(context)
    }

    private fun isAdbEnabled1(context: Context): Boolean {
        return Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.ADB_ENABLED, 0
        ) != 0
    }

    private fun isAdbEnabled2(context: Context): Boolean {
        return Settings.Secure.getInt(
            context.contentResolver,
            "adb_port", 0
        ) != 0
    }

    fun isDevelopmentEnabled(context: Context): Boolean {
        return try {
            Settings.Global.getInt(
                context.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
            ) > 0
        } catch (e: Throwable) {
            false
        }
    }

    fun isSupportStrongBox(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.packageManager.hasSystemFeature(PackageManager.FEATURE_STRONGBOX_KEYSTORE)
        } else {
            false
        }
    }

    fun isDeviceRooted(): Boolean {
        return RootUtils.isDeviceRooted()
    }

    fun isDebuggable(context: Context): Boolean {
        return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
}