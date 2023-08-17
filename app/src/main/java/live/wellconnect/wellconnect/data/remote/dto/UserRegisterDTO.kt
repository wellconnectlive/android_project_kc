package live.wellconnect.wellconnect.data.remote.dto

import com.squareup.moshi.Json

data class UserRegisterDTO(
    @Json(name = "id") val id : String?,
    @Json(name = "name") val name : String?,
    @Json(name = "email") val email : String?,
    @Json(name = "password") val password : String?,
)
