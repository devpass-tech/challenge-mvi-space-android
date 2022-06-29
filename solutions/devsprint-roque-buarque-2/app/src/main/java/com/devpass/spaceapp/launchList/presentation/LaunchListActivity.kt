package com.devpass.spaceapp.launchList.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.launchList.data.LaunchModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter
    private val viewModel: LaunchListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        viewModel.event(LaunchListViewModel.LaunchListEvent.FetchLaunchList)
        prepareObservers()
    }

    private fun prepareObservers() {
        lifecycleScope.launch {
            viewModel.viewState.collect { uiState ->
                when(uiState) {
                    is LaunchListViewModel.LaunchListViewState.ShowLoading -> showLoading()
                    is LaunchListViewModel.LaunchListViewState.HideLoading -> hideLoading()
                    is LaunchListViewModel.LaunchListViewState.LaunchList -> initLaunchList(uiState.list)
                    is LaunchListViewModel.LaunchListViewState.ItemClicked -> onItemClicked(uiState.itemClicked)
                }
            }
        }
    }

    private fun showLoading() {
        binding.pbLaunches.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLaunches.visibility = View.GONE
    }

    private fun initLaunchList(launchList: List<LaunchModel>) {
        viewModel.event(LaunchListViewModel.LaunchListEvent.FetchLaunchList)
        adapter.submitList(launchList)
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter(::intentItemClick)
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }

    private fun intentItemClick(listItem: LaunchModel) {
        viewModel.event(LaunchListViewModel.LaunchListEvent.OnItemClicked(itemClicked = listItem))
    }

    private fun onItemClicked(itemList: LaunchModel) {
        Toast.makeText(this, "Clicou em ${itemList.name}", Toast.LENGTH_LONG).show()
    }
}