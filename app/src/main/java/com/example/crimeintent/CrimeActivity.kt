package com.example.crimeintent

import androidx.fragment.app.Fragment

class CrimeActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment? {
        return CrimeFragment()
    }
}

