package com.example.tes_pitjarus.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.tes_pitjarus.databinding.FragmentMainBinding
import com.example.tes_pitjarus.utils.pref.PrefManager
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.view.LoginActivity
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding?=null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()
    private val pref: PrefManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
        initObservable()
    }

    private fun initActions(){
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }
            })

        binding.layoutMenu.llMenuKunjungan.setOnClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToVisitationFragment()
            findNavController().navigate(direction)
        }
        binding.layoutMenu.llMenuTarget.setOnClickListener {  }
        binding.layoutMenu.llMenuDashboard.setOnClickListener {  }
        binding.layoutMenu.llMenuHistory.setOnClickListener {  }
        binding.layoutMenu.llMenuLogout.setOnClickListener {
            mainViewModel.clearTable()
        }
    }

    private fun initObservable(){
        mainViewModel.clearData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultWrapper.Failure -> {

                }
                is ResultWrapper.Success -> {
                    pref.clearPref()
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}