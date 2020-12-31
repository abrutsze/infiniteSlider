package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.DataModel
import kotlinx.coroutines.flow.Flow


interface GetDataInteractor {
  suspend  fun getDataResponse(): Flow<Result<List<DataModel>>>
}