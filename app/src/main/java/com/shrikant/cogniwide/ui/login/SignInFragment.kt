package com.shrikant.cogniwide.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.network.shared.core.result.EventObserver
import com.network.shared.util.viewModelProvider
import com.shrikant.cogniwide.base.BaseFragment
import com.shrikant.cogniwide.databinding.FragmentLoginBinding
import com.shrikant.cogniwide.ui.movie.DashBoardActivity
import com.shrikant.cogniwide.utils.hideSoftKeyBoard
import javax.inject.Inject


class SignInFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: SignInViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginFormState.observe(viewLifecycleOwner, EventObserver { loginState ->
            binding.login.isEnabled = loginState.isDataValid
        })

        binding.password.doAfterTextChanged {
            checkValidation()
        }
        binding.username.doAfterTextChanged {
            checkValidation()
        }

        binding.login.setOnClickListener {
            requireActivity().hideSoftKeyBoard()
            startActivity(Intent(requireContext(), DashBoardActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun checkValidation(): Unit {
        viewModel.validateData(
            binding.username.text.toString(),
            binding.password.text.toString()
        )
    }


}
