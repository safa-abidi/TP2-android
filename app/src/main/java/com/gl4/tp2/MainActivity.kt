package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class MainActivity : AppCompatActivity() {
   lateinit var myAdapter: StudentListAdapter
   val recyclerView : RecyclerView by lazy { findViewById(R.id.recyclerView)  }
   val spinner : Spinner by lazy { findViewById(R.id.spinner) }


   lateinit var textRecherche : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textRecherche = findViewById(R.id.editTextRechercher)
        var studentList = arrayListOf<Student>(Student("jean", "paul","m"),
            Student("abidi","safa", "f"),
            Student("achour", "ines", "f"),
            Student("sammari", "amal", "f"),
            Student("samet", "rayen", "m"))
       // myAdapter = StudentListAdapter(studentList)
        var matieres = listOf<String>("Cours","TP")
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val matiere = matieres.get(position)
                Toast.makeText(this@MainActivity, "$matiere", Toast.LENGTH_LONG).show()
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }

        }
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = StudentListAdapter(studentList)
        }

        textRecherche.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(constraint: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                myAdapter.filter.filter(p0)

            }
        })
    }

}