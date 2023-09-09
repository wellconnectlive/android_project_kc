package live.wellconnect.wellconnect.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.domain.BloodType
import live.wellconnect.wellconnect.domain.Country
import live.wellconnect.wellconnect.domain.Gender
import live.wellconnect.wellconnect.domain.Implants
import live.wellconnect.wellconnect.domain.Religion
import live.wellconnect.wellconnect.ui.theme.TextColor
import live.wellconnect.wellconnect.ui.theme.TextColorDark
import live.wellconnect.wellconnect.ui.theme.myFont
import live.wellconnect.wellconnect.ui.theme.myFontSemiBold

@Composable
fun MakeText(text : String, size : Int, color : Color, align : TextAlign) = Text(
    text = text,
    style = TextStyle(
        fontSize = size.sp,
        fontFamily = myFont,
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
fun MyClickableText(
    text : String,
    onTextSelected : (String) -> Unit)
{
    val initText = "I've read and agree with the "
    val termText = " Terms and Conditions"
    val secondText = " and the"
    val privacyText = " Privacy Policy."

    val frankenText = buildAnnotatedString {
        append(initText)
        withStyle(style = SpanStyle(color = TextColor, fontFamily = myFont)) {
            pushStringAnnotation(tag = termText, annotation = termText)
            append(termText)
        }
        append(secondText)
        withStyle(style = SpanStyle(color = TextColor, fontFamily = myFont)) {
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
fun MyClickableTextLogin(
    onTextSelected: () -> Unit
)
{
    val initText = "Not a member? "
    val termText = " Register Now"

    val frankenText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColorDark, fontFamily = myFontSemiBold)) {
            append(initText)
        }
        withStyle(style = SpanStyle(color = TextColor, fontFamily = myFont)) {
            pushStringAnnotation(tag = termText, annotation = termText)
            append(termText)
        }
    }
    ClickableText(
        text = frankenText,
        onClick = { clickText ->
            frankenText.getStringAnnotations(clickText, clickText)
                .firstOrNull()?.also { span ->
                    Log.i("TAG", "{$span} es el texto click")

                    if (span.item == termText) {
                        onTextSelected()
                    }
                }
        }
    )
}

@Composable
fun MyButton(onClicked : () -> Unit, isEnabled : Boolean, text : String) = Button(
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
        text = text,
        color = Color.Black
    )
}

@Composable
fun MyCustomButton(text: String, heigh: Int, width : Float, colorText : Color, colorButton : Color, onSigIn: () -> Unit) = Button(
    onClick = { onSigIn() },
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier
        .fillMaxWidth(width)
        .heightIn(heigh.dp),
    colors = ButtonDefaults.buttonColors(colorButton)
) {
    Text(
        text = text,
        color = colorText
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

@Composable
fun MyCustomImageIcon(image: ImageVector, height: Int, width: Int) = Image(
    imageVector = image,
    contentDescription = "Send a Message image",
    modifier = Modifier
        .height(height.dp)
        .width(width.dp)
        .background(Color.Transparent)
)

@Composable
fun MyTextButton(onClick : () -> Unit, text: String, align: TextAlign) = TextButton(
        onClick = onClick,
    ) {
        Text(
            text,
            textAlign = align
        )
    }
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextField(
    value: Int?,
    onValueChange: (Int?) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(1.dp, colorResource(id = R.color.grisOscuroskyBlue), RoundedCornerShape(8.dp))
        .background(Color.White, shape = MaterialTheme.shapes.medium)
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        var text by remember { mutableStateOf(value?.toString() ?: "") }
        var isHintVisible by remember { mutableStateOf(value == null) }

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it.toIntOrNull())
                isHintVisible = it.isEmpty()
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Ocultar el teclado al hacer clic fuera del campo
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 12.dp,
                    top = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp
                )
        )

        if (isHintVisible) {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EditTextField_Preview() {
    EditTextField(0,{}, "Introduce tus datos")
}

@Composable
fun TextFieldForPhoto(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(1.dp, colorResource(id = R.color.grisOscuroskyBlue), RoundedCornerShape(8.dp))
        .background(Color.White, shape = MaterialTheme.shapes.medium)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person, // Icono de persona de la biblioteca Material
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )

        }
    }
}
@Composable
fun DropDownTextFieldWithDialog(gender: String, onGenderSelected: (Gender)->Unit) {
    var selectedGender by remember { mutableStateOf(gender) }

    DropDownTextField(
        value = selectedGender,
        onValueChange = { selectedGender = it },
        hint = "Selecciona tu género",
        options = listOf(Gender.MALE,Gender.FEMALE,Gender.NON_BINARY,Gender.OTHER),
        onOptionSelected = {
            selectedGender = it.toString()
            onGenderSelected(it)
        }
    )
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    options: List<Gender>,
    onOptionSelected: (Gender) -> Unit,
    modifier: Modifier = Modifier
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
) {
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(start = 12.dp, end = 12.dp)
    ) {

        ClickableText(
            text = AnnotatedString(value),
            onClick = {
                expanded = true // Activar el diálogo al hacer clic en el campo
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterStart)
                .wrapContentHeight(Alignment.CenterVertically),
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd) // Centrar verticalmente y alinear a la derecha
                .padding(end = 12.dp),
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )

        if (expanded) {
            GenderOptionsDialog(
                options = options,
                onOptionSelected = {
                    onOptionSelected(it)
                    expanded = false
                    // Cerrar el diálogo después de seleccionar una opción
                },
                onDismissRequest = {
                    expanded = false // Cerrar el diálogo si se descarta
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderOptionsDialog(
    options: List<Gender>,
    onOptionSelected: (Gender) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(
            text = "Selecciona tu género",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        ) },
        shape = MaterialTheme.shapes.medium,
        text = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                options.forEach { gender ->

                    Button(
                        onClick = {
                            onOptionSelected(gender)
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.grisOscuroskyBlue)

                        ),
                        border = BorderStroke(1.dp, colorResource(id = R.color.azul))//

                    ) {
                        Text(
                            text = gender.toString(),
                            color = colorResource(id = R.color.azul)
                        )
                    }
                }
            }
        }
        , confirmButton = {},
        dismissButton = {}
    )
}
@Preview
@Composable
fun GenderOptionsDialogPreview() {
    val genderOptions = listOf(Gender.MALE,Gender.FEMALE,Gender.NON_BINARY,Gender.OTHER)

    GenderOptionsDialog(
        options = genderOptions,
        onOptionSelected = {},
        onDismissRequest = {}
    )
}

@Composable
fun NumberedProgressBar(
    steps: Int,
    currentStep: Int,
    lineColor: Color = Color.Gray,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.LightGray
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until steps) {
                val isCurrentStep = i == currentStep

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(if (isCurrentStep) activeColor else inactiveColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (i + 1).toString(),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                if (i < steps - 1) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .background(lineColor)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun NumberedProgressBarPreview() {
    NumberedProgressBar(
        steps = 3,
        currentStep = 0,
        lineColor = Color.Gray,
        activeColor = Color.Blue,
        inactiveColor = Color.LightGray
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReligionSelectorWithDialog(selectedReligion: String, onReligionSelected: (Religion) -> Unit) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedReligionName by remember { mutableStateOf(selectedReligion) }

    ReligionSelectorTextField(
        value = selectedReligionName.toString(),
        onValueChange = { selectedReligionName = it.toString() },
        hint = "Selecciona tu religión",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        ReligionOptionsDialog(
            options = listOf(
                Religion.BUDDHISMO,Religion.CATHOLIC,Religion.EVANGELIST,Religion.HINDU,Religion.JEHOVAH_WITNESS,Religion.JEW,Religion.MUSLIM,
                Religion.SUNNI,Religion.SHIISM,Religion.ORTHODOX,Religion.PROTESTANT,Religion.JEHOVAH_WITNESS,Religion.CHRISTIAN_APOSTOLIC_ROMAN
            ), // Implementa una función que devuelve la lista de religiones
            selectedReligion = selectedReligionName.toString(),
            onOptionSelected = {
                selectedReligionName = it.toString()
                isDialogOpen = false
                onReligionSelected(it)
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun ReligionSelectorTextField(
    value: String,
    onValueChange: (Religion) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            text = if (value.isNotEmpty()) value else hint,
            color = if (value.isNotEmpty()) Color.Black else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically), // Centrar verticalmente y alinear a la derecha,
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReligionOptionsDialog(
    options: List<Religion>,
    selectedReligion: String,
    onOptionSelected: (Religion) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu religión",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { index, religion ->
                    val isSelected = religion.toString() == selectedReligion

                    Text(
                        text = religion.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(religion)
                                onDismissRequest()
                            },
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )

                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
@Preview
fun ReligionSelectorTextField_Preview() {
    ReligionSelectorTextField(
        value = "Cristianismo",
        onValueChange = {},
        hint = "Selecciona tu religión",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun ReligionOptionsDialog_Preview() {
    val religionOptions = listOf(
        Religion.BUDDHISMO,Religion.CATHOLIC,Religion.EVANGELIST,Religion.HINDU,Religion.JEHOVAH_WITNESS,Religion.JEW,Religion.MUSLIM,
        Religion.SUNNI,Religion.SHIISM,Religion.ORTHODOX,Religion.PROTESTANT,Religion.JEHOVAH_WITNESS,Religion.CHRISTIAN_APOSTOLIC_ROMAN
    )
    ReligionOptionsDialog(
        options = religionOptions,
        selectedReligion = "Cristianismo",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}

@Composable
fun BloodGroupSelectorWithDialog(selectedBloodGroup: String, onBloodSelected: (BloodType) -> Unit) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedBloodGroupName by remember { mutableStateOf(selectedBloodGroup) }

    BloodGroupSelectorTextField(
        value = selectedBloodGroupName,
        onValueChange = { selectedBloodGroupName = it },
        hint = "Selecciona tu grupo sanguíneo",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        BloodGroupOptionsDialog(
            options = listOf(
                BloodType.ABplus,
                BloodType.ABminus,
                BloodType.Aplus,
                BloodType.Aminus,
                BloodType.Bplus,
                BloodType.Bminus,
                BloodType.Oplus,
                BloodType.Ominus
            ),
            selectedBloodGroup = selectedBloodGroupName,
            onOptionSelected = {
                selectedBloodGroupName = it.toString()
                isDialogOpen = false
                onBloodSelected(it)
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun BloodGroupSelectorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            text = if (value.isNotEmpty()) value else hint,
            color = if (value.isNotEmpty()) Color.Black else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically), // Centrar verticalmente y alinear a la derecha,
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodGroupOptionsDialog(
    options: List<BloodType>,
    selectedBloodGroup: String,
    onOptionSelected: (BloodType) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu grupo sanguíneo",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { _, bloodGroup ->
                    val isSelected = bloodGroup.nombreEnEspanol == selectedBloodGroup

                    Text(
                        text = bloodGroup.nombreEnEspanol,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(bloodGroup)
                                onDismissRequest()
                            },
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
@Preview
fun BloodGroupSelectorTextField_Preview() {
    BloodGroupSelectorTextField(
        value = "A",
        onValueChange = {},
        hint = "Selecciona tu grupo sanguíneo",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun BloodGroupOptionsDialog_Preview() {
    val bloodGroupOptions = listOf(
        BloodType.ABplus,
        BloodType.ABminus,
        BloodType.Aplus,
        BloodType.Aminus,
        BloodType.Bplus,
        BloodType.Bminus,
        BloodType.Oplus,
        BloodType.Ominus)
    BloodGroupOptionsDialog(
        options = bloodGroupOptions,
        selectedBloodGroup = "A",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}

@Composable
fun ImplantSelectorWithDialog(selectedImplant: String, onImplantSelected: (Implants) -> Unit) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedImplantName by remember { mutableStateOf(selectedImplant) }

    ImplantSelectorTextField(
        value = selectedImplantName,
        onValueChange = { selectedImplantName = it },
        hint = "Selecciona un implante",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        ImplantOptionsDialog(
            options =listOf(
                Implants.CARDIAC_BYPASS,
                Implants.KNEE_PROSTHESIS,
                Implants.HIP_PROSTHESIS,
                Implants.CORONARY_STENT,
                Implants.PACEMAKER,
                Implants.COCHLEAR_IMPLANT,
                Implants.DENTAL_IMPLANT,
                Implants.CATARACT_IMPLANT,
                Implants.OTHER
            ),
             // Implementa una función que devuelve la lista de tipos de implantes
            selectedImplant = selectedImplantName,
            onOptionSelected = {
                selectedImplantName = it.toString()
                isDialogOpen = false
                onImplantSelected(it)
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun ImplantSelectorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = if (value.isNotEmpty()) value else hint,
            color = if (value.isNotEmpty()) Color.Black else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically), // Centrar verticalmente y alinear a la derecha,
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImplantOptionsDialog(
    options: List<Implants>,
    selectedImplant: String,
    onOptionSelected: (Implants) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona un tipo de implante",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { _, implant ->
                    val isSelected = implant.nameInSpanish == selectedImplant

                    Text(
                        text = implant.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(implant)
                                onDismissRequest()
                            },
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
@Preview
fun ImplantSelectorTextField_Preview() {
    ImplantSelectorTextField(
        value = "Bypass cardíaco",
        onValueChange = {},
        hint = "Selecciona un implante",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun ImplantOptionsDialog_Preview() {
    ImplantOptionsDialog(
        options = listOf(
            Implants.CARDIAC_BYPASS,
            Implants.KNEE_PROSTHESIS,
            Implants.HIP_PROSTHESIS,
            Implants.CORONARY_STENT,
            Implants.PACEMAKER,
            Implants.COCHLEAR_IMPLANT,
            Implants.DENTAL_IMPLANT,
            Implants.CATARACT_IMPLANT,
            Implants.OTHER
        ),
        selectedImplant = "Bypass cardíaco",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myDropDownMenu(allergyType: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var allergy by remember { mutableStateOf(allergyType) }
    var isCheckedPolen by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {

    }
    if (showConfirmation) {
        Text(
            text = "Tu alergia ha sido registrada correctamente",
            modifier = Modifier
                .padding(16.dp)
        )
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {
        TextField(
            value = allergy,
            onValueChange = { newValue ->
                allergy = newValue
            },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = {
                    TextFieldWithCheckbox(
                        text = "Polen",
                        isChecked = isCheckedPolen,
                        onCheckedChange = { newValue ->
                            isCheckedPolen = newValue
                            if (newValue) {
                                allergy = "Polen"
                                isExpanded = false
                            }
                        },
                        onDialogOpen = { }
                    )
                },
                onClick = {
                    allergy = "Polen"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    TextFieldWithCheckbox(
                        text = "Ácaros",
                        isChecked = false,
                        onCheckedChange = { },
                        onDialogOpen = { }
                    )
                },
                onClick = {
                    allergy = "Ácaros"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    TextFieldWithCheckbox(
                        text = "Picaduras de avispa",
                        isChecked = false,
                        onCheckedChange = { },
                        onDialogOpen = { }
                    )
                },
                onClick = {
                    allergy = "Picaduras de avispa"
                    isExpanded = false
                }
            )
        }
    }
}

@Composable
@Preview
fun myDropDownMenu_Preview() {
    myDropDownMenu("Alergia medicamentos")
}

@Composable
fun TextFieldWithCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDialogOpen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onDialogOpen() }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun DropDownTextFieldForAlergies(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
) {
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }
    var expanded by remember { mutableStateOf(false) }

    val selectedOptionIndex = options.indexOf(value)

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(start = 12.dp, end = 12.dp)
    ) {

        ClickableText(
            text = AnnotatedString(value),
            onClick = {
                expanded = true // Activar el diálogo al hacer clic en el campo
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterStart)
                .wrapContentHeight(Alignment.CenterVertically),
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd) // Centrar verticalmente y alinear a la derecha
                .padding(end = 12.dp),
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )

        if (expanded) {
            AlergiesOptionsDialog(
                options = options,
                onOptionSelected = {
                    onOptionSelected(it)
                    expanded = false // Cerrar el diálogo después de seleccionar una opción
                },
                onDismissRequest = {
                    expanded = false // Cerrar el diálogo si se descarta
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlergiesOptionsDialog(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var showConfirmation by remember { mutableStateOf(false) }
    var showEdiTextAlergie by remember { mutableStateOf(false) }
    var etAlergy by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    var resetDropDownMenus by remember { mutableStateOf(false) }

    LaunchedEffect(showConfirmation) {
        if (showConfirmation) {
            scope.launch {
                delay(3000) // Espera durante 3 segundos
                showConfirmation = false
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Selecciona tus alergias",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.align(Alignment.Top)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar",
                        tint = Color.Gray
                    )
                }

            }
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            Box(
                modifier = Modifier.height(450.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    if (showConfirmation) {
                        Text(
                            text = "Tu alergia ha sido registrada correctamente",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                    if (showEdiTextAlergie && !showConfirmation) {
                        EditTextField(
                            value = etAlergy.toInt(),
                            onValueChange = { newValue ->
                                //etAlergy = newValue
                            },
                            hint = "Tengo alergia a:"
                        )
                    }

                    options.forEach { allergy ->
                        myDropDownMenu(allergyType = allergy)
                    }

                    Button(
                        onClick = {
                            showConfirmation = false
                            showEdiTextAlergie = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                            contentColor = Color.White
                        ) //

                    ) {
                        Text(text = "Añadir otro tipo de alergia")
                    }

                    Button(
                        onClick = {
                            showConfirmation = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.grisOscuroskyBlue)
                        ),
                        border = BorderStroke(1.dp, colorResource(id = R.color.grisOscuroskyBlue))

                    ) {
                        Text(
                            text = "Guardar"
                        )
                    }

                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )

}

@Preview
@Composable
fun AlergiesOptionsDialogPreview() {
    val allergyOptions = listOf("Polen", "Ácaros", "Picaduras de avispa", "Otros")

    AlergiesOptionsDialog(
        options = allergyOptions,
        onOptionSelected = {},
        onDismissRequest = {}
    )
}

@Composable
fun CountrySelectorWithDialog(selectedCountry: String, onSelectedCountry: (Country)-> Unit) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCountryName by remember { mutableStateOf(selectedCountry) }

    CountrySelectorTextField(
        value = selectedCountryName,
        onValueChange = { selectedCountryName = it },
        hint = "Selecciona tu país",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        CountryOptionsDialog(
            options = Country.values().map { it.name }, // Implementa una función que devuelve la lista de países
            selectedCountry = selectedCountryName,
            onOptionSelected = {
                selectedCountryName = it.toString()
                isDialogOpen = false
                onSelectedCountry(it)
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}
@Composable
fun CountrySelectorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(start = 12.dp, end = 12.dp)

    ) {
        Text(
            text = if (value.isNotEmpty()) value else hint,
            color = if (value.isNotEmpty()) Color.Black else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically), // Centrar verticalmente y alinear a la derecha,
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryOptionsDialog(
    options: List<String>,
    selectedCountry: String,
    onOptionSelected: (Country) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu país",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { _, country ->
                    val isSelected = country == selectedCountry

                    Text(
                        text = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                val country = obtenerPaisPorNombre(country)
                                if (country != null) {
                                    onOptionSelected(country)
                                }
                                onDismissRequest()
                            },
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
@Preview
fun CountrySelectorTextField_Preview() {
    CountrySelectorTextField(
        value = "United States",
        onValueChange = {},
        hint = "Selecciona tu país",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun CountryOptionsDialog_Preview() {
    val countriesOfSouthAndCentralAmerica = listOf(
        "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
        "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela", "Belize", "Costa Rica",
        "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama"
    )
    CountryOptionsDialog(
        options = countriesOfSouthAndCentralAmerica,
        selectedCountry = "United States",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
fun obtenerPaisPorNombre(nombreEnEspanol: String): Country? {
    return Country.values().firstOrNull { it.name.equals(nombreEnEspanol, ignoreCase = true) }
}
fun obtenerImplantePorNombre(nombreEnEspanol: String): Implants? {
    return Implants.values().firstOrNull { it.name.equals(nombreEnEspanol, ignoreCase = true) }
}
fun obtenerTipoDeSangrePorNombre(nombreEnEspanol: String): BloodType? {
    return BloodType.values().firstOrNull { it.name.equals(nombreEnEspanol, ignoreCase = true) }
}
@Composable
fun CheckboxWithCustomIcon(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(1.dp, colorResource(id = R.color.grisOscuroskyBlue), RoundedCornerShape(8.dp))
        .background(Color.White, shape = MaterialTheme.shapes.medium)
) {
    val customCheckboxColors = CheckboxDefaults.colors(
        checkedColor = colorResource(id = R.color.grisOscuroskyBlue), // Cambia aquí al color verde que desees
        uncheckedColor = Color.Gray // Color cuando no está marcado
    )

    Row(
        modifier = modifier
            .padding(horizontal = 12.dp), // Espaciado horizontal
        verticalAlignment = Alignment.CenterVertically // Centrar verticalmente
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(
            checked = checked,
            onCheckedChange = { newChecked ->
                onCheckedChange(newChecked)
            },
            colors = customCheckboxColors,
            modifier = Modifier.size(24.dp).padding(end = 8.dp)
        )
    }
}
@Composable
@Preview
fun CheckboxWithCustomIconPreview() {
    var checked by remember { mutableStateOf(true) }

    CheckboxWithCustomIcon(
        text = "Tengo diabetes",
        checked = checked,
        onCheckedChange = { newChecked ->
            checked = newChecked
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextFieldForNewDiseases(
    value: String?,
    onValueChange: (String?) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(1.dp, colorResource(id = R.color.grisOscuroskyBlue), RoundedCornerShape(8.dp))
        .background(Color.White, shape = MaterialTheme.shapes.medium)
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {

        var isHintVisible by remember { mutableStateOf(value?.isEmpty()) }

        if (value != null) {
            BasicTextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                    isHintVisible = it.isEmpty()
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Ocultar el teclado al hacer clic fuera del campo
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 12.dp,
                        top = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
            )
        }

        if (isHintVisible == true) {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EditTextFieldForDeseases_Preview() {
    EditTextFieldForNewDiseases("",{}, "Introduce una nueva dolencia")
}

