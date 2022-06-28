package com.devpass.spaceapp.launchList.data

import kotlinx.coroutines.flow.Flow

interface LaunchListRepository {

    fun fetchLaunchList() : Flow<LaunchListAction>
}