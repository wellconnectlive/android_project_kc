package live.wellconnect.wellconnect.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.presentation.SignInState

@Composable
fun WelcomeThird(
    welcomeThird : () -> Unit
) {

    val context = LocalContext.current
    /*LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colorResource(id = R.color.blueBackground), Color.White),
                    start = Offset(0f, 300f),
                    end = Offset(0f, 1650f), // Puedes ajustar el tamaño del degradado aquí

                )

            )
    ) {
        PhotoThird()
        Box(
            modifier = Modifier
                .fillMaxSize() // El Box anidado ocupará todo el espacio del Box principal
                .padding(16.dp), // Ajusta el espaciado si es necesario
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .wrapContentSize(align = Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                LogoIcon()
                LogoText()
            }

        }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .wrapContentSize(align = Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 64.dp) // Ajusta el valor según el margen deseado
            ) {

                PaginationDots(itemCount = 3, currentItem = 2)

                Text(
                    text = "Todas tus citas, a mano",
                    style = TextStyle(
                        color = colorResource(id = R.color.blueFontText),
                        fontSize = 30.sp, // Cambia el tamaño del texto aquí
                        fontWeight = FontWeight.Black // Puedes ajustar el peso de la fuente si es necesario
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Te ayudamos a gestionar todo tu historial médico y a estar a salvo en caso de crisis",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = welcomeThird,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blueFontText),
                    contentColor = Color.White
                ) //

            ) {
                Text(text = "Me apunto")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun WelcomeThird_Preview() {
    WelcomeThird(welcomeThird = {})

}

@Composable
fun PhotoThird() {
    Image(
        painter = painterResource(id = R.drawable.welcomepicturethird),
        contentDescription = "Primera foto del bienvenida",
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp),
        contentScale = ContentScale.Crop,

    )
}
@Preview(showBackground = true)
@Composable
fun PhotoThird_Preview() {
    PhotoThird()
}