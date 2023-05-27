package com.example.carnage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carnage.R
import com.example.carnage.databinding.FragmentBattleBinding
import com.example.carnage.databinding.FragmentWinBinding
import com.example.carnage.fragments.contract.router


class WinFragment : Fragment() {

    private var _binding:  FragmentWinBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinBinding.inflate(inflater,container,false)
        binding.againButton.setOnClickListener {
            router().replaceFragment(BattleFragment())
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WinFragment().apply {


            }
    }
}