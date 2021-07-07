package com.barros.listrepo.ui.home.domain

import com.barros.listrepo.model.RepositoriesResponse
import com.barros.listrepo.model.Repository
import com.barros.listrepo.ui.home.data.HomeRepository
import com.barros.listrepo.utils.ResponseApi

class HomeUseCase(
    private val homeRepository: HomeRepository
) {
    fun setupMoviesList(list: RepositoriesResponse?): List<Repository> {
        val repository = list?.items

        return repository ?: listOf()
    }

    suspend fun loadRepository(currentPage: Int): ResponseApi {
        return homeRepository.getRepositories(currentPage)
    }
}