package com.devpass.spaceapp.presentation.launchList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaunchListViewModel: ViewModel() {

    private val _viewState = MutableStateFlow<LaunchListViewState>(LaunchListViewState.ShowLoading)
    val viewState: StateFlow<LaunchListViewState> = _viewState

    fun event(launchListEvent: LaunchListEvent) {
        when(launchListEvent) {
            is LaunchListEvent.FetchLaunchList -> fetchLaunchList()
            is LaunchListEvent.OnItemClicked -> handleItemClicked(launchListEvent.itemClicked)
        }
    }

    private fun handleItemClicked(itemClicked: LaunchModel) {
        //TODO Navigation to details
        _viewState.value = LaunchListViewState.ItemClicked(
            itemClicked = itemClicked
        )
    }

    private fun fetchLaunchList() {
        val launch1 = LaunchModel("Launch 1","1", "July 03, 2020", "Success", R.drawable.crs)
        val launch2 = LaunchModel("Launch 2","2", "July 03, 2020", "Success", R.drawable.falcon_sat)
        val launch3 = LaunchModel("Launch 3","3", "July 03, 2020", "Success", R.drawable.starlink)
        val launch4 = LaunchModel("Launch 4","4", "July 03, 2020", "Success", R.drawable.spacex_dragon_crs20_patch01)
        val launch5 = LaunchModel("Launch 5","5", "July 03, 2020", "Success", R.drawable.starlink)
        val launchList = listOf(launch1, launch2, launch3, launch4, launch5)

        viewModelScope.launch {
            _viewState.value = LaunchListViewState.ShowLoading
            delay(2000)
            _viewState.value = LaunchListViewState.LaunchList(
                list = launchList
            )
            _viewState.value = LaunchListViewState.HideLoading
        }
    }

    sealed class LaunchListEvent {
        object FetchLaunchList: LaunchListEvent()
        data class OnItemClicked(val itemClicked: LaunchModel): LaunchListEvent()
    }

    sealed class LaunchListViewState {
        object ShowLoading: LaunchListViewState()
        object HideLoading: LaunchListViewState()
        data class LaunchList(val list: List<LaunchModel>): LaunchListViewState()
        data class ItemClicked(val itemClicked: LaunchModel): LaunchListViewState()
    }
}