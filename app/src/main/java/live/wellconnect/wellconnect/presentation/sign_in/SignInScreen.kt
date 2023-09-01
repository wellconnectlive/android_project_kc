
package live.wellconnect.wellconnect.presentation
import androidx.compose.foundation.Image
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import live.wellconnect.wellconnect.components.MakeTextFieldPassword
import live.wellconnect.wellconnect.components.MyButton
import live.wellconnect.wellconnect.components.MyTextButton
import live.wellconnect.wellconnect.components.Space
import live.wellconnect.wellconnect.ui.theme.TextColor


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
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LogoName()// todo --< out of colum for max width
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .wrapContentSize(align = Alignment.CenterEnd),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start,
            ) {
            Space(250)
            MakeText(text = stringResource(id = R.string.login_title), size = 24, color = TextColor, align = TextAlign.Start)
            Space(15)
            MakeTextField(stringResource(id = R.string.email_address), icon = null, onTextChange = { viewModel.onEvent(SignInStateOk.EmailTaking(it)) }, errorStatus = viewModel.signUIStates.value.emailError)
            MakeTextFieldPassword(stringResource(id = R.string.password), icon = Icons.Outlined.Password, onTextChange = { viewModel.onEvent(SignInStateOk.PasswordTaking(it)) }, errorStatus = viewModel.signUIStates.value.passwordError)
            MyTextButton(onClick = {} , text = stringResource(id = R.string.forgot_password), align = TextAlign.Start)

            MyButton(onClicked = { viewModel.onEvent(SignInStateOk.ButtonClicked) }, isEnabled = viewModel.isValidOk.value, text = stringResource(id = R.string.login_button))

            //Space(30)
            MyTextButton(onClick = onRegisterClick , text = stringResource(id = R.string.register), align = TextAlign.Center)

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
            .height(300.dp)
            .background(Color.Transparent),
        )
}
@Preview(showBackground = true)
@Composable
fun LogoName_Preview() {
    LogoName()
}


