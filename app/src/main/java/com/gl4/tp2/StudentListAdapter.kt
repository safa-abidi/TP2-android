package com.gl4.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class StudentListAdapter(private val data: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>(), Filterable {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView
        val imageView: ImageView
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
        return dataFilterList.size
    }

    var dataFilterList = ArrayList<Student>()
    init {
        // associer le tableau des données initiales
        dataFilterList = data
    }


    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in data) {
                        if (student.prenom.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }


}