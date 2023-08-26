package live.wellconnect.wellconnect.presentation.register_example

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.ui.theme.TextColor


@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onSigIn : () -> Unit,
){
    Dialog(onDismissRequest = {  onDismiss() /*onDismiss(viewModel)*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            //elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .background(Color.White)
                .alpha(0.9f)
                .fillMaxWidth(0.9f)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MakeText("Message sended to your email", 20, TextColor, TextAlign.Start)
                Space(size = 20)
                MakeText("Please Verify your email address to log in and get started", 14, TextColor, TextAlign.Start)
                Space(size = 50)
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                    ,
                    onClick = { onSigIn() },
                    shape = RoundedCornerShape(50.dp)
                )
                {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}



