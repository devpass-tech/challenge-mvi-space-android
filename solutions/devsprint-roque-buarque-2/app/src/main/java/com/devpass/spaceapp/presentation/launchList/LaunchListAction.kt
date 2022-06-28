package com.devpass.spaceapp.presentation.launchList

sealed class LaunchListAction {

    object Loading : LaunchListAction()
    data class Success(val launchList: List<LaunchModel> ) : LaunchListAction()
    data class Error(val throwable: Throwable) : LaunchListAction()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            is Success -> "Success[data=$launchList]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}
