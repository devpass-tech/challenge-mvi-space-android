package com.devpass.spaceapp.launchList.data

import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.launchList.data.model.Launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class LaunchListRepositoryImpl (
    private val service: SpaceXAPIService = Network.createSpaceXService()
) : LaunchListRepository {

    override suspend fun fetchLaunchList(): Flow<LaunchListAction> {
        return flow<LaunchListAction> {
            //val list = launchList
            val list = service.fetchNextLaunches().map {
                it.toLaunchModel()
            }
            emit(LaunchListAction.Success(
                launchList = list
            ))
        }
            .catch { error -> emit(LaunchListAction.Error(error)) }
            .onStart { emit(LaunchListAction.Executing) }
    }

    companion object {
        private val launch1 = LaunchModel("Launch 1","1", "July 03, 2020", "Success", R.drawable.crs)
        private val launch2 = LaunchModel("Launch 2","2", "July 03, 2020", "Success", R.drawable.falcon_sat)
        private val launch3 = LaunchModel("Launch 3","3", "July 03, 2020", "Success", R.drawable.starlink)
        private val launch4 = LaunchModel("Launch 4","4", "July 03, 2020", "Success", R.drawable.spacex_dragon_crs20_patch01)
        private val launch5 = LaunchModel("Launch 5","5", "July 03, 2020", "Success", R.drawable.starlink)
        val launchList = listOf(launch1, launch2, launch3, launch4, launch5)
    }
}

fun Launch.toLaunchModel(): LaunchModel =
    LaunchModel(
        name = name,
        number = stages.toString(),
        date = firstFlight,
        status = successRatePct.toString(),
        R.drawable.falcon_sat
    )
