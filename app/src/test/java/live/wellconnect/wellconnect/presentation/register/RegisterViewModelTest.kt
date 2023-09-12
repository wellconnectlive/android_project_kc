package live.wellconnect.wellconnect.presentation.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.firestore.DocumentSnapshot
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.presentation.register.register_ex.RegisterViewModelContinue
import live.wellconnect.wellconnect.viewmodels.firebaseService.FireBaseService
import live.wellconnect.wellconnect.viewmodels.firebaseService.FireBaseServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito

internal class RegisterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //private lateinit var viewModel: RegisterViewModel
    private lateinit var repository: DataRepository
    private val testDispatcher = UnconfinedTestDispatcher()
    private var email = ""
    private var password = ""


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        //viewModel = RegisterViewModel(repository)
    }

    /*@Test
    fun `WHEN register a user EXPECT the correct params`() = runBlocking {
         RegisterStates.NameTaking(  "Albert")
         RegisterStates.EmailTaking(  "albert@gmail.com")
         RegisterStates.PasswordTaking(  "1234567")
         RegisterStates.RepasswordTaking(  "1234567")
         RegisterStates.TermsAndPolicyTaking(  true)


       // Assert.assertTrue(RegisterStates.EmailTaking)

    }*/


    @Test
    fun testin(){
        val service = Mockito.mock(FireBaseService::class.java)

        Mockito.`when`(service. register("la@gmail.com", "lalalala")).thenReturn(true)

        val faker = Mockito.mock(DocumentSnapshot::class.java)

    }

    @AfterEach
    fun tearDown() {
    }
}