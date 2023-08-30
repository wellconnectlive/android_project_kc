package live.wellconnect.wellconnect.presentation.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.MakeTextField
import live.wellconnect.wellconnect.components.MakeTextFieldPassword
import live.wellconnect.wellconnect.components.MyButton
import live.wellconnect.wellconnect.components.MyCheckBox
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.navigation.AppRouter
import live.wellconnect.wellconnect.navigation.Screens
import live.wellconnect.wellconnect.ui.theme.TextColor


@Composable
fun RegisterScreen(
    viewModel : RegisterViewModel,
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") } // required validation
    var check : Boolean = false
    var value : String = ""
    val termText = " Terms and Conditions"

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            MakeText("Sign up", 16, TextColor, TextAlign.Start)
            Space(10)
            MakeText("Create an account to get started", 12, Color.Black, TextAlign.Start)
            Space(20)
            MakeText("Name", 12, TextColor, TextAlign.Start)
            MakeTextField(labelValue = "Name", Icons.Outlined.Edit, onTextChange = { viewModel.onEvent(RegisterStates.NameTaking(it)) }, errorStatus = viewModel.registerUIStates.value.nameError)

            Space(15)
            MakeText("Email Address", 12, TextColor, TextAlign.Start)

            MakeTextField(labelValue = "name@email.com", icon = null, onTextChange = { viewModel.onEvent(RegisterStates.EmailTaking(it)) }, errorStatus = viewModel.registerUIStates.value.emailError)

            Space(15)
            MakeText("Password", 12, TextColor, TextAlign.Start)

            MakeTextFieldPassword(labelValue = "Create a password", icon = Icons.Outlined.Password, onTextChange = { viewModel.onEvent(RegisterStates.PasswordTaking(it)) }, errorStatus = viewModel.registerUIStates.value.passwordError)
            Space(15)
            // todo -> still await for the same password validator
            MakeTextFieldPassword(labelValue = "Confirm password", icon = Icons.Outlined.Password, onTextChange = { viewModel.onEvent(RegisterStates.PasswordTaking(it)) }, errorStatus = viewModel.registerUIStates.value.passwordError)

            Space(15)

            MyCheckBox(text = "I've read and agree with the Terms and Conditions and the Privacy Policy.",
                    onCheckedChange = {
                        viewModel.onEvent(RegisterStates.TermsAndPolicyTaking(it))
                    },
                    onTextSelected = {
                        Log.i("TERMS", "TERMS")
                        value = it
                    }
                )

            // todo -> delete after proof
            if (value == termText) {
                Log.i("TERMS", "TERMS")
                TermsAndConditionsScreen(onDismiss = {}, onAccept = {})
            }

            // todo :  check for politics and conditions
            Space(20)

            MyButton(onClicked = { viewModel.onEvent(RegisterStates.ButtonClicked) }, isEnabled = viewModel.isValidOK.value)

            if (viewModel.isRegisterShow) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreen_Preview() {
    RegisterScreen(viewModel = RegisterViewModel(repository = DataRepositoryImpl()))
}


