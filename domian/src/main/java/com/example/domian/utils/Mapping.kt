package com.example.domian.utils


import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun DataResponse.toLocalData(): DataModel {
    return DataModel(
        title = title,
        url = url
    )
}
fun DataModelDb.fromDataDbToData(): DataModel {
    return DataModel(
        title = title,
        url = url
    )
}
fun DataResponse.fromDataToDataDb(): DataModelDb {
    return DataModelDb(
        title = title,
        url = url
    )
}



