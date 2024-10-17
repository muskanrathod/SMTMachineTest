package com.baseproject.data.remote

import android.content.Context
import com.baseproject.data.model.DataDTO
import com.baseproject.data.pref.AppPreferenceManager
import com.baseproject.data.remote.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class RemoteDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    preferenceManager: AppPreferenceManager,
) : RemoteSource {

    @Inject
    lateinit var authService: AuthService
    override fun getData(hashMap: HashMap<String, Any>): Observable<Response<DataDTO>> =
        authService.getData(hashMap)


}
