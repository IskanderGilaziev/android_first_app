package com.example.crimeintent

import java.util.*

class Crime {

     val mId: UUID
     var mTitle: String? = null
     var mDate: Date
     var mSolved = false

    init {
        mId = UUID.randomUUID()
        mDate = Date()
    }

}