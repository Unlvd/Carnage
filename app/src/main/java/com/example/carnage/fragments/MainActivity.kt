package com.example.carnage.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.carnage.R
import com.example.carnage.databinding.ActivityMainBinding
import com.example.carnage.fragments.contract.Router


class MainActivity : AppCompatActivity(), Router {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(savedInstanceState == null){
            replaceFragment(StartMenuFragment())
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(BattleFragment())
                R.id.balance -> replaceFragment(BalanceFragment())
                R.id.backpack -> replaceFragment(BackpackFragment())
                else ->{}
            }
            true
        }

        }


    override fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.item_layout,fragment)
        fragmentTransaction.commit()

    }





    override fun goBack() {
        onBackPressed()
    }



    override fun openMenu() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

}
    
