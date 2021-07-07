package com.barros.listrepo.ui.home.data

import com.barros.listrepo.api.ApiService
import com.barros.listrepo.base.BaseRepository
import com.barros.listrepo.model.RepositoriesResponse
import com.barros.listrepo.utils.ResponseApi

class HomeRepositoryImpl : HomeRepository, BaseRepository() {
    override suspend fun getRepositories(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.repoAPi.getRepositories(currentPage = page)
        }.let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    response.data = response.data as? RepositoriesResponse
                    return@let response
                }
                is ResponseApi.Error -> return@let response
            }
        }
    }
}