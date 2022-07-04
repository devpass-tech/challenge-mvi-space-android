package com.devpass.spaceapp.launchList.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.launchList.data.LaunchModel
import com.devpass.spaceapp.presentation.launch.LaunchActivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter
    private val viewModel: LaunchListViewModel by lazy { LaunchListViewModel.newInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        prepareObservers()
        viewModel.event(LaunchListViewModel.LaunchListEvent.FetchLaunchList)
    }

    private fun prepareObservers() {
        lifecycleScope.launch {
            viewModel.viewState.collect { uiState ->
                when(uiState) {
                    is LaunchListScreenState.Loading -> showLoading()
                    is LaunchListScreenState.DisplayLaunchList-> initLaunchList(uiState.launchList)
                    is LaunchListScreenState.Message -> print(uiState.msg)
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
        hideLoading()
        adapter.submitList(launchList)
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter(::intentItemClick)
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }

    private fun intentItemClick(listItem: LaunchModel) {
        val intent = Intent(this, LaunchActivity::class.java)
        intent.putExtra("LAUNCH", listItem)
        startActivity(intent)
    }
}