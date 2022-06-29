package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchModel

data class LaunchListViewState (
    val launchList: List<LaunchModel>,
    val syncState: LaunchListSyncState
)

sealed class LaunchListSyncState {
    object Loading: LaunchListSyncState()
    object Content: LaunchListSyncState()
    data class Message(val msg: String) : LaunchListSyncState()
}
