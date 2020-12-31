package com.example.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.responcemodel.DataModelDb

@Dao
interface DataListDto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item: List<DataModelDb>)

    @Query("SELECT * FROM dataModel")
    fun getDataList(): List<DataModelDb>?

}