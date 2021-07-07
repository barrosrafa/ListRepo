package com.barros.listrepo.api

import com.barros.listrepo.model.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoAPI {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sortBy: String = "stars",
        @Query("page") currentPage: Int
    ): Response<RepositoriesResponse>
}