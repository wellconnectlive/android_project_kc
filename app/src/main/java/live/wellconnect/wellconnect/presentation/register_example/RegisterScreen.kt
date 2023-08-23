package live.wellconnect.wellconnect.presentation.register_example

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel : RegisterViewModel) {

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) } // required validation

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            MakeText("Sign up", 25)
            Space()
            MakeText("Name", 14)
            TextField(
                label = { Text(text = "Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.8F)
                    .background(Color.White),
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                trailingIcon = {
                    Image(imageVector = Icons.Outlined.Edit, contentDescription = "Pencil Edit")
                },
            )
            Space()
            MakeText("Email Address", 14)
            TextField(
                label = { Text(text = "name@email.com") },
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Space()
            MakeText("Password", 14)
            TextField(
                label = { Text(text = "Create a password") },
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier.alpha(1F).background(Color.White).fillMaxWidth(),
                trailingIcon = {
                    Image(imageVector = Icons.Outlined.Lock, contentDescription = "Password")
                },
            )
            Space()
            TextField(
                label = { Text(text = "Confirm password") },
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier.alpha(1F).background(Color.White).fillMaxWidth(),
                trailingIcon = {
                    Image(imageVector = Icons.Outlined.Lock, contentDescription = "Password")
                },
            )
            Space()
            /// check for politics and conditions

            val user = UserRegister(name.text.toString(), email.text.toString(), password.text.toString()/*, photo.text.toString()*/)

            Button(
                onClick = {
                    viewModel.registerUser(user)
                    Log.i("PUSHED", user.toString())
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Sign Up",
                    color = Color.Black
                )
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreen_Preview(){
    RegisterScreen(viewModel = RegisterViewModel(repository = DataRepositoryImpl()))
}
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
fun Space() = Spacer(
    modifier = Modifier
        .height(20.dp)
)