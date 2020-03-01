package com.example.crimeintent

import android.content.Context
import java.util.*

class CrimeLab private constructor(context: Context) {
    val crimes: List<Crime>

    fun getCrime(id: UUID): Crime? {
        for (crime in crimes) {
            if (crime.mId == id) {
                return crime
            }
        }
        return null
    }

    companion object {
        private var crimeLab: CrimeLab? = null
        fun getCrimeLab(context: Context): CrimeLab? {
            if (crimeLab == null) {
                crimeLab = CrimeLab(context)
            }
            return crimeLab
        }
    }

    init {
        crimes = ArrayList()
        for (i in 0..99) {
            val crime = Crime()
            crime.mTitle = "Crime #$i"
            crime.mSolved = i % 2 == 0 // Для каждого второго объекта
            crimes.add(crime)
        }
    }
}