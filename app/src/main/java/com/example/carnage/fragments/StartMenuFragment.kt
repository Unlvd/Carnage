package com.example.carnage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.carnage.R
import com.example.carnage.databinding.FragmentStartMenuBinding
import com.example.carnage.fragments.contract.router
import com.google.firebase.auth.FirebaseAuth


class StartMenuFragment : Fragment() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var login: Button
    lateinit var register: TextView

    private lateinit var mAuth: FirebaseAuth


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

        email = binding.editTextTextEmailAddress
        password = binding.editTextTextPassword
        login = binding.startButtleButton
        register = binding.registerTv

        mAuth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(activity, "Заполните поля", Toast.LENGTH_SHORT).show()
            }else {
                mAuth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            router().replaceFragment(BattleFragment())
                            router().openMenu()
                        }else{
                            Toast.makeText(activity, "Чета не так пошло", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        register.setOnClickListener{
            router().replaceFragment(RegisterFragment())

        }

        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StartMenuFragment().apply {}
    }
}