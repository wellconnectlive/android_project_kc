package live.wellconnect.wellconnect.presentation.register_example

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.MakeTextField
import live.wellconnect.wellconnect.components.MakeTextFieldPassword
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.ui.theme.TextColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel : RegisterViewModel,
) {

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) } // required validation

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                MakeText("Sign up", 16, TextColor, TextAlign.Start)
                Space(10)
                MakeText("Create an account to get started", 12, Color.Black, TextAlign.Start)
                Space(30)
                MakeText("Name", 12, TextColor, TextAlign.Start)
                Space(10)
                MakeTextField(labelValue = "Name", Icons.Outlined.Edit)

                Space(20)
                MakeText("Email Address", 12, TextColor, TextAlign.Start)
                Space(10)
                MakeTextField(labelValue = "name@email.com", icon = null)

                Space(20)
                MakeText("Password", 12, TextColor, TextAlign.Start)
                Space(10)

                MakeTextFieldPassword(labelValue = "Create a password", icon = Icons.Outlined.Lock)
                Space(20)
                MakeTextFieldPassword(labelValue = "Confirm password", icon = Icons.Outlined.Lock)

                Space(20)
                // todo :  check for politics and conditions

                val user = UserRegister(name.text.toString(), email.text.toString(), password.text.toString()/*, photo.text.toString()*/)

                Button(
                    onClick = {
                        viewModel.registerUser(user)
                        Log.i("PUSHED", user.toString())
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                    ) {
                    Text(
                        text = "Sign Up",
                        color = Color.Black
                    )
                }

                if (viewModel.isRegisterShow){
                    CustomDialog(
                        onDismiss = {
                            viewModel.isRegisterShow
                        },
                        onSigIn = {
                            viewModel.signOut()
                        }
                    )
                }
            }
        }

    }




}


/*@Composable
fun MakeTextField(
    label : String,
    image : Icons,
) {
    val (text, setText) = mutableStateOf("")
    TextField(
        trailingIcon = image,
        value = text,
        onValueChange = setText,
        label = label,
        modifier = Modifier.fillMaxWidth()
    )
}*/

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreen_Preview(){
    RegisterScreen(viewModel = RegisterViewModel(repository = DataRepositoryImpl()))
}


