package com.example.entity.responcemodel

import com.squareup.moshi.Json

data class DataResponse(
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "url")
    var url: String? = null
)