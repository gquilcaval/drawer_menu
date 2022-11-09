package com.example.serviceandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.serviceandroid.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private val arguments by lazy {
        getArguments()?.getString("TEXT")
    }

    private var _binding: FragmentSecondBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.tvText?.text = arguments.toString()

        _binding?.btnNext?.setOnClickListener {
            findNavController().navigate(R.id.detailSecondFragment)
        }
    }


}