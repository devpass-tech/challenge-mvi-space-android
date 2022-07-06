package com.devpass.spaceapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devpass.spaceapp.databinding.FragmentLaunchpadBinding
import com.devpass.spaceapp.launchList.data.LaunchPadModel
import com.devpass.spaceapp.launchList.presentation.LaunchPadScreenState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
class FragmentLaunchpad : Fragment() {

    private val launchpadId by lazy {
        arguments?.getString(LAUNCHPAD_ID)
    }

    private var binding: FragmentLaunchpadBinding? = null
    private val viewModel: FragmentLaunchpadViewModel by lazy {
        FragmentLaunchpadViewModel.newInstance(
            this@FragmentLaunchpad
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaunchpadBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareObservable()
        launchpadId?.let {
            viewModel.event(FragmentLaunchpadViewModel.LaunchpadEvent.FetchLaunchpad(it))
        }
    }

    private fun prepareObservable() {
        lifecycleScope.launch {
            viewModel.viewState.collect { uiState ->
                when(uiState) {
                    LaunchPadScreenState.Loading -> showLoading()
                    is LaunchPadScreenState.DisplayLaunchPadDetail -> displayInformations(uiState.launchPadDetail)
                }
            }
        }
    }

    private fun showLoading() {
        binding?.let {
            it.cardViewLaunchpad.visibility = View.GONE
            it.progress.visibility = View.VISIBLE
        }
    }

    private fun displayInformations(data: LaunchPadModel) {
        binding?.let {
            it.cardViewLaunchpad.visibility = View.VISIBLE
            it.progress.visibility = View.GONE
            it.tvTittleCardLaunchpad.text = data.name
            it.tvTypeLaunchpad.text = data.localy
            it.tvStateLaunchpad.text = data.region
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object {
        const val LAUNCHPAD_ID = "launchId"
        fun newInstance(launchpadId: String) : FragmentLaunchpad {
            val bundle = Bundle().apply {
                putString(LAUNCHPAD_ID, launchpadId)
            }
            val fragment = FragmentLaunchpad()
            fragment.arguments = bundle
            return fragment
        }
    }
}