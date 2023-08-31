package live.wellconnect.wellconnect.components

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.ui.theme.TextColor

@Composable
fun MakeText(text : String, size : Int, color : Color, align : TextAlign) = Text(
    text = text,
    style = TextStyle(
        fontSize = size.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    color = color,
    textAlign = align
)

@Composable
fun Space(size : Int) = Spacer(
    modifier = Modifier
        .height(size.dp)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeTextField(
    labelValue: String,
    icon: ImageVector?,
    onTextChange : (String) -> Unit,
    errorStatus : Boolean = false,
) {
    
    val textValue = remember { mutableStateOf("") }
    
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = TextColor,
            focusedLabelColor = TextColor,
            cursorColor = TextColor,
            placeholderColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChange(it)
            },
        singleLine = true,
        maxLines = 1,
        isError = !errorStatus,
        trailingIcon = {
            if (icon != null) {
                Image(imageVector = icon, contentDescription = "Pencil Edit")
            }
        },
        shape = RoundedCornerShape(10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeTextFieldPassword(
    labelValue: String,
    icon: ImageVector?,
    onTextChange : (String) -> Unit,
    errorStatus : Boolean = false,
) {

    val passwordValue = remember { mutableStateOf("") }

    val passwordIsVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = TextColor,
            focusedLabelColor = TextColor,
            cursorColor = TextColor,
            placeholderColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it
            onTextChange(it)
            },
        singleLine = true,
        maxLines = 1,
        isError = !errorStatus,
        trailingIcon = {
            if (icon != null) {
                val iconImage = if(passwordIsVisible.value){
                    Icons.Outlined.Visibility
                }else {
                    Icons.Outlined.VisibilityOff
                }
                IconButton(onClick = { passwordIsVisible.value = !passwordIsVisible.value }) {
                    Image(imageVector = icon, contentDescription = "Pencil Edit")
                }
            }
        },
        visualTransformation = if(passwordIsVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(10.dp),
    )

}

@Composable
fun MyCheckBox(
    text : String,
    onCheckedChange : (Boolean) -> Unit,
    onTextSelected: (String) -> Unit,
) = Row(
    Modifier
        .fillMaxWidth()
        .heightIn(70.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    val checkState = remember { mutableStateOf(false) }
    Checkbox(
        checked = checkState.value,
        onCheckedChange = {
            checkState.value = it
            onCheckedChange.invoke(it)
        }
    )
    MyClickableText(text = text, onTextSelected)
}

@Composable
fun MyClickableText(text : String, onTextSelected : (String) -> Unit) {
    val initText = "I've read and agree with the "
    val termText = " Terms and Conditions"
    val secondText = " and the"
    val privacyText = " Privacy Policy."

    val frankenText = buildAnnotatedString {
        append(initText)
        withStyle(style = SpanStyle(color = TextColor)) {
            pushStringAnnotation(tag = termText, annotation = termText)
            append(termText)
        }
        append(secondText)
        withStyle(style = SpanStyle(color = TextColor)) {
            pushStringAnnotation(tag = privacyText, annotation = privacyText)
            append(privacyText)
        }
    }
    ClickableText(
        text = frankenText,
        onClick = { clickText ->
            frankenText.getStringAnnotations(clickText, clickText)
                .firstOrNull()?.also { span ->
                    Log.i("TAG", "{$span} es el texto click")

                    if (span.item == termText || span.item == privacyText) {
                        onTextSelected(span.item)
                    }
                }
        }
    )
}

@Composable
//fun MyButton(user : UserRegister, viewModel : RegisterViewModel) = Button(
fun MyButton(onClicked : () -> Unit, isEnabled : Boolean) = Button(
    onClick = {
        onClicked.invoke()
    },
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(50.dp),
    colors = ButtonDefaults.buttonColors(TextColor),
    enabled = isEnabled
) {
    Text(
        text = "Sign Up",
        color = Color.Black
    )
}

@Composable
fun MyCustomButton(text: String, heigh: Int, onSigIn: () -> Unit) = Button(
    onClick = { onSigIn() },
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(heigh.dp),
    colors = ButtonDefaults.buttonColors(TextColor)
) {
    Text(
        text = text,
        color = Color.Black
    )
}

@Composable
fun MyCustomImage(image : Int, height : Int, width : Int) = Image(
    painter = painterResource(id = image),
    contentDescription = "Send a Message image",
    modifier = Modifier
        .height(height.dp)
        .width(width.dp)
        .background(Color.Transparent)
)
