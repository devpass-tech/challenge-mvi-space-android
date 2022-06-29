package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchModel

data class LaunchListViewState (
    val launchList: List<LaunchModel>,
    val syncState: LaunchListScreenState
)

sealed class LaunchListScreenState {
    object Loading: LaunchListScreenState()
    data class DisplayLaunchList (val launchList: List<LaunchModel>) : LaunchListScreenState()
    data class Message(val msg: String) : LaunchListScreenState()
}
