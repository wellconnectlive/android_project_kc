package live.wellconnect.wellconnect.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user")
data class UserDAO(
    @PrimaryKey @ColumnInfo(name = "id") val id : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "lastNameFather") val lastNameFather : String,
    @ColumnInfo(name = "lastNameMother") val lastNameMother : String,
    //@ColumnInfo(name = "gender") val gender : String,
    @ColumnInfo(name = "dni") val dni : String,
    @ColumnInfo(name = "address") val address : String,
    @ColumnInfo(name = "poblation") val poblation : String, // no es reiterativo y va contenido en la direccion?
    @ColumnInfo(name = "country") val country : String,
    //@ColumnInfo(name = "bloodType") val bloodType : String,
    @ColumnInfo(name = "age") val age : Int,
    //@ColumnInfo(name = "birthDate") val birthDate : Date,
    //@ColumnInfo(name = "inscriptionDate") val inscriptionDate : Date,
    @ColumnInfo(name = "phoneNumber") val phoneNumber : String,
    @ColumnInfo(name = "postalCode") val postalCode : Int,
)
