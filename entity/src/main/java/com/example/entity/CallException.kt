package com.example.entity

data class CallException<ErrorBody>(val errorCode: Int, val errorBody: ErrorBody? = null, val errorMessage:String?=null)