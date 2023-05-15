package com.example.carnage.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carnage.R
import com.example.carnage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.item_layout,StartMenuFragment()).commit()
        }
        }
    }
    
