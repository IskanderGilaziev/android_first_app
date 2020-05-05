package com.example.database

import androidx.room.Dao
import androidx.room.Query
import com.example.crimeintent.Crime
import java.util.*

/**
 * DAO
 */
@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
    fun getCrimes(): List<Crime>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): Crime?


}