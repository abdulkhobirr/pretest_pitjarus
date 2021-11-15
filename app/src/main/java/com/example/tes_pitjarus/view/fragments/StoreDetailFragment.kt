package com.example.tes_pitjarus.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tes_pitjarus.R
import com.example.tes_pitjarus.databinding.FragmentStoreDetailBinding
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StoreDetailFragment : Fragment() {
    private var _binding : FragmentStoreDetailBinding ?= null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initActions()
    }

    private fun initUI(){
        val data = mainViewModel.selectedStore.value
        binding.apply {
            tvStoreName.text = data?.store_name
            tvStoreAddress.text = data?.address
        }
    }

    private fun initActions(){
        binding.apply {
            btnNoVisit.setOnClickListener {

            }
            btnVisit.setOnClickListener {
                val direction = StoreDetailFragmentDirections.actionStoreDetailFragmentToVisitDetailFragment()
                findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}