package com.example.mvi_demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvi_demo.R
import com.example.mvi_demo.databinding.FragmentLoginBinding
import com.example.mvi_demo.intent.LoginEffect
import com.example.mvi_demo.intent.LoginIntent
import com.example.mvi_demo.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.findNavController


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            loginViewModel.sendUiIntent(
                LoginIntent.Login(
                    binding.accountEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            )
        }
        registerEvent()
    }

    private fun registerEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiStateFlow
                    .collect { state ->

                    }
            }
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiEffectFlow.collect {
                    when (it) {
                        is LoginEffect.ShowToast -> {
                            Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
                        }

                        is LoginEffect.ErrorMessage -> {
                            Toast.makeText(context, it.error.toString(), Toast.LENGTH_SHORT).show()
                        }

                        LoginEffect.NavigationToSchoolDetail -> {
                            binding.loginBtn.findNavController()
                                .navigate(R.id.action_loginFragment_to_carkParkInfoFragment)
                        }
                    }
                }
            }
        }
    }
}