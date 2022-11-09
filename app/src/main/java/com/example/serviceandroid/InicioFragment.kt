package com.example.serviceandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.serviceandroid.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    private val texto by lazy {
        (arguments?.getString("TEXTO"))
    }

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTexto.text = texto

        binding.btnNext.setOnClickListener {
            goSecondFragment()
        }
    }

    private fun goSecondFragment() {
        findNavController().navigate(R.id.secondFragment)
    }
}