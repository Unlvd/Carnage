package com.example.carnage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.CheckBox
import com.example.carnage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var checkedBox: CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectCheckBox(binding.cbHead)
        selectCheckBox(binding.cbChest)
        selectCheckBox(binding.cbBelt)
        selectCheckBox(binding.cbLegs)
    }


    private fun selectCheckBox(checkBox: CheckBox) {
        checkBox.setOnClickListener {
            checkedBox?.isChecked = false
            checkedBox = checkBox
            checkedBox?.isChecked = true
        }

    }
    
}