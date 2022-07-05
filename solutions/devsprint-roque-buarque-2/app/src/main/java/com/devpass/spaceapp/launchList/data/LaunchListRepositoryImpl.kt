package com.devpass.spaceapp.launchList.data

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.launchList.data.model.DocsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class LaunchListRepositoryImpl(
    private val service: SpaceXAPIService = Network.createService()
) : LaunchListRepository {

    override suspend fun fetchLaunchList(): Flow<LaunchListAction> {
        return flow<LaunchListAction> {
            val list = service.fetchNextLaunches().docs?.map {
                it.toLaunchModel()
            }
            list?.let {
                LaunchListAction.Success(
                    launchList = it
                )
            }?.let {
                emit(
                    it
                )
            }
        }
            .catch { error -> emit(LaunchListAction.Error(error)) }
            .onStart { emit(LaunchListAction.Executing) }
    }
}

fun DocsItem.toLaunchModel(): LaunchModel =
    LaunchModel(
        name = name,
        number = flightNumber.toString(),
        date = dateLocal,
        status = success.toString(),
        image = links.patch.small
    )
