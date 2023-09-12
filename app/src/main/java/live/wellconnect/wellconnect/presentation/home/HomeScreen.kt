package live.wellconnect.wellconnect.presentation.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.presentation.UserData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userData: UserData?,
               onQRShow : () -> Unit,
               onMapClick : () -> Unit) {
    val uiID =  userData?.userId?: ""
    val qrData = "https://wellconnect.live/qr/?data=${uiID}"
    val encoder = BarcodeEncoder()
    val bitmap = encoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400)

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                start = Offset(0f, 300f),
                end = Offset(0f, 1650f),
            )
        )
        , topBar = {
            HomeTopBar(onMenuButtonClick = {

        },
            onActionButtonClick = {
            })
    }, bottomBar = {
        HomeBottomAppBar()
    }, floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Sos, contentDescription = "SOS", tint = Color.Red)
            }
        }) {
            padding ->
        ScaffoldContent(
            padding = padding,
            onButtonClick = { onQRShow() },
            onMapClick = { onMapClick() },
            bitmap,
            qrData
        )
    }
}

@Composable
fun ScaffoldContent(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    onMapClick: () -> Unit,
    bitmap: Bitmap,
    qrData: String
) {
    val isOn = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(top = 70.dp, bottom = padding.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            IconButton(onClick = {  }
                       , modifier = Modifier.width(150.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn, contentDescription = "",
                        tint = colorResource(id = R.color.blueFontText)
                    )
                    Text(text = "Mi Ubicación")
                }
            }

            IconButton(onClick = { }
                        , modifier = Modifier.width(150.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Phone, contentDescription = "",
                        tint = colorResource(id = R.color.blueFontText)
                    )
                    Text(text = "Emergencia")
                }
            }

            IconButton(onClick = { }
                        , modifier = Modifier.width(150.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Doorbell, contentDescription = "",
                        tint = colorResource(id = R.color.blueFontText)
                    )
                    Text(text = "Avisar")
                }
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                        start = Offset(0f, 10f),
                        end = Offset(0f, 200f),
                    )
                )
                .padding(horizontal = 40.dp)) {
                Text(text = "Seguimiento en vivo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = colorResource(id = R.color.blueFontText))
                Switch(
                    checked = isOn.value,
                    onCheckedChange = { newState -> isOn.value = newState }
                )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                        start = Offset(0f, 10f),
                        end = Offset(0f, 500f),
                    )
                )) {

            AsyncImage(model = bitmap,
                contentDescription = qrData,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(10.dp)
                    .clickable(onClick = { onButtonClick() }),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.lucas),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                        start = Offset(0f, 10f),
                        end = Offset(0f, 500f),
                    ))) {

            Image(
                painter = painterResource(id = R.drawable.mapa),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(10.dp)
                    .clickable { onMapClick() },
                contentScale = ContentScale.Crop
            )

            Text(text = "Ruta en bici mañana",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
                ) {

            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(400.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
               onMenuButtonClick: () -> Unit,
               onActionButtonClick: (String) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onMenuButtonClick) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable", tint = colorResource(id = R.color.blueFontText))
            }
        },
        title = { Text(text = "WellConnect",
                   modifier = Modifier.fillMaxWidth(),
                   style = MaterialTheme.typography.headlineLarge,
                   color = colorResource(id = R.color.blueFontText)
        ) },
        actions = {
            IconButton(onClick = { onActionButtonClick("Profile") }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile", tint = colorResource(id = R.color.blueFontText))
            }
        }
    )
}

@Composable
private fun HomeBottomAppBar() {
    BottomAppBar {
        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = colorResource(id = R.color.blueFontText)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    tint = colorResource(id = R.color.blueFontText)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Map,
                    contentDescription = "Localización",
                    tint = colorResource(id = R.color.blueFontText)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Cámara",
                    tint = colorResource(id = R.color.blueFontText)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreen_Preview() {
    MaterialTheme {
        HomeScreen(UserData("11111","correo@correo.com","Usurious"), onQRShow = {}, onMapClick = {})
    }
}