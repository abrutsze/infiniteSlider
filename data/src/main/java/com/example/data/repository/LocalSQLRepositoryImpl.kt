package com.example.data.repository


import com.example.data.dataservice.sqlservice.DataListDto
import com.example.data.datastore.LocalSQLRepository
import com.example.entity.responcemodel.DataModelDb


class LocalSQLRepositoryImpl(
    private val dataListDto: DataListDto
) : LocalSQLRepository {
    override suspend fun addUpdateDataDB(items: List<DataModelDb>) =
        dataListDto.insertData(items)

    override fun geDataListDB(): List<DataModelDb>? = dataListDto.getDataList()

}