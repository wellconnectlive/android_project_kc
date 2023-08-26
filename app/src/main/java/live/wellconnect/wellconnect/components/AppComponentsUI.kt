package live.wellconnect.wellconnect.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MakeText(text : String, size : Int) = Text(
    text = text,
    style = TextStyle(
        fontSize = size.sp,
        fontFamily = FontFamily.Serif,
        color = Color.DarkGray
    )
)

@Composable
fun Space(size : Int) = Spacer(
    modifier = Modifier
        .height(size.dp)
)