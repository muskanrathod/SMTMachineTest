package com.baseproject.data.remote

import com.baseproject.data.model.DataDTO
import io.reactivex.Observable
import retrofit2.Response


interface RemoteSource {
    fun getData(hashMap: HashMap<String, Any>): Observable<Response<DataDTO>>
}