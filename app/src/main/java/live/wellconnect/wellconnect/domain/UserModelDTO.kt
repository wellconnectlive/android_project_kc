package live.wellconnect.wellconnect.domain

import com.google.firebase.database.PropertyName

data class UserModelDTO(
    @set:PropertyName("name") var name : String = "",
    @set:PropertyName("email") var email: String = "",
    @set:PropertyName("password") var password: String = "",
    //@set:PropertyName("photo") var photo: String = "",
)

data class UserModelContinueDTO(
    @set:PropertyName("name") var name : String = "",
    @set:PropertyName("lastNameFather") var lastNameFather : String = "",
    @set:PropertyName("lastNameMother") var lastNameMother : String = "",
    @set:PropertyName("gender") var gender  : Gender = Gender.FEMALE,
    @set:PropertyName("dni") var dni : String = "",
    @set:PropertyName("address") var address : String = "",
    @set:PropertyName("poblation") var poblation : String = "",
    @set:PropertyName("country") var country : String = "",
    @set:PropertyName("age") var age : String = "",
    @set:PropertyName("phoneNumber") var phoneNumber : String = "",
    @set:PropertyName("postalCode") var postalCode: String = "",
)