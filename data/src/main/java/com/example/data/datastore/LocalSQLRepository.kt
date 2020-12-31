package com.example.data.datastore

import com.example.entity.responcemodel.DataModelDb

interface LocalSQLRepository {
    suspend fun addUpdateDataDB(items: List<DataModelDb>)
    fun geDataListDB(): List<DataModelDb>?
}