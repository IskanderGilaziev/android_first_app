package com.example.crimeintent

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.IllegalArgumentException

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment()  {

    private lateinit var crimeRecyclerView: RecyclerView
    private var crimeAdapter: CrimeAdapter? = null
    private val crimeListViewModel: CrimeListViewModel by lazy {
          ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        //после создания виджета RecyclerView ему назначается другой объект LayoutManager.
        // Это необходимо для работы виджета RecyclerView.
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return view
    }

    private fun updateUI() {
     val crimes = crimeListViewModel.crimes
        crimeAdapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = crimeAdapter
    }



    // Реализация ViewHolder
    // В конструкторе CrimeHolder происходит заполнение list_item_crime.xml
    private inner class CrimeHolder(view: View) : BaseHolder(view) {
        private lateinit var crime: Crime
        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)
        override val text: String
            get() = "${crime.title} pressed!"

        override fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text =  DateFormat.format( "EEEE, MMM d, yyyy", this.crime.date)
            solvedImageView.visibility = if(crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

    }

    // добавил обработчик для случая вызова полиции
    private inner class CallPoliceHolder(view: View) : BaseHolder(view)  {
        private lateinit var crime: Crime
        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val buttonCallPolice: Button = itemView.findViewById(R.id.call_police)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        override val text: String
            get() = "Need call police with ${crime.title} !"

        override fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text =  DateFormat.format( "EEE, d MMM, yyyy", this.crime.date)
            buttonCallPolice.setOnClickListener{
                Toast.makeText(context, "Calling...", Toast.LENGTH_SHORT).show()
            }
            solvedImageView.visibility = if(crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private abstract inner class BaseHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        abstract val text: String

        init {
            itemView.setOnClickListener(this)
        }

        abstract fun bind(crime: Crime)
        //для показа реакции при нажатии на выбранное значение из списка
        override fun onClick(v: View?) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    /*Класс RecyclerView взаимодействует с адаптером,
     когда требуется создать новый объект ViewHolder
     или связать существующий объект ViewHolder с объектом Crime.
     Сам виджет RecyclerView ничего не знает об объекте Crime, но адаптер располагает полной информацией о Crime.
     */
    private inner class CrimeAdapter(var crimes: List<Crime>) :
        RecyclerView.Adapter<BaseHolder>() {

        //когда требуется новое представление для отображения элемента.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
            return if (RequiresPolice.NOT_NEED_CALL_POLICE.code == viewType) {
                val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
                CrimeHolder(view)
            } else {
                val view = layoutInflater.inflate(R.layout.call_police_item_crime, parent, false)
                CallPoliceHolder(view)
            }
        }
        // привязка представления
        override fun onBindViewHolder(holder: BaseHolder, position: Int) {
            val crime = crimes[position]
            when (holder) {
                is CrimeHolder -> {
                    holder.bind(crime)
                }
                is CallPoliceHolder -> {
                    holder.bind(crime)
                }
                else -> throw IllegalArgumentException("Invalid type holder")
            }
        }
        // получение размера для вывода на экран необходимо
        override fun getItemCount(): Int {
            return crimes.size
        }
        // для определения какой тип обработчика выводить
        override fun getItemViewType(position: Int): Int {
            val crime = crimes[position]
            if (crime.isNeedRequiresPolice) {
                return RequiresPolice.NEED_CALL_POLICE.code
            }
          return RequiresPolice.NOT_NEED_CALL_POLICE.code
        }
    }


}