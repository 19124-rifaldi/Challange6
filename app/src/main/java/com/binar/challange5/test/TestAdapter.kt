package com.binar.challange5.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binar.challange5.R

class ContactAdapter (
    private val listContact : List<MyContact>,
    private val onItemClick : (MyContact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    class ContactViewHolder (view: View): RecyclerView.ViewHolder(view){
        val judulTextView : TextView = itemView.findViewById(R.id.judul_tv)
//        val phoneTextView : TextView = itemView.findViewById(R.id.nohp_tv)
//        val root : LinearLayout = itemView.findViewById(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_home_list,parent,false
        )
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.judulTextView.text = listContact[position].nama
//        holder.phoneTextView.text = listContact[position].noHp.toString()
//        holder.root.setOnClickListener{
//            onItemClick(listContact[position])
//        }
    }

    override fun getItemCount(): Int = listContact.size

}