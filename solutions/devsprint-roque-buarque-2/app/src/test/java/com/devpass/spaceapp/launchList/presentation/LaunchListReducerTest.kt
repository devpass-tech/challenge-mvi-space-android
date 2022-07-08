package com.devpass.spaceapp.launchList.presentation

import com.devpass.spaceapp.launchList.data.LaunchListAction
import com.devpass.spaceapp.launchList.data.LaunchModel
import com.devpass.spaceapp.launchList.presentation.LaunchListViewModel.LaunchListEvent
import com.devpass.spaceapp.launchList.presentation.LaunchListViewModel.LaunchListEvent.FetchLaunchList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class LaunchListReducerTest {

    private val state = LaunchListViewState()

    @Test
    fun `GIVEN initial state WHEN invoke THEN return loading`() {
        val reducer = LaunchListReducer

        val result = reducer.invoke(action = LaunchListAction.Executing, currentState = state)

        val expected = LaunchListViewState(emptyList(), LaunchListScreenState.Loading)
        assert(result == expected)
    }

    @Test
    fun `GIVEN initial state WHEN invoke THEN return error`() {
        val reducer = LaunchListReducer

        val result =
            reducer.invoke(action = LaunchListAction.Error(Throwable()), currentState = state)

        val excepted = LaunchListViewState(emptyList(), LaunchListScreenState.Loading)
        assert(result == excepted)
    }

    @Test
    fun `GIVEN initial state WHEN invoke THEN return success empty list`() {
        val reducer = LaunchListReducer

        val result =
            reducer.invoke(action = LaunchListAction.Success(emptyList()), currentState = state)

        val excepted = LaunchListViewState(
            emptyList(), LaunchListScreenState.DisplayLaunchList(
                emptyList()
            )
        )
        assert(result == excepted)
    }

    @Test
    fun `GIVEN initial state WHEN invoke THEN return success`() {
        val reducer = LaunchListReducer

        val launchList = arrayListOf<LaunchModel>()

        val result =
            reducer.invoke(action = LaunchListAction.Success(launchList), currentState = state)

        val excepted = LaunchListViewState(emptyList(), LaunchListScreenState.DisplayLaunchList(launchList))
        assert(result == excepted)
    }

    @Test
    fun `Testing Flow`() {
        runBlocking {
            val stateFlow = MutableSharedFlow<String>()

            stateFlow.emit("1")
            stateFlow.emit("2")

            val list = stateFlow.take(1).toList()

            println("test ${list[0]}")
            println("test ${list[1]}")
        }
    }
}