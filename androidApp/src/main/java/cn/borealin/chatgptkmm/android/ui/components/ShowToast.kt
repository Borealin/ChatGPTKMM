package cn.borealin.chatgptkmm.android.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ShowToast(toastMessage: MutableStateFlow<String?>) {
    val context = LocalContext.current
    val message by toastMessage.collectAsState(null)

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            toastMessage.value = null
        }
    }
}