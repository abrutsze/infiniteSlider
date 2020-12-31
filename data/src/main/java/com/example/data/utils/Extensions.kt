package com.example.data.utils

import java.lang.Exception
import com.example.entity.CallException
import retrofit2.Response
import com.example.entity.Result
suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    Result.Error(CallException<Nothing>(errorMessage))
}

fun <R> analyzeResponse(response: Response<R>): Result<R> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            Result.Success(responseBody)
        }
        else -> {
            Result.Error(CallException<Nothing>(response.code()))
        }
    }
}
