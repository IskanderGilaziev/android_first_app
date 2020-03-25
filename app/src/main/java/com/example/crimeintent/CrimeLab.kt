package com.example.crimeintent

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

@Deprecated("Not actual")
class CrimeLab private constructor(context: Context) {
    val crimes: List<Crime>

    fun getCrime(id: UUID): Crime? {
        for (crime in crimes) {
            if (crime.id == id) {
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
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0 // Для каждого второго объекта
            crimes.add(crime)
        }
    }
}