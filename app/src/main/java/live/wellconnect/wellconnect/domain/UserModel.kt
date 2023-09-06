package live.wellconnect.wellconnect.domain

import java.util.Date

// todo, validar los atributos que pasarán a opcionales
// add photo attibute
/*data class UserModel(
    //val id : String?,
    var name : String?,
    var lastNameFather : String?,
    var lastNameMother : String?,
    //var photo : String?,
    //var gender : Gender?,
    val gender : String,
    var dni : String?,
    var address : String?,
    val poblation : String?,
    var country : String?,
    //val bloodType : BloodType,
    //val bloodType : String,
    //val age : Int,
    var age : String?,
    //val birthDate : Date,
    //val birthDate : String,
    //val inscriptionDate : Date,
    //val inscriptionDate : String,
    var phoneNumber : String?,
    //val postalCode : Int,
    var postalCode : String?,
)*/

data class UserModel(
    val gender : String,
    var age : String?,
    var country : String?,
    var phoneNumber : String?,
    var religion: String,
    //val bloodType : BloodType,
    val bloodType : String,
    val implants: String
    //val age : Int,

    //val birthDate : Date,
    //val birthDate : String,
    //val inscriptionDate : Date,
    //val inscriptionDate : String,

    //val postalCode : Int,
    //var postalCode : String?,
)
///Users/isadevs/Documents/
enum class Gender{
    FEMALE,
    MALE,
}

enum class BloodType {
    ABplus,
    ABminus,
    Aplus,
    Aminus,
    Bplus,
    Bminus,
    Oplus,
    Ominus,
}

// cómo enlaza con la clase user???
data class UserDataModel (
    val id : String,
    val religion : Religion,
    val userType : UserType,
    val note : String,
    val allowTracking : Boolean,
    val contacts : List<Contacts>,
    val diseases : CommomDisease,
    val medAllergy : List<MedsAllergy>,
    val foodAllergy : List<FoodAllergy>,
    val otherAllergy : List<OtherAllergy>,
    val implants : List<Implants>
)

enum class Religion{
    CHRISTIAN_APOSTOLIC_ROMAN,
    ORTHODOX,
    JEW,
    MUSLIM,
    CATHOLIC,
    EVANGELIST,
    JEHOVAH_WITNESS,
    HINDU,
    PROTESTANT,
    SUNNI,
    SHIISM,
    BUDDHISMO,
}

enum class UserType{
    PREMIUM,
    FREEMIUM,
}

data class Contacts(
    val id : String,
    val name : String,
    val kinship : Kinship,
    val email : String,
    val phoneNumber : String,
    val address: String,
    val shareLocation : Boolean,
)

enum class Kinship {
    DAUGHTER,
    SON,
    GRANDMOTHER,
    GRANDFATHER,
    MOTHER,
    FATHER,
    SISTER,
    BROTHER,
    NEPHEW,
    FRIEND,
}

enum class CommomDisease {
    DISABILITY_INTELLECTUAL,
    DIABETES,
    HYPERTENSION,
    ALZHEIMER,
    AUTISM,
    DISEASE_VON_WILLEBRAND,
    HEMOPHILIA,
    DEMENTIA_SENILE,
    DEAFNESS,
}

enum class MedsAllergy {
    ANTIBIOTICS,
    ANTI_INFLAMMATORIES,
    GLUTEN, // NO ES ALIMENTICIA?
    PENICILLIN,
    ASPIRIN,
    IBUPROFEN,
    NAPROXEN_SODIC,
    SULFOAMIDES,
    ANTICONVULSANTS,
}

enum class FoodAllergy {
    COW_MILK,
    EGG,
    FISH_SEAFOOD,
    FRUITS_DRIED,
    WHEAT,
    PEACH,
    KIWI,
    BANANA,
    PEANUT,
}

enum class OtherAllergy {
    POLLEN,
    MITES,
    MOLD,
    HAIR_SKIN_ANIMALS,
    PICKETS_BEE,
    PICKETS_WASP,
    FUNGUS,
}

enum class Implants {
    PACEMAKER,
    DEFIBRILLATOR,
}
