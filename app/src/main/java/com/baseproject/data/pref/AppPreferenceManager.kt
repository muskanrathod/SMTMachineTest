package com.baseproject.data.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) : Preferences(), PreferenceSource {
    init {
        init(context, "AppPref")
    }

    override fun saveDataDTO(key: String, value: String?) {
        var dataStoreKey by stringPref( key )
        dataStoreKey= value
    }

    override fun getDataDTO(key: String): String? {
        val dataStoreKey by stringPref( key )
        return  dataStoreKey
    }

}



