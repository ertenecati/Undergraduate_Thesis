package com.ertenecati.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ertenecati.MainViewModel
import com.ertenecati.R
import com.ertenecati.databinding.FragmentButtonBinding

class ButtonFragment : Fragment(R.layout.fragment_button) {

    private lateinit var binding: FragmentButtonBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnForward.setOnClickListener {
            viewModel.sendCommand("2")
        }
        binding.btnLeft.setOnClickListener {
            viewModel.sendCommand("4")
        }
        binding.btnStop.setOnClickListener {
            viewModel.sendCommand("5")
        }
        binding.btnRight.setOnClickListener {
            viewModel.sendCommand("6")
        }
        binding.btnBackward.setOnClickListener {
            viewModel.sendCommand("8")
        }
    }

}