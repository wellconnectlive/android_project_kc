package live.wellconnect.wellconnect.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery5Bar
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.presentation.UserData

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    profileScreenViewModel: ProfileScreenViewModel,
    onRegContinue : () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uiID =  userData?.userId?: ""
        profileScreenViewModel.getUserValues(uiID)
        val userName = profileScreenViewModel.dataUser.value
        
        if (userName != null) {
            Text(
                text = userName.name,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (userName != null) {
            Text(
                text = userName.email,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.lucas),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp),
            contentScale = ContentScale.Crop
        )

        val qrData = "${profileScreenViewModel.datoURL}${uiID}"
        val encoder = BarcodeEncoder()
        val bitmap = encoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400)

        Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {
            AsyncImage(model = bitmap,
                contentDescription = qrData,
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp),
                contentScale = ContentScale.Crop
            )
        }

        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRegContinue) {
            Text(text = "Continue")
        }
    }
}


