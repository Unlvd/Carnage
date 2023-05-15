package com.example.carnage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carnage.R
import com.example.carnage.databinding.FragmentStartMenuBinding



class StartMenuFragment : Fragment() {

    private var _binding : FragmentStartMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartMenuBinding.inflate(inflater,container,false)

        binding.startButtleButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.item_layout, BattleFragment()).commit()
        }
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StartMenuFragment().apply {}
    }
}