package live.wellconnect.wellconnect.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.MyCustomButton
import live.wellconnect.wellconnect.components.MyCustomImage
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.ui.theme.TextColor


@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onSigIn: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(0.7f)
                .background(Color.White)
                .alpha(0.9f)
                .border(2.dp, TextColor, shape = RoundedCornerShape(15.dp))
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MyCustomImage(image = R.drawable.message_sender, height = 72, width = 72)
                    Space(size = 30)
                    MakeText(stringResource(id = R.string.message_send), 20, TextColor, TextAlign.Center)
                    Space(size = 20)
                    MakeText(
                        stringResource(id = R.string.message_subtitle),
                        14,
                        Color.DarkGray,
                        TextAlign.Center
                    )

                    Space(size = 50)
                    MyCustomButton(text = stringResource(id = R.string.sign_title), heigh = 30, colorText = Color.White, onSigIn = { onSigIn() })
                }
            }
        }
    }
}


@Preview
@Composable
fun CustomDialog_Preview() = CustomDialog(onDismiss = { }, onSigIn = {})



