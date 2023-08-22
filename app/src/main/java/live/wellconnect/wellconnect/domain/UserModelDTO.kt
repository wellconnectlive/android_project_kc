package live.wellconnect.wellconnect.domain

import com.google.firebase.database.PropertyName

data class UserModelDTO(
    @set:PropertyName("name") var name : String = "",
    @set:PropertyName("email") var email: String = "",
    @set:PropertyName("password") var password: String = "",
)