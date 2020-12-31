package com.example.domian.usecase

import com.example.data.datastore.GetDataRepository
import com.example.data.datastore.LocalSQLRepository
import com.example.domian.interactors.GetDataInteractor
import com.example.domian.utils.fromDataDbToData
import com.example.domian.utils.fromDataToDataDb
import com.example.domian.utils.toLocalData
import com.example.entity.CallException
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Result
import com.example.entity.localmodels.DataModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow

class GetDataUseCase(
    private val getDataRepository: GetDataRepository,
    private val localSQLRepository: LocalSQLRepository
) :
    GetDataInteractor {

    override suspend fun getDataResponse() = channelFlow<Result<List<DataModel>>> {
        val dataListFromDb = localSQLRepository.geDataListDB()
        dataListFromDb?.let {
            channel.offer(Result.Success(it.map {
                it.fromDataDbToData()
            }))
        }

        when (val apiData = getDataRepository.getDataListResponse()) {
            is Result.Success -> {
                apiData.data?.let { list ->
                    val mappingList = list.map { it.toLocalData() }
                    val mappingListDb = list.map { it.fromDataToDataDb() }
                    localSQLRepository.addUpdateDataDB(mappingListDb)
                    channel.offer(Result.Success(mappingList))
                } ?:  channel.offer(Result.Error(
                    CallException(
                        ERROR_NULL_DATA,
                        null,
                        "Can't load data into server"
                    ))
                )
            }
            else -> {
                channel.offer(
                    Result.Error(
                        CallException(
                            ERROR_NULL_DATA,
                            null,
                            "Can't load data into server"
                        )
                    )
                )
            }
        }
        awaitClose {}
    }

}