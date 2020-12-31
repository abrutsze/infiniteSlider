package com.example.data.dataservice.apiservice

import com.example.entity.responcemodel.DataResponse
import retrofit2.Response
import retrofit2.http.*

interface AllApiService {

    @GET("test_app.json")
    suspend fun getData(): Response<List<DataResponse>>

}
