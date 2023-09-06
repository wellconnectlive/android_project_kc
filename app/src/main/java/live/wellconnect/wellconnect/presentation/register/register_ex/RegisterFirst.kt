package live.wellconnect.wellconnect.presentation.register.register_ex

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import live.wellconnect.wellconnect.R
import live.wellconnect.wellconnect.components.BloodGroupSelectorWithDialog
import live.wellconnect.wellconnect.components.CountrySelectorWithDialog
import live.wellconnect.wellconnect.components.DropDownTextFieldWithDialog
import live.wellconnect.wellconnect.components.EditTextField
import live.wellconnect.wellconnect.components.ImplantSelectorWithDialog
import live.wellconnect.wellconnect.components.NumberedProgressBar
import live.wellconnect.wellconnect.components.ReligionSelectorWithDialog
import live.wellconnect.wellconnect.components.TextFieldForPhoto
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.data.DataRepositoryImpl
import live.wellconnect.wellconnect.domain.Gender
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.presentation.UserData
import live.wellconnect.wellconnect.presentation.SignInState
import live.wellconnect.wellconnect.presentation.register.GetButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFirst(
    userData: UserData?,
    viewModel: RegisterViewModelContinue,
    onRegContinue : () -> Unit
) {
    //var tValName by remember { mutableStateOf("") }
    val idData =  userData?.userId ?: ""
    var tValSex by remember { mutableStateOf("") }
    var tValAge by remember { mutableStateOf("") }
    var tValCountry by remember { mutableStateOf("") }
    var tValPhoneNumber by remember { mutableStateOf("") }
    var tValBloodGroup by remember { mutableStateOf("") }
    val values = ArrayList<Any>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top= 16.dp, bottom = 8.dp) // Ajusta el valor según el margen deseado
        ) {
            NumberedProgressBar(
                steps = 3,
                currentStep = 0,
                lineColor = Color.LightGray,
                activeColor = colorResource(id = R.color.grisOscuroskyBlue),
                inactiveColor = Color.LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text= "Vamos a crear tu perfil",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 24.sp, // Cambia el tamaño del texto aquí
                    fontWeight = FontWeight.Bold // Puedes ajustar el peso de la fuente si es necesario
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Necesitamos tus datos",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 16.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hola XXXXX",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 16.sp,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 92.dp)
                .wrapContentSize(align = Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {


            DropDownTextFieldWithDialog("Indica tu sexo"){ selectedGender ->
                values.add(selectedGender)
            }

            EditTextField(
                value = tValAge,
                onValueChange = { tValAge = it },
                hint = "Edad"
            )
            values.add(tValAge)
            TextFieldForPhoto("Tu foto")
            CountrySelectorWithDialog("Indica tu país de origen"){ selectedCountry ->
                values.add(selectedCountry)
            }

            EditTextField(
                value = tValPhoneNumber,
                onValueChange = { tValPhoneNumber = it },
                hint = "Teléfono"
            )
            values.add(tValPhoneNumber)
            ReligionSelectorWithDialog("Religion") { selectedReligion ->
                values.add(selectedReligion)
            }
            BloodGroupSelectorWithDialog("Indica tu grupo sanguíneo"){ selectedBlood ->
                values.add((selectedBlood))
            }

            ImplantSelectorWithDialog("Implantes"){ selectedImplant ->
                values.add((selectedImplant))
            }

            Spacer(modifier = Modifier.height(30.dp))
            if(!checkUser(values)){
                val user = UserModel(values[0].toString(), values[1].toString(), values[2].toString(),values[3].toString(), values[4].toString(), values[5].toString(),values[5].toString())
                viewModel.addUser(user,idData)
            }

        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = { onRegContinue
                },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                contentColor = Color.White
            )

        ) {
            Text(text = "Siguiente")
        }
    }


}
fun checkUser(values: ArrayList<Any>): Boolean {

    return values.none { it.toString().isNotEmpty() }
}
/*
@Preview(showBackground = true)
@Composable
fun RegisterFirst_Preview() {
    RegisterFirst(SignInState(true,null)){}

}*/
