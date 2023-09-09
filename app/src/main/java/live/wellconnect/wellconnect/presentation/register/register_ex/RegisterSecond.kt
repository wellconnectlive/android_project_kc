package com.jmoreno.mispruebasparawell

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.text.Selection.setSelection
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import live.wellconnect.wellconnect.presentation.SignInState
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.components.CheckboxWithCustomIcon
import live.wellconnect.wellconnect.components.DropDownTextFieldForAlergies
import live.wellconnect.wellconnect.components.EditTextField
import live.wellconnect.wellconnect.components.EditTextFieldForNewDiseases
import live.wellconnect.wellconnect.components.NumberedProgressBar
import live.wellconnect.wellconnect.domain.CommomDisease
import live.wellconnect.wellconnect.domain.Implants
import live.wellconnect.wellconnect.domain.UserDataModel
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserType
import live.wellconnect.wellconnect.presentation.UserData
import live.wellconnect.wellconnect.presentation.register.register_ex.RegisterViewModelContinue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterSecond(
    navController: NavController,
    userData: UserData?,
    viewModel: RegisterViewModelContinue,
    onRegContinue : () -> Unit,
    navigateBack: () -> Unit
) {
    val idData =  userData?.userId ?: ""
    viewModel.getUserValues(idData)
    val userName = viewModel.dataUser.value
    var isCheckedDiabetes by remember { mutableStateOf(false) }
    var anotherIllness by remember { mutableStateOf(false) }
    var isCheckedHypertension by remember { mutableStateOf(false) }
    var isCheckedAutism by remember { mutableStateOf(false) }
    var isCheckedVonWill by remember { mutableStateOf(false) }
    var isCheckedHemophilia by remember { mutableStateOf(false) }
    var isCheckedSenil by remember { mutableStateOf(false) }
    var isCheckedDeafness by remember { mutableStateOf(false) }
    
    var eTNewIllness by remember {  mutableStateOf<String?>(null) }
    var diseases by remember { mutableStateOf<List<CommomDisease?>>(emptyList()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {


        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        /*BackHandler {
            navController.navigateUp()
        }*/


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)


        ) {

            item {

                NumberedProgressBar(
                    steps = 3,
                    currentStep = 1,
                    lineColor = Color.LightGray,
                    activeColor = colorResource(id = R.color.grisOscuroskyBlue),
                    inactiveColor = Color.LightGray
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cuéntanos un poco sobre ti",
                    style = TextStyle(
                        color = colorResource(id = R.color.grisOscuroskyBlue),
                        fontSize = 24.sp, // Cambia el tamaño del texto aquí
                        fontWeight = FontWeight.Bold // Puedes ajustar el peso de la fuente si es necesario
                    )
                )
                Text(
                    "Selecciona la opción que más se ajuste a ti",
                    style = TextStyle(
                        color = colorResource(id = R.color.grisOscuroskyBlue),
                        fontSize = 16.sp,
                    )
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

                if (anotherIllness) {
                    EditTextFieldForNewDiseases(
                        value = eTNewIllness,
                        onValueChange = { eTNewIllness = it },
                        hint = "Añade tu dolencia o enfermedad"
                    )
                    val disease = eTNewIllness?.let { CommomDisease.fromString(it) }
                    diseases = diseases + disease
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DropDownAllergiesWithDialog("Tengo alergias")
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo diabetes",
                    checked = isCheckedDiabetes,
                    onCheckedChange = { newChecked ->
                        isCheckedDiabetes = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.DIABETES
                        }
                    }
                )

            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo hipertensión",
                    checked = isCheckedHypertension,
                    onCheckedChange = { newChecked ->
                        isCheckedHypertension = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.HYPERTENSION
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo autismo",
                    checked = isCheckedAutism,
                    onCheckedChange = { newChecked ->
                        isCheckedAutism = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.AUTISM
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo la enfermedad de Von Willebrand",
                    checked = isCheckedVonWill,
                    onCheckedChange = { newChecked ->
                        isCheckedVonWill = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.DISEASE_VON_WILLEBRAND
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo hemofilia",
                    checked = isCheckedHemophilia,
                    onCheckedChange = { newChecked ->
                        isCheckedHemophilia = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.HEMOPHILIA
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo demencia senil",
                    checked = isCheckedSenil,
                    onCheckedChange = { newChecked ->
                        isCheckedSenil = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.DEMENTIA_SENILE
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo sordera",
                    checked = isCheckedDeafness,
                    onCheckedChange = { newChecked ->
                        isCheckedDeafness = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.DEAFNESS
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CheckboxWithCustomIcon(
                    text = "Tengo discapacidad intelectual",
                    checked = isCheckedDeafness,
                    onCheckedChange = { newChecked ->
                        isCheckedDeafness = newChecked
                        if(isCheckedDiabetes){
                            diseases = diseases + CommomDisease.DISABILITY_INTELLECTUAL
                        }
                    }
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FloatingActionButton(
                onClick = { anotherIllness = !anotherIllness},
                modifier = Modifier
                    .size(56.dp),
                containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Button(
                onClick = {
                    //val user = UserDataModel(idData, UserType.FREEMIUM,"",false,
                        emptyList(),diseases,tValReligion,tValBloodGroup,tValImplants)
                    //viewModel.addUserContinue(userModel, user,idData)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                    contentColor = Color.White
                ) //

            ) {
                Text(text = "Siguiente")
            }
        }
    }

}/*
@Preview(showBackground = true)
@Composable
fun OnBoardingSecond_Preview() {
    RegisterSecond(NavController(LocalContext.current),SignInState(true,null)){}

}*/

@Composable
fun DropDownAllergiesWithDialog(sex: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedAllergy by remember { mutableStateOf(sex) }


    DropDownTextFieldForAlergies(
        value = selectedAllergy,
        onValueChange = { selectedAllergy = it },
        hint = "Añade tu alergia",
        options = listOf("Alergia medicamentos", "Alergia alimentaria", "Otras alergias"),
        onOptionSelected = {
            selectedAllergy = it
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        AllergiesOptionsDialog(
            allergies = listOf("Polen", "Polvo", "Animales", "Otros"),
            medications = listOf("a","b","c"),
            onAllergySelected = {
                selectedAllergy = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false },
        )

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun AllergiesOptionsDialog(
    allergies: List<String>,
    medications: List<String>,
    onAllergySelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedAllergy by remember { mutableStateOf("") }
    var selectedMedication by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var showSuccessMessage by remember { mutableStateOf(false) }

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
            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                // Dropdown menu para alergias a medicamentos
                if (isDropdownExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        medications.forEach { medication ->
                            Text(
                                text = medication,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedMedication = medication
                                        isDropdownExpanded = false
                                    }
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botones para otras alergias
                allergies.forEach { allergy ->
                    Button(
                        onClick = {
                            selectedAllergy = allergy
                            onDismissRequest()
                            showSuccessMessage = true

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.grisOscuroskyBlue)
                        ),
                        border = BorderStroke(1.dp, colorResource(id = R.color.azul))
                    ) {
                        Text(
                            text = allergy,
                            color = colorResource(id = R.color.azul)
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
fun AllergiesOptionsDialogPreview() {
    val allergyOptions = listOf("Polen", "Polvo", "Animales", "Otros")
    val medicationOptions = listOf("Medicamento 1", "Medicamento 2", "Medicamento 3")

    AllergiesOptionsDialog(
        allergies = allergyOptions,
        medications = medicationOptions,
        onAllergySelected = {},
        onDismissRequest = {}
    )
}
