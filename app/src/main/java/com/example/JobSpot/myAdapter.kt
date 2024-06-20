package com.example.JobSpot

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myAdapter(private val supplierList : ArrayList<supplierData>): RecyclerView.Adapter<myAdapter.myViewHolder>() {
    //Create variable to catch itemClick
    private lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position:Int)
    }

    fun setOnItemClickListner(listener:onItemClickListner){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter.myViewHolder {
        val supplierView = LayoutInflater.from(parent.context).inflate(R.layout.single_supplierview,parent,false)
        return myViewHolder(supplierView,mListener)
    }

    override fun onBindViewHolder(holder: myAdapter.myViewHolder, position: Int) {
        val currentSupplier = supplierList[position]

        holder.jobSupplierName.text = currentSupplier.jobSupplierName
        holder.jobTitle.text = currentSupplier.jobTitle
        holder.jobSalary.text = currentSupplier.jobSalary.toString()
    }

    override fun getItemCount(): Int {
        return supplierList.size;
    }

    class myViewHolder(supplierView: View,listener: onItemClickListner): RecyclerView.ViewHolder(supplierView){
        val jobSupplierName: TextView = supplierView.findViewById(R.id.tvsupplierName)
        val jobTitle: TextView = supplierView.findViewById(R.id.tvsupplierEmail)
        val jobSalary: TextView = supplierView.findViewById(R.id.tvsupplierContact)

        //init method to set itemClick
        init{
            supplierView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}