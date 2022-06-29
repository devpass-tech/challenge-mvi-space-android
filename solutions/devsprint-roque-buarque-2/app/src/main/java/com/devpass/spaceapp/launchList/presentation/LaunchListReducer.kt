package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchListAction

object LaunchListReducer {

    operator fun invoke(
        action: LaunchListAction,
        currentState: LaunchListViewState
    ): LaunchListViewState {
        return when (action) {
            is LaunchListAction.Error -> currentState.copy(
                screenState = LaunchListScreenState.Message(
                    action.throwable.message
                )
            )
            LaunchListAction.Executing -> currentState.copy(
                screenState = LaunchListScreenState.Loading
            )
            is LaunchListAction.Success -> currentState.copy(
                launchList = action.launchList,
                screenState = LaunchListScreenState.DisplayLaunchList(action.launchList)
            )
        }
    }
}