package com.barros.listrepo.utils

import com.barros.listrepo.api.ApiError
import com.barros.listrepo.api.ApiService
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>): ApiError? {
        val converter: Converter<ResponseBody, ApiError> = ApiService.getRepoApiClient()
            .responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))

        var error: ApiError? = null

        try {
            response.errorBody()?.let { errorBody ->
                error = converter.convert(errorBody)
            }
        } catch (e: IOException) {
            return ApiError
        }

        return error
    }
}