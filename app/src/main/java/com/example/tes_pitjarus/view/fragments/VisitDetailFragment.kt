package com.example.tes_pitjarus.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.tes_pitjarus.R
import com.example.tes_pitjarus.databinding.FragmentVisitDetailBinding
import com.example.tes_pitjarus.viewmodels.MainViewModel
import com.google.android.material.transition.MaterialFadeThrough
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VisitDetailFragment : Fragment() {
    private var _binding : FragmentVisitDetailBinding ?=null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVisitDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initActions()
    }

    private fun initUI(){
        binding.apply {
            val data = mainViewModel.selectedStore.value
            tvStoreId.text = data?.store_id
            tvStoreName.text = data?.store_name
            layoutMenu.iconKunjungan.apply {
                setImageResource(R.drawable.ic_info)
            }
            layoutMenu.iconTarget.apply {
                setImageResource(R.drawable.ic_product_check)
            }
            layoutMenu.iconDashboard.apply {
                setImageResource(R.drawable.ic_shopping_cart)
            }
            layoutMenu.iconHistory.apply {
                setImageResource(R.drawable.ic_oos)
            }
            layoutMenu.iconLogout.apply {
                setImageResource(R.drawable.ic_store_investment)
            }
            layoutMenu.tvKunjungan.text = "Information"
            layoutMenu.tvTarget.text = "Product\nCheck"
            layoutMenu.tvDashboard.text = "SKU\nPromo"
            layoutMenu.tvHistory.text = "Ringkasan\nOOS"
            layoutMenu.tvLogout.text = "Store\nInvestment"
        }
    }

    private fun initActions(){
        binding.btnFinish.setOnClickListener {
            val direction = VisitDetailFragmentDirections.actionVisitDetailFragmentToVisitationFragment()
            findNavController().navigate(direction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}