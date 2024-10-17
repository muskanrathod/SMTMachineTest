package com.baseproject.data.pref

interface PreferenceSource {

    fun saveDataDTO(key: String, value: String?)
    fun getDataDTO(key: String): String?
}