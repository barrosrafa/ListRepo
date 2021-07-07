package com.barros.listrepo.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.barros.listrepo.base.BaseViewModel
import com.barros.listrepo.model.RepositoriesResponse
import com.barros.listrepo.model.Repository
import com.barros.listrepo.ui.home.data.HomeDataSourceFactory
import com.barros.listrepo.ui.home.domain.HomeUseCase
import com.barros.listrepo.utils.Constants.Paging.FIRST_PAGE
import com.barros.listrepo.utils.Constants.Paging.ZERO
import com.barros.listrepo.utils.Event
import com.barros.listrepo.utils.SimpleEvent
import com.barros.listrepo.utils.triggerEvent
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    homeDataSourceFactory: HomeDataSourceFactory
) : BaseViewModel() {

    private var currentList: List<Repository> = emptyList()
    private var currentPage: Int = FIRST_PAGE
    private var totalResult: Int = ZERO
    private val _fullResultResponseEvent = MutableLiveData<SimpleEvent>()

    private val _successResponseEvent = MutableLiveData<Event<List<Repository>>>()
    val successResponseEvent: LiveData<Event<List<Repository>>>
        get() = _successResponseEvent

    fun getRepositories(currentPage: Int = FIRST_PAGE, isScrolling: Boolean = false) {
        viewModelScope.launch {
            callApi(
                call = suspend { homeUseCase.loadRepository(currentPage) },
                onSuccess = {
                    _successResponseEvent.triggerEvent((it as RepositoriesResponse).items)
                },
                onError = {
                    _successResponseEvent.triggerEvent(currentList)
                }
            )
        }
    }

    fun nextPage(samePage: Boolean = false) {
        if (currentList.size < totalResult) {
            if (!samePage) currentPage++
            getRepositories(currentPage, true)
        } else {
            _fullResultResponseEvent.triggerEvent()
        }
    }
}