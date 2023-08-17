
package live.wellconnect.wellconnect.presentation
import android.util.Log
import androidx.compose.foundation.Image
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.R

import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.text.font.FontWeight


@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onRegisterClick : () -> Unit,
) {

    var textValue by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .wrapContentSize(align = Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            var textValue by remember { mutableStateOf("") }
            Logo()
            LogoName()

            CustomTextFieldLogin(
                value = textValue,
                onValueChange = { textValue = it },
                hint = "DNI"
            )
            CustomTextFieldLogin(
                value = textValue,
                onValueChange = { textValue = it },
                hint = "Password"
            )
            Button(
                onClick = onSignInClick,
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.orange),
                    contentColor = Color.White
                ) //

            ) {
                Text(text = "Ingresar")
            }
            Spacer(modifier = Modifier.height(30.dp))

            TextButton(
                onClick = onRegisterClick,
            ){
                Text("¿No tienes una cuenta? ¡Regístrate!")
            }
            Text("Recuperar contraseña",  // button que dispara la acción rcuperar contaseña
            fontWeight = FontWeight.Bold)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreen_Preview() {
    SignInScreen(SignInState(true,null),
                    onSignInClick =  {},
                    onRegisterClick = {},
    )
}
@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logonuevo),
        contentDescription = "Logo de la aplicación",
        modifier = Modifier
            .size(138.dp)
            .background(Color.Transparent)
    )
}
@Preview(showBackground = true)
@Composable
fun Logo_Preview() {
    Logo()
}
@Composable
fun LogoName() {
    Image(
        painter = painterResource(id = R.drawable.logonamenuevo),
        contentDescription = "Nombre de la aplicación",
        modifier = Modifier
            .height(40.26.dp)
            .width(359.dp)
            .background(Color.Transparent),
        )
}
@Preview(showBackground = true)
@Composable
fun LogoName_Preview() {
    LogoName()
}
@Composable
fun CustomTextFieldLogin(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }

    BasicTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            isHintVisible = it.isEmpty()
        },
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        singleLine = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onDone = { /* Acción al presionar "Done" en el teclado */ }),
        modifier = modifier
            .height(80.dp)
            .width(300.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            ) // Aplica una sombra de 4dp alrededor del TextField
            .background(Color.White, shape = MaterialTheme.shapes.medium) // Establece el fondo blanco y las esquinas redondeadas

    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            if (isHintVisible) {
                Text(
                    text = hint,
                    style = LocalTextStyle.current.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterStart)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CustomTextFieldLogin_Preview() {
    CustomTextFieldLogin("",{}, "Introduce tus datos")
}
