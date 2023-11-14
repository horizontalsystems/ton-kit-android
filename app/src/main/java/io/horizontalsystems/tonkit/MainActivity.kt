package io.horizontalsystems.tonkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.horizontalsystems.tonkit.ui.theme.TonKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TonKitTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel = viewModel<MainViewModel>()
    val uiState = viewModel.uiState
    val address = viewModel.address
    val transactionList = viewModel.transactionList

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Text(text = "Address: $address")
            Text(text = "Balance: ${uiState.balance}")
            Text(text = "Sync State: ${uiState.syncState.toStr()}")
            Text(text = "Tx Sync State: ${uiState.txSyncState.toStr()}")

            Spacer(modifier = Modifier.height(8.dp))

            transactionList?.let {
                Transactions(transactionList)
            }
        }
    }
}

@Composable
fun Transactions(transactionList: List<TonTransaction>) {
    if (transactionList.isEmpty()) {
        Text(text = "No transactions")
    }
    LazyColumn {
        items(transactionList) {
            Row {
                Text(text = "Hash: ${it.hash}")
            }
        }
    }
}
