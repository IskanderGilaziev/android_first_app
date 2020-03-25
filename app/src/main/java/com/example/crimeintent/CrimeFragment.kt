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
import androidx.fragment.app.Fragment


class CrimeFragment : Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solveCheckBox: CheckBox
    //Экземпляр фрагмента настраивается
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    //создание и настройка представления фрагмента
    //в этом методе заполняется макет представления фрагмента, а заполнен- ный объект View возвращается активности-хосту
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
        //titleField.textChanged{it} //TODO!!

        dateButton = view.findViewById(R.id.crime_date)
        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        solveCheckBox = view.findViewById(R.id.crime_solved)
        solveCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    /**
     * View state is restored after onCreateView(…) and before onStart().
     * When the state is restored, the contents of the EditText will get set to whatever value is currently in crime.title.
     * At this point, if you have already set a listener on the EditText (such as in onCreate(…) or onCreateView(…)),
     * TextWatcher’s beforeTextChanged(…), onTextChanged(…), and afterTextChanged(…)functions will execute.
     * Setting the listener in onStart() avoids this behavior since the listener is hooked up after the view state is restored.
     */

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int) {
                // This space intentionally left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        //Even though the OnClickListener is not triggered by the state restoration of the fragment,
        // putting it in onStart helps keep all of your listeners in one place and easy to find.
        solveCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked }

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