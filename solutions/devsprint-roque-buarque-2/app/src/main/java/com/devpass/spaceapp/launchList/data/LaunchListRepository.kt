package com.devpass.spaceapp.launchList.data

import kotlinx.coroutines.flow.Flow

interface LaunchListRepository {

    suspend fun fetchLaunchList() : Flow<LaunchListAction>
}