package com.example.carnage.fragments

import android.app.Activity
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
import com.example.carnage.databinding.FragmentRegisterBinding
import com.example.carnage.databinding.FragmentStartMenuBinding
import com.example.carnage.fragments.contract.router
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var register: Button


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        email = binding.emailRegisterFragment
        password = binding.passwordRegisterFragment
        register = binding.registerButton

        mAuth = FirebaseAuth.getInstance()
        binding.goBackBtn.setOnClickListener {
            router().replaceFragment(StartMenuFragment())
        }

        register.setOnClickListener {
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(activity, "Заполните поля", Toast.LENGTH_SHORT).show()
            }else{
                mAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){

                            router().replaceFragment(BattleFragment())
                            router().openMenu()
                        }else{
                            Toast.makeText(activity, "Чета не правельно ввел", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {
            }
    }
}