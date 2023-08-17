package live.wellconnect.wellconnect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import live.wellconnect.wellconnect.data.local.dao.UserDAO

@Database(entities = [UserDAO::class], version = 1, exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {
    abstract fun getDataQueryFromDAO() : DataDAO
}