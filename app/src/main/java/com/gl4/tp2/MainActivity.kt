package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val recyclerView : RecyclerView by lazy { findViewById(R.id.recyclerView)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var studentList = arrayListOf<Student>(Student("jean", "paul","m"), Student("abidi","safa", "f"), Student("achour", "ines", "f"))

        val spinner : Spinner by lazy { findViewById(R.id.spinner) }
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
    }


    class StudentListAdapter(private val data: List<Student>):
        RecyclerView.Adapter<StudentListAdapter.ViewHolder>(){
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val textView: TextView
            val imageView : ImageView
            init {
                textView = itemView.findViewById(R.id.textView)
                imageView = itemView.findViewById(R.id.imageView)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_item, parent, false)
            return ViewHolder(view)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = data[position].prenom + " " + data[position].nom
            if(data[position].genre == "m"){
                holder.imageView.setImageResource(R.drawable.boss)
            } else {
                holder.imageView.setImageResource(R.drawable.businesswoman)
            }

        }
        override fun getItemCount(): Int {
            return data.size
        }



    }
}