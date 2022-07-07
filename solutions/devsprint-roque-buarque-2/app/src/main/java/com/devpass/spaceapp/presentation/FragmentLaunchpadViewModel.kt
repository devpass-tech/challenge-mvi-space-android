package com.devpass.spaceapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.launchList.data.LaunchListRepository
import com.devpass.spaceapp.launchList.data.LaunchListRepositoryImpl
import com.devpass.spaceapp.launchList.presentation.LaunchPadReducer
import com.devpass.spaceapp.launchList.presentation.LaunchPadScreenState
import com.devpass.spaceapp.launchList.presentation.LaunchPadStore
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
class FragmentLaunchpadViewModel(
    private val repository: LaunchListRepository,
    private val store: LaunchPadStore
) : ViewModel() {

    private val viewEvent = MutableSharedFlow<LaunchpadEvent>()
    val viewState: Flow<LaunchPadScreenState> =
        viewEvent
            .flatMapMerge {
                when (it) {
                    is LaunchpadEvent.FetchLaunchpad -> {
                        repository.getLaunchPadDetails(it.launchId)
                    }
                }
            }
            .map {
                store.invoke(it)
            }
            .catch {
                println()
            }

    fun event(launchpadEvent: LaunchpadEvent) {
        viewModelScope.launch {
            viewEvent.emit(launchpadEvent)
        }
    }

    sealed class LaunchpadEvent {
        data class FetchLaunchpad(val launchId: String) : LaunchpadEvent()
    }

    companion object {

        fun newInstance(owner: ViewModelStoreOwner): FragmentLaunchpadViewModel {
            return ViewModelProvider(
                owner,
                LaunchpadViewModelFactory()
            )[FragmentLaunchpadViewModel::class.java]
        }
    }

    class LaunchpadViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(FragmentLaunchpadViewModel::class.java)) {
                FragmentLaunchpadViewModel(
                    repository = LaunchListRepositoryImpl(),
                    store = LaunchPadStore(LaunchPadReducer)
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}