package com.baseproject.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.baseproject.data.model.DataDTO
import com.baseproject.data.pref.AppPreferenceManager
import com.baseproject.data.remote.RemoteDataManager
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager,
    private val remoteDataManager: RemoteDataManager
) : DataManager {

    var observer = MutableLiveData<Boolean>()
    public var appPreferenceManager: AppPreferenceManager = preferenceManager

    override fun saveDataDTO(key: String, value: String?) = appPreferenceManager.saveDataDTO(key, value)

    override fun getDataDTO(key: String): String? = appPreferenceManager.getDataDTO(key)

    override fun getData(hashMap: HashMap<String, Any>): Observable<Response<DataDTO>> = remoteDataManager.getData(hashMap)


}