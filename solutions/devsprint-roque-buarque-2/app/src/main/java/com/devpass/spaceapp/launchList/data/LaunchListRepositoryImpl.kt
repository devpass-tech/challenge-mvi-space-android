package com.devpass.spaceapp.launchList.data

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.launchList.data.model.DocsItem
import com.devpass.spaceapp.launchList.data.model.LaunchPad
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

    override suspend fun getLaunchPadDetails(launchId: String): Flow<LaunchPadAction> {
        return flow<LaunchPadAction> {
            val launchDetail = service.fetchLaunchpadDetails(launchId).toLaunchPadModel()
            emit(
                LaunchPadAction.Success(
                    launchDetail = launchDetail
                )
            )
        }
            .catch { error -> emit(LaunchPadAction.Error(error)) }
            .onStart { emit(LaunchPadAction.Executing) }
    }
}

fun LaunchPad.toLaunchPadModel(): LaunchPadModel =
    LaunchPadModel(
        name = name,
        localy = locality,
        region = region
    )

fun DocsItem.toLaunchModel(): LaunchModel =
    LaunchModel(
        name = name,
        number = flightNumber.toString(),
        date = dateLocal,
        status = success.toString(),
        image = links.patch.small,
        id = launchpad
    )
