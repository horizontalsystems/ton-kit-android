package com.tonapps.icu

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

data class Coins(
    val value: BigDecimal,
    val decimals: Int = DEFAULT_DECIMALS,
): Parcelable, Comparable<Coins> {

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Coins> {
            override fun createFromParcel(parcel: Parcel) = Coins(parcel)
            override fun newArray(size: Int): Array<Coins?> = arrayOfNulls(size)
        }

        const val DEFAULT_DECIMALS = 9

        val ZERO = of(BigDecimal.ZERO, DEFAULT_DECIMALS)

        val ONE = of(BigDecimal.ONE, DEFAULT_DECIMALS)

        fun of(
            value: String,
            decimals: Int = DEFAULT_DECIMALS
        ): Coins {
            val divisor = BigDecimal.TEN.pow(decimals)
            val bigDecimal = safeBigDecimal(prepareValue(value)).divide(divisor, decimals, RoundingMode.FLOOR)
            return of(bigDecimal.toDouble(), decimals)
        }

        fun of(
            value: BigDecimal,
            decimals: Int = DEFAULT_DECIMALS
        ): Coins {
            return Coins(value, decimals)
        }

        fun of(
            value: Long,
            decimals: Int = DEFAULT_DECIMALS
        ): Coins {
            val bigDecimal = safeBigDecimal(value).movePointLeft(decimals)
            return Coins(bigDecimal, decimals)
        }

        fun of(
            value: Double,
            decimals: Int = DEFAULT_DECIMALS,
        ): Coins {
            val bigDecimal = safeBigDecimal(value) //.movePointLeft(decimals)
            return Coins(bigDecimal, decimals)
        }

        private fun safeBigDecimal(
            value: Any
        ): BigDecimal {
            return try {
                when (value) {
                    is BigInteger -> value.toBigDecimal()
                    is String -> BigDecimal(value)
                    is Double -> value.toBigDecimal()
                    is Long -> value.toBigDecimal()
                    else -> BigDecimal.ZERO
                }
            } catch (e: Throwable) {
                BigDecimal.ZERO
            }
        }

        fun safeParseDouble(value: String): Double {
            return prepareValue(value).toDoubleOrNull() ?: 0.0
        }

        @Deprecated("Remove this dirty hack")
        fun prepareValue(value: String): String {
            var v = value.trim()
            if (v.endsWith(".") || v.startsWith(",")) {
                v = v.dropLast(1)
            }
            if (v.startsWith("0")) {
                v = v.dropWhile { it == '0' }
            }
            if (v.startsWith(".") || v.startsWith(",")) {
                v = "0$v"
            }
            if (v.contains(",")) {
                v = v.replace(",", ".")
            }
            if (v.isEmpty()) {
                v = "0"
            }
            return v
        }

        inline fun <T> Iterable<T>.sumOf(selector: (T) -> Coins): Coins {
            var sum: Coins = ZERO
            for (element in this) {
                sum += selector(element)
            }
            return sum
        }
    }

    val isZero: Boolean
        get() = value == ZERO.value

    val isPositive: Boolean
        get() = value > ZERO.value

    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as BigDecimal,
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(value)
        parcel.writeInt(decimals)
    }

    operator fun plus(other: Coins) = of(value + other.value, decimals)

    operator fun minus(other: Coins) = of(value - other.value, decimals)

    operator fun times(other: Coins) = of(value * other.value, decimals)

    operator fun div(other: Coins) = of(value / other.value, decimals)

    operator fun rem(other: Coins) = of(value.remainder(other.value), decimals)

    operator fun inc() = Coins(value + ONE.value, decimals)

    operator fun dec() = Coins(value - ONE.value, decimals)

    override operator fun compareTo(other: Coins) = value.compareTo(other.value)

    fun abs() = Coins(value.abs(), decimals)

    fun stripTrailingZeros(): Coins = Coins(value.stripTrailingZeros(), decimals)

    fun toLong(): Long {
        val multiplier = BigDecimal.TEN.pow(decimals)
        val multipliedValue = value.multiply(multiplier)
        return multipliedValue.toLong()
    }

    fun toDouble(): Double = value.toDouble()

    override fun describeContents(): Int {
        return 0
    }


}