package com.example.serviceandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.serviceandroid.databinding.FragmentThredBinding


class ThredFragment : Fragment() {

    private var _binding: FragmentThredBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnDetail.setOnClickListener {
            findNavController().navigate(R.id.action_thredFragment_to_detailRecepcionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}