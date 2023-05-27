package com.example.carnage.fragments.contract

import androidx.fragment.app.Fragment

typealias ResultListener<T> = (T) -> Unit

fun Fragment.router(): Router{
    return requireActivity() as Router
}

interface Router{

    fun replaceFragment(fragment: Fragment)



    fun goBack()

    fun openMenu()


}