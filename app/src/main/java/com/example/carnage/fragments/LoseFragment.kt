package com.example.carnage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carnage.R
import com.example.carnage.databinding.FragmentLoseBinding
import com.example.carnage.databinding.FragmentWinBinding
import com.example.carnage.fragments.contract.router


class LoseFragment : Fragment() {


    private var _binding:  FragmentLoseBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoseBinding.inflate(inflater,container,false)
        binding.againButtonLose.setOnClickListener {
            router().replaceFragment(BattleFragment())
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoseFragment().apply {

            }
    }
}