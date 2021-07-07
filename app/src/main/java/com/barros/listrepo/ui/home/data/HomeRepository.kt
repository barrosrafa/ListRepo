package com.barros.listrepo.ui.home.data

import com.barros.listrepo.utils.ResponseApi

interface HomeRepository {

    suspend fun getRepositories(page: Int): ResponseApi
}