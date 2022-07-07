package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.launchList.data.model.Launch
import com.devpass.spaceapp.launchList.data.model.LaunchPad
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceXAPIService {

    @POST("v5/launches/query")
    suspend fun fetchNextLaunches(@Body queryBody: QueryBody = QueryBody()): Launch

    @GET("v4/launchpads/{id}")
    suspend fun fetchLaunchpadDetails(@Path("id") launchId: String): LaunchPad
}

data class QueryBody(
    val options: OptionBody = OptionBody()
)

data class OptionBody(
    val page: Int = 1,
    val limit: Int = 20
)