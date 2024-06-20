package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class supplier_dashboard : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var supplierRecyclerView: RecyclerView
    private lateinit var supplierArrayList:ArrayList<supplierData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_dashboard)

        supplierRecyclerView = findViewById(R.id.supplierList)
        supplierRecyclerView.layoutManager = LinearLayoutManager(this)
        supplierRecyclerView.setHasFixedSize(true)

        supplierArrayList = arrayListOf<supplierData>()

        // Set the adapter of supplierRecyclerView to an empty adapter
        supplierRecyclerView.adapter = myAdapter(supplierArrayList)

        // Call getSupplierData() to populate supplierArrayList and update the adapter
        getSupplierData()
    }


    private fun getSupplierData(){
        dbRef = FirebaseDatabase.getInstance().getReference("Supplier")

        dbRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //fetch all data from database and store in arraylist
                if(snapshot.exists()){
                    for(supplierSnapshot in snapshot.children){
                        val supplier = supplierSnapshot.getValue(supplierData::class.java)
                        supplierArrayList.add(supplier!!)
                    }
                    //Store fetched data using myAdapter class
                    var adapter = myAdapter(supplierArrayList)
                    supplierRecyclerView.adapter = adapter
                    adapter.setOnItemClickListner(object:myAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            Toast.makeText(this@supplier_dashboard,"You clicked on $position",Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })



    }
}