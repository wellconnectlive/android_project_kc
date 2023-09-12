package live.wellconnect.wellconnect.presentation.sign_in

import android.graphics.drawable.Icon
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.presentation.SignInState

@Composable
fun OnBoardingSecond(
    //state: SignInState,
    onBordingCircle : () -> Unit
) {

    var textValue by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
   /* LaunchedEffect(key1 = state.signInError) {
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
            .padding(bottom = 16.dp)
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
                .padding(start = 16.dp, top= 64.dp) // Ajusta el valor según el margen deseado
        ) {
            ProgressScreenSecond(progress = 0.45f)
            Text(text= "Cuéntanos un poco sobre ti",
                style = TextStyle(
                    color = colorResource(id = R.color.blueFontText),
                    fontSize = 24.sp, // Cambia el tamaño del texto aquí
                    fontWeight = FontWeight.Bold // Puedes ajustar el peso de la fuente si es necesario
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Selecciona la opción que más se ajuste a ti",
                style = TextStyle(
                    color = colorResource(id = R.color.blueFontText),
                    fontSize = 16.sp,
                ))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top= 160.dp)
                .wrapContentSize(align = Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            var tValDesease by remember { mutableStateOf("") }

            EditTextField(
                value = tValDesease,
                onValueChange = { tValDesease = it },
                hint = "Enfermedad / Alergia"
            )
            Spacer(modifier = Modifier.height(30.dp))

        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onBordingCircle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blueFontText),
                contentColor = Color.White
            ) //

        ) {
            Text(text = "Siguiente")
        }
    }

}
@Preview(showBackground = true)
@Composable
fun OnBoardingSecond_Preview() {
    OnBoardingSecond(onBordingCircle = {})
}
@Composable
fun EditTextFieldSecond(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
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
            .height(50.dp)
            .fillMaxWidth()
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            ) // Aplica una sombra de 4dp alrededor del TextField
            .background(Color.White, shape = MaterialTheme.shapes.medium) // Establece el fondo blanco y las esquinas redondeadas

    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            if (isHintVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = hint,
                        style = LocalTextStyle.current.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 12.dp),
                        tint = colorResource(id = R.color.blueFontText)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EditTextFieldSecond_Preview() {
    EditTextFieldSecond("",{}, "Introduce tus datos")
}
@Composable
fun DropDownTextFieldSecond(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
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
            .height(50.dp)
            .fillMaxWidth()
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            ) // Aplica una sombra de 4dp alrededor del TextField
            .background(Color.White, shape = MaterialTheme.shapes.medium) // Establece el fondo blanco y las esquinas redondeadas

    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            if (isHintVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = hint,
                        style = LocalTextStyle.current.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.caretdown),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 12.dp),
                        tint = colorResource(id = R.color.blueFontText),
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DropDownTextFieldSecond_Preview() {
    DropDownTextFieldSecond("",{}, "Introduce tus datos")
}
@Composable
fun ProgressScreenSecond(progress: Float) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(),
            color = colorResource(id = R.color.blueFontText)

        )
    }
}

@Preview
@Composable
fun ProgressScreenSecond_Preview() {
    ProgressScreenSecond(progress = 0.7f) // Cambia el valor de "progress" según tu necesidad (de 0.0f a 1.0f)
}
