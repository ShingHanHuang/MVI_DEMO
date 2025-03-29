package com.example.mvi_demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi_demo.adapter.CarParkInfoAdapter
import com.example.mvi_demo.databinding.FragmentCarkParkInfoBinding
import com.example.mvi_demo.intent.CarParkInfoEffect
import com.example.mvi_demo.viewmodel.CarParkInfoViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CarParkInfoFragment :
    BaseFragment<FragmentCarkParkInfoBinding>(FragmentCarkParkInfoBinding::inflate) {

    private val carParkInfoViewModel: CarParkInfoViewModel by viewModel()


    private val carParkInfoAdapter: CarParkInfoAdapter by inject { parametersOf(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        registerEvent()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(context)
        binding.carParkRv.layoutManager = layoutManager
        binding.carParkRv.adapter = carParkInfoAdapter
    }

    private fun registerEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                carParkInfoViewModel.uiStateFlow
                    .collect { state ->
                        state.carParkList?.let { carParkInfoAdapter.setCarParkInfoList(it) }
                    }
            }
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                carParkInfoViewModel.uiEffectFlow.collect {
                    when (it) {
                        is CarParkInfoEffect.ShowToast -> {
                            Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                        }

                        is CarParkInfoEffect.ErrorMessage -> {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

}