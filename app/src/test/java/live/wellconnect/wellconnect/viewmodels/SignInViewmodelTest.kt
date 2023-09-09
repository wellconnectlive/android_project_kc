package live.wellconnect.wellconnect.viewmodels

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.mockk.MockKAnnotations
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import live.wellconnect.wellconnect.presentation.SignInViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SignInViewModelTest {

    private lateinit var viewModel : SignInViewModel
    private var email = ""
    private var password = ""


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = SignInViewModel()
        email = "lo@gmail.com"
        password = "lololo"
    }

    @Test
    fun `WHEN get login EXPECT a successfully response`() = runTest {
        val fireMock = mockkObject(FirebaseAuth::class.java)

        viewModel.login(email, password)
        Assert.assertEquals(true, viewModel.isSignIn)
    }
}