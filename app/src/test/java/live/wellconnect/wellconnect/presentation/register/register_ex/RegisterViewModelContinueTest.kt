package live.wellconnect.wellconnect.presentation.register.register_ex

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.arch.core.executor.DefaultTaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.util.Assert.fail
import io.mockk.coEvery
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.domain.BloodType
import live.wellconnect.wellconnect.domain.Country
import live.wellconnect.wellconnect.domain.Gender
import live.wellconnect.wellconnect.domain.Implants
import live.wellconnect.wellconnect.domain.Religion
import live.wellconnect.wellconnect.domain.UserModel
import live.wellconnect.wellconnect.domain.UserModelDTO
import live.wellconnect.wellconnect.utils.getOrAwaitValue
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RegisterViewModelContinueTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModelContinue
    private lateinit var repository: DataRepository
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = RegisterViewModelContinue(repository)
    }

    @Test
    fun `WHEN send an id EXPECTED the user asociated to this id`() = runBlocking {
        // GIVEN
        val uID = "uX2yRDaY4RTGdalsFYLpPEXIBrU2"
        val userRegister = UserModelDTO("Roberto", "rmc@gmail.com", "123456")
        coEvery { repository.checkUser(uID) } returns (userRegister)

        // WHEN
        viewModel.getUserValues(uID)
        val actualLiveData = viewModel.dataUser.getOrAwaitValue()

        assert(actualLiveData.email == userRegister.email)
    }
    /*Este test verifica que, cuando se llama al método getUserValues con un ID específico (uID),
    el LiveData dataUser se actualiza con un objeto de usuario (UserModelDTO) asociado a ese ID.
    Se configura el comportamiento simulado del repository para devolver el usuario
    esperado (userRegister) cuando se llama a checkUser(uID).
     */
    @Test
    fun `addUser should call repository insertUser with correct parameters`() = runBlocking {
        // GIVEN
        val userModel = UserModel("Roberto",Gender.MALE,"",Country.PANAMA,34,1234567,Religion.CATHOLIC,BloodType.Bminus,
            listOf(Implants.OTHER)
        )
        val userId = "user123"

        // WHEN
        viewModel.addUser(userModel, userId)

        // THEN
        coVerify { repository.insertUser(userModel, userId) }
    }
    /*Este test verifica que el método addUser del viewModel llama correctamente
    al método insertUser del repository con los parámetros esperados (userModel y userId).
    Se configura el viewModel y el repository simulado para realizar esta verificación.
    Este test asegura que la interacción entre el viewModel y el repository es la
    correcta al agregar un usuario.
     */

    @After
    fun tearDown() {
        Dispatchers.resetMain()

    }
}
