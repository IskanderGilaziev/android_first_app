package com.example.crimeintent

import android.content.Context
import androidx.room.Room
import com.example.database.CrimeDao
import com.example.database.CrimeDatabase
import java.util.*

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {

    //Room.databaseBuilder () создает конкретную реализацию вашей абстрактной CrimeDatabase, используя три параметра.
    // Сначала нужен объект Context, поскольку база данных обращается к файловой системе. Вы передаете контекст приложения,
    // потому что, как обсуждалось выше, синглтон, скорее всего, будет жить дольше, чем любой из ваших классов активности.
    //Второй параметр - это класс базы данных, который вы хотите, чтобы Room создал. Третье - это имя файла базы данных,
    // который вы хотите, чтобы Room создал для вас. Вы используете закрытую строковую константу,
    // определенную в том же файле, так как никакие другие компоненты не должны обращаться к ней.
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val crimeDao: CrimeDao = database.crimeDao()

    fun getCrimes(): List<Crime> = crimeDao.getCrimes()

    fun getCrime(id: UUID): Crime? = crimeDao.getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")

        }
    }
}