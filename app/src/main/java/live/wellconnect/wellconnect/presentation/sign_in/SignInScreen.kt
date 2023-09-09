
package live.wellconnect.wellconnect.presentation
import androidx.compose.foundation.Image
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.components.MakeText
import live.wellconnect.wellconnect.components.MakeTextField

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextAlign
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import live.wellconnect.wellconnect.components.MakeTextFieldPassword
import live.wellconnect.wellconnect.components.MyButton
import live.wellconnect.wellconnect.components.MyClickableTextLogin
import live.wellconnect.wellconnect.components.MyTextButton
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.ui.theme.TextColorDark


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onRegisterClick : () -> Unit,
    viewModel: SignInViewModel
) {

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
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
                //.wrapContentSize(align = Alignment.Center),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LogoName()
            }
            Space(10)
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .wrapContentSize(align = Alignment.CenterEnd),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                MakeText(text = stringResource(id = R.string.login_title), size = 24, color = TextColorDark, align = TextAlign.Start)
                Space(15)
                MakeTextField(stringResource(id = R.string.email_address), icon = null, onTextChange = { viewModel.onEvent(SignInStateOk.EmailTaking(it)) }, errorStatus = viewModel.signUIStates.value.emailError)
                MakeTextFieldPassword(stringResource(id = R.string.password), onTextChange = { viewModel.onEvent(SignInStateOk.PasswordTaking(it)) }, errorStatus = viewModel.signUIStates.value.passwordError)
                MyTextButton(onClick = {} , text = stringResource(id = R.string.forgot_password), align = TextAlign.Start)
                Space(size = 10)
                // todo -> event on forgot password?
                MyButton(onClicked = { viewModel.onEvent(SignInStateOk.ButtonClicked) }, isEnabled = viewModel.isValidOk.value, text = stringResource(id = R.string.login_button))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ){
                MyClickableTextLogin(onTextSelected = onRegisterClick)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreen_Preview() {
    SignInScreen(SignInState(true,null),
                    onSignInClick =  {},
                    onRegisterClick = {},
                    viewModel = SignInViewModel(),
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
        painter = painterResource(id = R.drawable.new_logo),
        contentDescription = "Nombre de la aplicación",
        modifier = Modifier
            .fillMaxWidth()
            .height(276.dp)
            .background(Color.Transparent),
        )
}
@Preview(showBackground = true)
@Composable
fun LogoName_Preview() {
    LogoName()
}


