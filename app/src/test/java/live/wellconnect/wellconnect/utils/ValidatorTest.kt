import live.wellconnect.wellconnect.domain.UserRegister
import live.wellconnect.wellconnect.utils.Validator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.rules.TemporaryFolder.Builder

class ValidatorTest {

    private lateinit var user : UserRegister

    @Before
    fun setup(){
        user = UserRegister(
            name = "Ernst",
            email = "ernie@fantasymail.com",
            password = "ernieernie"
        )
    }

    @Test
    fun `WHEN receive a name EXPECT a validate value`(){
        val name = Validator.validateName(user.name)

        assert(name)
    }

    @Test
    fun `WHEN receive a name EXPECT a not empty value`(){
        val name = Validator.validateName(user.name)

        Assert.assertNotNull(name)
    }

    @Test
    fun `WHEN receive a name EXPECT the same name`(){

        Assert.assertEquals("Ernst", user.name)
    }

    @Test
    fun `WHEN receive a email EXPECT a validate value`(){
        val email = Validator.validateEmail(user.email)

        assert(email)
    }

    @Test
    fun `WHEN receive a email EXPECT a correct format with @`(){
        val email = "lo.gmail.com"
        val response = Validator.validateEmail(email)

        Assert.assertFalse(response)
    }

    @Test
    fun `WHEN receive a email EXPECT a correct format with dot`(){
        val email = "lo@gmailcom"
        val response = Validator.validateEmail(email)

        Assert.assertFalse(response)
    }

    @Test
    fun `WHEN receive a email EXPECT a specific email`(){

        Assert.assertTrue("ernie@fantasymail.com" == user.email)
    }

    @Test
    fun `WHEN receive a password EXPECT a validate length`(){

        val password = Validator.validatePasswordOnLogin(user.password)
        assert(password)
    }

    @Test
    fun `WHEN receive a password EXPECT a minimun length`(){

        val aux = "lola6"
        val password = Validator.validatePasswordOnLogin(aux)
        Assert.assertFalse(password)
    }
}