package com.example.crimeintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment


class CrimeFragment : Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var button: Button
    private lateinit var solveCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Заполняем представление фрагмента, вызывая LayoutInflater.inflate(…) с передачей идентификатора ресурса макета.
        // Второй параметр определяет родителя представления, что обычно необходимо для правильной настройки виджета.
        // Третий параметр указывает, нужно ли включать заполненное представление в родителя.
        // Мы передаем false, потому что представление будет добавлено в коде активности.
        val view: View =  inflater.inflate(R.layout.fragment_crime, container, false)

        titleField = view.findViewById(R.id.crime_title)
        titleField.textChanged{it} //TODO!!

        button = view.findViewById(R.id.crime_date)
        button.text = crime.mDate.toString()
        button.isEnabled = false

        solveCheckBox = view.findViewById(R.id.crime_solved)
        solveCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->  crime.mSolved = isChecked}

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}

fun EditText.textChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun onTextChanged(c: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}