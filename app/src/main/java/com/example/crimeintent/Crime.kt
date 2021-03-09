package com.example.crimeintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false)
//                 var isNeedRequiresPolice: Boolean = true) //TODO закоментировал, т.к. не работало подключение к бд по примеру в книги