package com.baseproject.data.remote.service

import io.reactivex.Observable
import com.baseproject.data.model.DataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AuthService {
    @GET("posts")
    fun getData(@QueryMap hashMap: HashMap<String, Any>): Observable<Response<DataDTO>>
}
