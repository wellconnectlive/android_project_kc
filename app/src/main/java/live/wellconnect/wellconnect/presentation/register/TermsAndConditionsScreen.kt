package live.wellconnect.wellconnect.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
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
import live.wellconnect.wellconnect.components.MyCustomImageIcon
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.ui.theme.TextColor

@Composable
fun TermsAndConditionsScreen(
    onDismiss: () -> Unit,
    onAccept: () -> Unit,
) = Dialog(
    onDismissRequest = { onDismiss() },
    properties = DialogProperties(
        usePlatformDefaultWidth = false
        )
    ) {
    OutlinedCard(
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
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyCustomImageIcon(image = Icons.Outlined.PendingActions , height = 96, width = 96)
                Space(size = 30)
                MakeText(text = stringResource(id = R.string.terms_and_conditions_header), size = 24, color = TextColor, align = TextAlign.Center)
                Space(size = 30)
                MakeText(text = stringResource(id = R.string.terms_and_conditions), size = 14, color = Color.DarkGray, align = TextAlign.Justify)
                Space(size = 50)
                MyCustomButton(text = "Accept", 30, colorText = Color.White, onSigIn = { onAccept() })
            }
        }
    }
}

@Preview
@Composable()
fun TermsAndConditionsScreen_Preview() {
    TermsAndConditionsScreen(onDismiss = {}, onAccept = {  })
}