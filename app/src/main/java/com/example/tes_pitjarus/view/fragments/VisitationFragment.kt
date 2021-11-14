package com.example.tes_pitjarus.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import com.example.tes_pitjarus.databinding.FragmentVisitationBinding
import com.example.tes_pitjarus.utils.*
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.view.adapter.StoresAdapter
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VisitationFragment : Fragment(), StoresAdapter.OnStoreClicked {
    private var _binding: FragmentVisitationBinding?=null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var storeAdapter: StoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVisitationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        initObservable()
        mainViewModel.getStores()
    }

    private fun initRV(){
        storeAdapter = StoresAdapter(listener = this)

        binding.rvListKunjungan.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
            adapter = storeAdapter
        }
    }

    override fun onStoreClicked(data: Stores) {
        Toast.makeText(requireContext(), data.store_name, Toast.LENGTH_SHORT).show()
    }

    private fun initObservable(){
        mainViewModel.storeData.observe(viewLifecycleOwner, {
            when(it){
                is ResultWrapper.Empty -> {
                    binding.msvListKunjungan.showEmptyState()
                }
                is ResultWrapper.Failure -> {
                    binding.msvListKunjungan.showErrorState(it.message, it.title){
                        mainViewModel.getStores()
                    }
                }
                is ResultWrapper.Loading -> {
                    binding.msvListKunjungan.showLoadingState()
                }
                is ResultWrapper.Success -> {
                    binding.msvListKunjungan.showDefaultState()
                    storeAdapter.setStoresData(it.data)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}