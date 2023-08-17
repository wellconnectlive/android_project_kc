package live.wellconnect.wellconnect.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import live.wellconnect.wellconnect.data.local.dao.UserDAO

@Dao
interface DataDAO {

    @Query("SELECT * FROM user")
    suspend fun getAllUsers() : List<UserDAO>

    @Insert
    suspend fun insertNew(userDAO: UserDAO)
}