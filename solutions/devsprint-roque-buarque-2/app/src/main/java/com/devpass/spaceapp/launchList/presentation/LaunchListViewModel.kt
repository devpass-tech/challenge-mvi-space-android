package com.devpass.spaceapp.launchList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.R
import com.devpass.spaceapp.launchList.data.LaunchListRepository
import com.devpass.spaceapp.launchList.data.LaunchListRepositoryImpl
import com.devpass.spaceapp.repository.FetchLaunchesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
class LaunchListViewModel(
    private val repository: LaunchListRepository,
    private val store: LaunchListStore
) : ViewModel() {

    private val viewEvent = MutableSharedFlow<LaunchListEvent>()

    val viewState: Flow<LaunchListScreenState> =
        viewEvent
            .flatMapMerge {
                when (it) {
                    is LaunchListEvent.FetchLaunchList -> {
                        repository.fetchLaunchList()
                    }
                }
            }
            .map {
                store.invoke(it)
            }

    fun event(launchListEvent: LaunchListEvent) {
        viewModelScope.launch {
            viewEvent.emit(launchListEvent)
        }
    }

    sealed class LaunchListEvent {
        object FetchLaunchList : LaunchListEvent()
    }

    companion object {

        fun newInstance(owner: ViewModelStoreOwner): LaunchListViewModel {
            return ViewModelProvider(
                owner,
                LaunchListViewModelFactory()
            )[LaunchListViewModel::class.java]
        }
    }

    class LaunchListViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(LaunchListViewModel::class.java)) {
                LaunchListViewModel(
                    repository = LaunchListRepositoryImpl(),
                    store = LaunchListStore(LaunchListReducer)
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}