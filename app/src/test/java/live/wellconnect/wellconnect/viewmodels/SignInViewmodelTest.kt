package live.wellconnect.wellconnect.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import live.wellconnect.wellconnect.data.DataRepository
import live.wellconnect.wellconnect.presentation.SignInViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : SignInViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private var email = ""
    private var password = ""

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SignInViewModel()
        email = "lo@gmail.com"
        password = "lololo"
    }

    @Test
    fun `WHEN get login EXPECT a successfully response`() = runBlocking {

        coEvery { viewModel.login(email, password) }

        Assert.assertTrue(viewModel.isSignIn.value)
    }
}