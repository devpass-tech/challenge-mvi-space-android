package com.devpass.spaceapp.launchList.data

sealed class LaunchListAction {

    object Executing : LaunchListAction()
    data class Success(val launchList: List<LaunchModel> ) : LaunchListAction()
    data class Error(val throwable: Throwable) : LaunchListAction()

    override fun toString(): String {
        return when (this) {
            Executing -> "Executing"
            is Success -> "Success[data=$launchList]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}
