package live.wellconnect.wellconnect.presentation.register

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.MakeTextField
import live.wellconnect.wellconnect.components.MakeTextFieldPassword
import live.wellconnect.wellconnect.components.MyButton
import live.wellconnect.wellconnect.components.MyCheckBox
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.ui.theme.TextColor
import live.wellconnect.wellconnect.ui.theme.TextColorDark


@Composable
fun RegisterScreen(
    viewModel : RegisterViewModel,
) {
    val termText = " Terms and Conditions"
    val privacyText = " Privacy Policy."
    val context = LocalContext.current

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
            MakeText(stringResource(id = R.string.sign_title), 16, TextColorDark, TextAlign.Start)
            Space(10)
            MakeText(stringResource(id = R.string.sign_sub_title), 12, Color.Black, TextAlign.Start)
            Space(20)
            MakeText(stringResource(id = R.string.name), 12, TextColorDark, TextAlign.Start)
            MakeTextField(stringResource(id = R.string.name), Icons.Outlined.Edit, onTextChange = { viewModel.onEvent(RegisterStates.NameTaking(it)) }, errorStatus = viewModel.registerUIStates.value.nameError)
            //getAmessageToUser(context, viewModel.registerUIStates.value.nameError, stringResource(id = R.string.name_error))

            
            Space(15)
            MakeText(stringResource(id = R.string.email), 12, TextColorDark, TextAlign.Start)
            MakeTextField(stringResource(id = R.string.email_example), icon = null, onTextChange = { viewModel.onEvent(RegisterStates.EmailTaking(it)) }, errorStatus = viewModel.registerUIStates.value.emailError)
            //getAmessageToUser(context, viewModel.registerUIStates.value.emailError, stringResource(id = R.string.email_error))

            Space(15)
            MakeText(stringResource(id = R.string.password), 12, TextColorDark, TextAlign.Start)
            MakeTextFieldPassword(stringResource(id = R.string.password_label),  onTextChange = { viewModel.onEvent(RegisterStates.PasswordTaking(it)) }, errorStatus = viewModel.registerUIStates.value.passwordError)
            //getAmessageToUser(context, viewModel.registerUIStates.value.passwordError, stringResource(id = R.string.password_error))

            Space(15)
            MakeTextFieldPassword(stringResource(id = R.string.confirm_password), onTextChange = { viewModel.onEvent(RegisterStates.RepasswordTaking(it)) }, errorStatus = viewModel.registerUIStates.value.passwordError)
            //getAmessageToUser(context, viewModel.registerUIStates.value.repasswordError, stringResource(id = R.string.confirm_password_error))
            
            Space(15)
            MyCheckBox(text = stringResource(id = R.string.checkbox_text),
                    onCheckedChange = {
                        viewModel.onEvent(RegisterStates.TermsAndPolicyTaking(it))
                    },
                    onTextSelected = {
                        when(it){
                            termText -> { viewModel.isTermsShow = true }
                            privacyText -> { viewModel.isPolicyShow = true }
                        }
                    }
                )
            //getAmessageToUser(context, viewModel.registerUIStates.value.termsAndPolicyError, stringResource(id = R.string.terms_and_conditions_error))

            when(true){
                viewModel.isTermsShow -> {
                    TermsAndConditionsScreen(
                        onDismiss = { },
                        onAccept = { viewModel.isTermsShow = false },
                    )
                }
                viewModel.isPolicyShow -> {
                    PrivacyPolicyScreen(
                        onDismiss = { },
                        onAccept = { viewModel.isPolicyShow = false },
                    )
                }
                viewModel.isRegisterShow -> {
                    CustomDialog(
                        onDismiss = {
                            viewModel.isRegisterShow
                        },
                        onSigIn = {
                            viewModel.signOut()
                        }
                    )
                }
                else -> {}
            }

            Space(20)
            MyButton(onClicked = { viewModel.onEvent(RegisterStates.ButtonClicked) }, isEnabled = viewModel.isValidOK.value, text = stringResource(id = R.string.sign_title))

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreen_Preview() {
    RegisterScreen(viewModel = RegisterViewModel(repository = DataRepositoryImpl()))
}

private fun getAmessageToUser(context : Context, stateError : Boolean, message : String){

    if(!stateError){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}


