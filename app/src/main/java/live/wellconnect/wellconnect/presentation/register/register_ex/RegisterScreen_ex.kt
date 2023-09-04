package live.wellconnect.wellconnect.presentation.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import live.wellconnect.wellconnect.domain.Gender
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.presentation.UserData
import live.wellconnect.wellconnect.presentation.register.register_ex.RegisterViewModelContinue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    userData: UserData?,
    viewModel: RegisterViewModelContinue,
) {

    val idData =  userData?.userId ?: ""
    //var id by remember { mutableStateOf(TextFieldValue()) } /// verificar cómo se manejará él primer registro para asignar id
    var name by remember { mutableStateOf(TextFieldValue()) }
    var fatherLastName by remember { mutableStateOf(TextFieldValue()) }
    var motherLastName by remember { mutableStateOf(TextFieldValue()) }
    var dni by remember { mutableStateOf(TextFieldValue()) }
    var address by remember { mutableStateOf(TextFieldValue()) }
    var poblation by remember { mutableStateOf(TextFieldValue()) }
    var country by remember { mutableStateOf(TextFieldValue()) }
    var age by remember { mutableStateOf(TextFieldValue()) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
    var postalCode by remember { mutableStateOf(TextFieldValue()) }
    val values = ArrayList<Any>()

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*TextField(
            value = id,
            onValueChange = { id = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "ID") }
        )
        values.add(id.text)*/
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Name") }
        )
        values.add(name.text)
        TextField(
            value = fatherLastName,
            onValueChange = { fatherLastName = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Apellido Paterno") }
        )
        values.add(fatherLastName.text)

        TextField(
            value = motherLastName,
            onValueChange = { motherLastName = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Apellido Materno") }
        )
        values.add(motherLastName.text)

        TextField(
            value = dni,
            onValueChange = { dni = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "DNI") }
        )
        values.add(dni.text)

        TextField(
            value = address,
            onValueChange = { address = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Direccion") }
        )
        values.add(address.text)

        TextField(
            value = poblation,
            onValueChange = { poblation = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Población") }
        )
        values.add(poblation.text)

        TextField(
            value = country,
            onValueChange = { country = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "País") }
        )
        values.add(country.text)

        TextField(
            value = age,
            onValueChange = { age = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Edad") }
        )
        values.add(age.text)

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Teléfono") }
        )
        values.add(phoneNumber.text)

        TextField(
            value = postalCode,
            onValueChange = { postalCode = it },
            modifier = Modifier
                .alpha(0.8F)
                .background(Color.White),
            placeholder = { Text(text = "Código Postal") }
        )
        values.add(postalCode.text)

        if(!checkUser(values)){
            val user = UserModel(Gender.FEMALE, values[0].toString(), values[1].toString(), values[2].toString(),  values[3].toString(), true, null, null, null, null, listOf(), listOf(), listOf(),listOf(),null, listOf(), listOf())
            GetButton(viewModel, user, idData)
        }

    }
}

fun checkUser(values: ArrayList<Any>): Boolean {

    return values.none { it.toString().isNotEmpty() }
}

/**
 * Simultáneamente se debe de crear una funcion para limpiar los textfield y señalizar que se ha agregado al nuevo objeto
 */
@Composable
fun GetButton(viewModel: RegisterViewModelContinue, user: UserModel, userData: String) {
    val response = viewModel.user.observeAsState()

    Button(
        onClick = {
            viewModel.addUser(user, userData)
            Log.i("PUSHED", user.toString())
                  },
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(text = "Registrar")
    }


}


