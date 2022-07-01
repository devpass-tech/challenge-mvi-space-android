package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.launchList.data.model.Launch
import retrofit2.http.POST

interface SpaceXAPIService {

    @POST("/launches/query")
    suspend fun fetchNextLaunches(): List<Launch>
}