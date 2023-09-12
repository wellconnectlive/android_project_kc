package live.wellconnect.wellconnect.presentation.QR

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import live.wellconnect.wellconnect.R

@Composable
fun QRScreen(uiID: String) {
    val qrData = "https://wellconnect.live/qr/?data=${uiID}"
    val encoder = BarcodeEncoder()
    val bitmap = encoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400)

    Spacer(modifier = Modifier
    .fillMaxWidth()
    .height(50.dp))

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .height(600.dp)
            .padding(horizontal = 20.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                    start = Offset(0f, 10f),
                    end = Offset(0f, 500f),
                ))) {

        Column( horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = uiID,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)

            AsyncImage(
                model = bitmap,
                contentDescription = qrData,
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QRScreen_Preview(){
    QRScreen("AAAEEE11111")
}