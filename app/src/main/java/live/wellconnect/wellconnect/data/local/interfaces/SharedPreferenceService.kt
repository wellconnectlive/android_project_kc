package live.wellconnect.wellconnect.data.local.interfaces

interface SharedPreferenceService {
    fun getPrefString(prefName : String, response : Boolean) : Boolean?
    fun putPrefString(prefName : String, response : Boolean)
}