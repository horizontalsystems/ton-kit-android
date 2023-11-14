package io.horizontalsystems.tonkit

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    val transactionList = uiState.transactionList

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
                Transactions(transactionList) {
                    viewModel.loadNextTransactionsPage()
                }
            }
        }
    }
}

@Composable
fun Transactions(transactionList: List<TonTransaction>, onBottomReach: () -> Unit) {
    if (transactionList.isEmpty()) {
        Text(text = "No transactions")
    }
    LazyColumn {
        itemsIndexed(transactionList) { i, it ->
            LaunchedEffect(transactionList) {
                if (i == transactionList.size - 1) {
                    onBottomReach.invoke()
                }
            }

            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                val date = DateFormat.format("yyyy-MM-dd hh:mm:ss a", it.timestamp * 1000)
                Text(text = "# $i")
                Text(text = "Hash: ${it.hash}")
                Text(text = "Type: ${it.type}")
                Text(text = "Value: ${it.value?.stripTrailingZeros()?.toPlainString()}")
                Text(text = "Date: $date")
                Text(text = "LT: ${it.lt}")
            }
            Divider()
        }
    }
}
