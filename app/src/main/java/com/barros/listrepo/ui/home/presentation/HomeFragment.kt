package com.barros.listrepo.ui.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.barros.listrepo.R
import com.barros.listrepo.base.BaseFragment
import com.barros.listrepo.databinding.FragmentHomeBinding
import com.barros.listrepo.ui.home.adapter.HomeAdapter
import com.barros.listrepo.utils.Command
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.barros.listrepo.ui.home.adapter.RepositoryAdapterCallback
import com.barros.listrepo.utils.subscribe

class HomeFragment : BaseFragment() {

    private var homeBinding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val homAdapter by lazy {
        HomeAdapter(::onRepositoryAdapterCallback)
    }

    private fun onRepositoryAdapterCallback(repositoryAdapterCallback: RepositoryAdapterCallback) =
        repositoryAdapterCallback.run {
            when (this) {
                RepositoryAdapterCallback.OnNextPage -> homeViewModel.nextPage()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.command = command

        setupObservables()
        setupRecyclerView()
        homeViewModel.getRepositories()
    }

    private fun setupObservables() {

        homeViewModel.command.observe(viewLifecycleOwner) {
            when (it) {
                is Command.Loading -> {
                    if (it.value) showLoading()
                    else hideLoading()
                }
                is Command.Error -> {
                    homeBinding?.rvHomeRepositoryList?.let { view ->
                        Snackbar.make(view, getString(it.error), Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        homeViewModel.successResponseEvent.subscribe(this) {
            homAdapter.update(this.data)
        }
    }

    private fun setupRecyclerView() {
        homeBinding?.rvHomeRepositoryList?.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = homAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}