package live.wellconnect.wellconnect.presentation.register_example

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel : RegisterViewModel) {

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) } // required validation
    var photo by remember { mutableStateOf(TextFieldValue()) } // required validation
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MakeText("Sign Up", 40)
        Space()
        MakeText("Name", 16)
        TextField(
            label = { Text(text = "Name") },
            value = name,
            onValueChange = { name = it },
        )
        Space()
        MakeText("Email Address", 16)
        TextField(
            label = { Text(text = "name@email.com") },
            value = email,
            onValueChange = { email = it },
        )
        Space()
        MakeText("Photo Location", 16)
        TextField(
            label = { Text(text = "photo") },
            value = photo,
            onValueChange = { photo = it },
        )
        Space()
        MakeText("Password", 16)
        TextField(
            label = { Text(text = "Create a password") },
            value = password,
            onValueChange = { password = it },
        )
        TextField(
            label = { Text(text = "Confirm password") },
            value = password,
            onValueChange = { password = it },
        )

        /// check for politics and conditions

        val user = UserRegister(name.text.toString(), email.text.toString(), password.text.toString()/*, photo.text.toString()*/)

        Button(
            onClick = {
                viewModel.registerUser(user)
                Log.i("PUSHED", user.toString())
            },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Registrar")
        }
    }
}

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreen_Preview(){
    RegisterScreen(viewModel = RegisterViewModel(null))
}*/

@Composable
fun MakeText(text : String, size : Int) = Text(
    text = text,
    style = TextStyle(
        fontSize = size.sp,
        fontFamily = FontFamily.Cursive
    )
)

@Composable
fun Space() = Spacer(
    modifier = Modifier
        .height(15.dp)
)