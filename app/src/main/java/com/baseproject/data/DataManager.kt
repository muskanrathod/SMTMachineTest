package com.baseproject.data

import com.baseproject.data.pref.PreferenceSource
import com.baseproject.data.remote.RemoteSource

interface DataManager : PreferenceSource, RemoteSource{
    enum class LoggedInMode(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGEDIN(1)
    }

    enum class LoggedInType(val type: Int) {
        LOGGING_IN_FIRST_TIME(0),
        LOGGING_IN_REGULAR(1)
    }
}