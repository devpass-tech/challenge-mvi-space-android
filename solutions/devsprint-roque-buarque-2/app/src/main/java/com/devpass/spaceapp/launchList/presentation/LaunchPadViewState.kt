package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchPadModel

data class LaunchPadViewState(
    val launchPadDetail: LaunchPadModel = LaunchPadModel(),
    val screenState: LaunchPadScreenState = LaunchPadScreenState.Loading
)

sealed class LaunchPadScreenState {
    object Loading: LaunchPadScreenState()
    data class DisplayLaunchPadDetail (val launchPadDetail: LaunchPadModel) : LaunchPadScreenState()
    data class Message(val msg: String?) : LaunchPadScreenState()
}