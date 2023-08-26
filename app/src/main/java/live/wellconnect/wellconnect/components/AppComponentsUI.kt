package live.wellconnect.wellconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.ui.theme.TextColor

@Composable
fun MakeText(text : String, size : Int, color : Color, align : TextAlign) = Text(
    text = text,
    style = TextStyle(
        fontSize = size.sp,
        fontFamily = FontFamily.Serif,
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
fun MakeTextField(labelValue: String, icon: ImageVector?){
    
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
        onValueChange = { textValue.value = it },
        singleLine = true,
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
fun MakeTextFieldPassword(labelValue: String, icon: ImageVector?){

    val passwordValue = remember { mutableStateOf("") }

    val passworsIsVisible = remember { mutableStateOf(false) }

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
        onValueChange = { passwordValue.value = it },
        singleLine = true,
        trailingIcon = {
            if (icon != null) {
                val iconImage = if(passworsIsVisible.value){
                    Icons.Outlined.Visibility
                }else {
                    Icons.Outlined.VisibilityOff
                }
                IconButton(onClick = { passworsIsVisible.value = !passworsIsVisible.value }) {
                    Image(imageVector = icon, contentDescription = "Pencil Edit")
                }
            }
        },
        visualTransformation = if(passworsIsVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(10.dp),
    )
}