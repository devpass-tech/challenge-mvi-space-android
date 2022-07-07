package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchPadAction

class LaunchPadStore(
    private val reducer: LaunchPadReducer
) {

    private var state = LaunchPadViewState()

    operator fun invoke(action: LaunchPadAction): LaunchPadScreenState {
        state = reducer(action, state)
        return state.screenState
    }
}