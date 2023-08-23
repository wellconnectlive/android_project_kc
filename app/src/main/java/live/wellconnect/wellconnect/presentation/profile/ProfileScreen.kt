package live.wellconnect.wellconnect.presentation.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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

        //if(userData?.profilePictureUrl != null) {
        /*if(userData?.userId != null) {
            AsyncImage(
                model = userData.email,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }*/
            if (userName != null) {
                Text(
                    text = userName.name,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

        // DELETE
                if (userName != null) {
                    Text(
                        text = userName.email,
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRegContinue) {
            Text(text = "Continue")
        }
    }


}