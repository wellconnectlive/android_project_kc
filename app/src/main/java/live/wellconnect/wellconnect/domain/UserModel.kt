package live.wellconnect.wellconnect.domain

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
*/
data class UserModel(
    //val id : String?,
    var name: String?,
    var gender: Gender?,
    var address : String?,
    var country: Country?,
    var age: Int?,
    var phoneNumber: Int?,
    val religion: Religion?,
    val bloodType: BloodType?,
    val implants: List<Implants?>?
)
data class UserDataModel (
    val id : String,
    val userType : UserType,
    val note : String,
    val allowTracking : Boolean,
    val contacts : List<Contacts>,
    val diseases : List<CommomDisease?>,
    val medAllergy : List<MedsAllergy>,
    val foodAllergy : List<FoodAllergy>,
    val otherAllergy : List<OtherAllergy>,
)

///Users/isadevs/Documents/
enum class Gender(val gender: String){
    FEMALE("Femenino"),
    MALE("Masculino"),
    NON_BINARY("No Binario"),
    OTHER("Otro"),
}

enum class BloodType(val nombreEnEspanol: String) {
    ABplus("AB+"),
    ABminus("AB-"),
    Aplus("A+"),
    Aminus("A-"),
    Bplus("B+"),
    Bminus("B-"),
    Oplus("O+"),
    Ominus("O-")
}
// cómo enlaza con la clase user???

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

enum class CommomDisease(var nombreEnEspanol: String) {
    DISABILITY_INTELLECTUAL("Tengo discapacidad intelectual"),
    DIABETES("Tengo diabetes"),
    HYPERTENSION("Tengo hipertensión"),
    ALZHEIMER("Tengo Alzheimer"),
    AUTISM("Tengo autismo"),
    DISEASE_VON_WILLEBRAND("Tengo la enfermedad de Von Willebrand"),
    HEMOPHILIA("Tengo hemofilia"),
    DEMENTIA_SENILE("Tengo demencia senil"),
    DEAFNESS("Tengo sordera"),
    OTHER("");

    companion object {
        fun fromString(nombre: String): CommomDisease {
            for (disease in values()) {
                if (disease.nombreEnEspanol == nombre) {
                    return disease
                }
            }
            // Si no se encuentra una coincidencia, se devuelve "OTRA_ENFERMEDAD"
            OTHER.nombreEnEspanol = nombre
            return OTHER
        }
    }
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

enum class Implants(val nameInSpanish: String) {
    CARDIAC_BYPASS("Bypass cardíaco"),
    KNEE_PROSTHESIS("Prótesis de rodilla"),
    HIP_PROSTHESIS("Prótesis de cadera"),
    CORONARY_STENT("Stent coronario"),
    PACEMAKER("Pacemaker"),
    COCHLEAR_IMPLANT("Implante coclear"),
    DENTAL_IMPLANT("Implante dental"),
    CATARACT_IMPLANT("Implante de catarata"),
    OTHER("Otro")
}
enum class Country(name: String) {
    BELICE("Belice"),
    COSTA_RICA("Costa Rica"),
    EL_SALVADOR("El Salvador"),
    GUATEMALA("Guatemala"),
    HONDURAS("Honduras"),
    NICARAGUA("Nicaragua"),
    PANAMA("Panamá")

}
