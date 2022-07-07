package com.devpass.spaceapp.launchList.data

sealed class LaunchPadAction {

    object Executing : LaunchPadAction()
    data class Success(val launchDetail: LaunchPadModel ) : LaunchPadAction()
    data class Error(val throwable: Throwable) : LaunchPadAction()

    override fun toString(): String {
        return when (this) {
            Executing -> "Executing"
            is Success -> "Success[data=$launchDetail]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}
