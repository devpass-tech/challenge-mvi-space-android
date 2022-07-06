package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchPadAction

object LaunchPadReducer {

    operator fun invoke(
        action: LaunchPadAction,
        currentState: LaunchPadViewState
    ): LaunchPadViewState {
        return when (action) {
            is LaunchPadAction.Error -> currentState.copy(
                screenState = LaunchPadScreenState.Message(
                    action.throwable.message
                )
            )
            LaunchPadAction.Executing -> currentState.copy(
                screenState = LaunchPadScreenState.Loading
            )
            is LaunchPadAction.Success -> currentState.copy(
                launchPadDetail = action.launchDetail,
                screenState = LaunchPadScreenState.DisplayLaunchPadDetail(action.launchDetail)
            )
        }
    }
}