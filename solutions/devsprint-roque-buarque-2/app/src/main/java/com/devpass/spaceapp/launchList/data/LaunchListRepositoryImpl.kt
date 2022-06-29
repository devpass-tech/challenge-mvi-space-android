package com.devpass.spaceapp.launchList.data

import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.launchList.presentation.LaunchListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LaunchListRepositoryImpl (
    private val service: SpaceXAPIService
) : LaunchListRepository {

    override fun fetchLaunchList(): Flow<LaunchListAction> {
        return flow<LaunchListAction> {
            // TODO service network request
            val list = launchList
            emit(LaunchListAction.Success(list))
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