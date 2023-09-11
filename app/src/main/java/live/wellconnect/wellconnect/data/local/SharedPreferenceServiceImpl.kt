package live.wellconnect.wellconnect.data.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import live.wellconnect.wellconnect.data.local.interfaces.SharedPreferenceService

class SharedPreferenceServiceImpl : SharedPreferenceService {

    companion object {
        var shared : SharedPreferences? = null
        fun init(context: Context) {
            shared = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        }
    }

    override fun getPrefString(prefName: String, response: Boolean): Boolean? = shared!!.getBoolean(prefName, response)

    override fun putPrefString(prefName: String, response: Boolean) {
        val add = shared!!.edit()
        add.putBoolean(prefName, response)
        add.apply()
    }
}