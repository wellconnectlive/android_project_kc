package live.wellconnect.wellconnect.domain

import java.sql.Date


// todo, validar los atributos que pasarán a opcionales

data class UserModel(
    var gender : Gender?,
    var age : String?,
    var photo : String?,
    var country : String?,
    var phoneNumber : String?,
    var allowTracking : Boolean?,
    var myFiles : MyFiles?,
    var alerts : Alerts?,
    var religion : Religion?,
    var bloodType : BloodType?,
    var implants : List<Implants>,
    var medAllergy : List<MedsAllergy>,
    var foodAllergy : List<FoodAllergy>,
    var otherAllergy : List<OtherAllergy>,
    var userType : UserType?,
    var diseases : List<CommomDisease>,
    var contacts : List<Contacts>,

)

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

data class MyFiles(
    var name : String,
    var type : FileType,
)

data class Alerts(
    var alertType : AlertType,
    var date : Date,
)
enum class FileType {
    PDF,
    JPG,
    DATE
}

enum class AlertType {
    MEDICATION,
    DATES,
}

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
    INTELLECTUAL_DISABILITY,
    DIABETES,
    HYPERTENSION,
    ALZHEIMER,
    AUTISM,
    VON_WILLEBRAND_DISEASE,
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
