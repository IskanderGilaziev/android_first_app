package com.example.crimeintent

import androidx.fragment.app.Fragment

class CrimeListActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment? {
        return CrimeListFragment()
    }
}