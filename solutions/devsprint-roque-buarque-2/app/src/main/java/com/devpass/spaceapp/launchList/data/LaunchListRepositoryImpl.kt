package com.devpass.spaceapp.launchList.data

import com.devpass.spaceapp.data.api.SpaceXAPIService
import kotlinx.coroutines.flow.*

class LaunchListRepositoryImpl (
    private val service: SpaceXAPIService
) : LaunchListRepository {

    override fun fetchLaunchList(): Flow<LaunchListAction> {
        return flow<LaunchListAction> {
            // TODO service network request
            val list = listLaunch
            emit(LaunchListAction.Success(list))
        }
            .catch { error -> emit(LaunchListAction.Error(error)) }
            .onStart { emit(LaunchListAction.Executing) }
    }

    companion object {

        val listLaunch = listOf(LaunchModel("test1", "23", "03", "testing", 1))
    }
}