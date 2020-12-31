package com.example.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entity.localmodels.DataModel
import com.example.entity.responcemodel.DataModelDb

@Database(
    entities = [DataModelDb::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonListDao():DataListDto
}