package com.example.crimeintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment : Fragment()  {

    private lateinit var crimeRecyclerView: RecyclerView
    private lateinit var crimeAdapter: CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view)

        crimeRecyclerView.layoutManager = LinearLayoutManager(activity) //после создания виджета RecyclerView ему назначается другой объект LayoutManager. Это необходимо для работы виджета RecyclerView.

        updateUI()
        return view
    }

    private fun updateUI() {
        val crimeLab = CrimeLab.getCrimeLab(this.activity!!)
        val crimes: List<Crime> = crimeLab!!.crimes
        crimeAdapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = crimeAdapter
    }



    // Реализация ViewHolder
    // В конструкторе CrimeHolder происходит заполнение list_item_crime.xml
    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false))


    /*Класс RecyclerView взаимодействует с адаптером,
     когда требуется создать новый объект ViewHolder
     или связать существующий объект ViewHolder с объектом Crime.
     Сам виджет RecyclerView ничего не знает об объекте Crime, но адаптер располагает полной информацией о Crime.
     */
    private inner class CrimeAdapter(private val crimes: List<Crime>) :
        RecyclerView.Adapter<CrimeHolder>() {

        //когда требуется новое представление для отображения элемента.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
           val layoutInflater: LayoutInflater = LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        }

        override fun getItemCount(): Int {
            return 0
        }
    }


}