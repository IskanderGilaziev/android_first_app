package com.example.crimeintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment()  {

    private lateinit var crimeRecyclerView: RecyclerView
    private  var crimeAdapter: CrimeAdapter? = null
    private val crimeListViewModel: CrimeListViewModel by lazy {
          ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)

        crimeRecyclerView.layoutManager = LinearLayoutManager(context) //после создания виджета RecyclerView ему назначается другой объект LayoutManager. Это необходимо для работы виджета RecyclerView.

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
    private inner class CrimeHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
    }


    /*Класс RecyclerView взаимодействует с адаптером,
     когда требуется создать новый объект ViewHolder
     или связать существующий объект ViewHolder с объектом Crime.
     Сам виджет RecyclerView ничего не знает об объекте Crime, но адаптер располагает полной информацией о Crime.
     */
    private inner class CrimeAdapter(var crimes: List<Crime>) :
        RecyclerView.Adapter<CrimeHolder>() {

        //когда требуется новое представление для отображения элемента.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.apply {
                titleTextView.text = crime.title
                dateTextView.text = crime.date.toString()
            }
        }

        override fun getItemCount(): Int {
            return crimes.size
        }
    }


}