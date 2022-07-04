package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchListAction

class LaunchListStore(
    private val reducer: LaunchListReducer
) {

    private var state = LaunchListViewState()

    operator fun invoke(action: LaunchListAction): LaunchListScreenState {
        state = reducer(action, state)
        return state.screenState
    }
}
