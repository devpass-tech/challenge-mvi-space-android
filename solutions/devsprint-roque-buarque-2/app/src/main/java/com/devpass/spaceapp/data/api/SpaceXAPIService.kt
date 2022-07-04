package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.launchList.data.model.Launch
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPIService {

    @POST("launches/query?limit=20")
    suspend fun fetchNextLaunches(@Body queryBody: QueryBody = QueryBody()): Launch
}

data class QueryBody(
    val options: OptionBody = OptionBody()
)

data class OptionBody(
    val page: Int = 1,
    val limit: Int = 20
)