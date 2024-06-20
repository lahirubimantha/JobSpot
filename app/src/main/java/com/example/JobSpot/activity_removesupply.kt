package com.example.JobSpot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.JobSpot.databinding.ActivityRemovesupplyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class activity_removesupply : AppCompatActivity() {
    private lateinit var binding : ActivityRemovesupplyBinding
    private lateinit var deleteRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemovesupplyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelDelete.setOnClickListener {
            var intent = Intent(this@activity_removesupply,activity_supplieshome::class.java)
            startActivity(intent)
        }

        binding.btnRemove.setOnClickListener {
            var deleteSupplier = binding.edtDelete.text.toString()
            if(deleteSupplier.isNotEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Delete Job")
                    .setMessage("Are you sure you want to delete this job/task?")
                    .setPositiveButton("Yes") { _, _ ->
                        // code to delete record
                        deleteData(deleteSupplier)
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
            }
            else
                Toast.makeText(this,"Please enter JobID",Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(deleteSupplier: String) {
        deleteRef = FirebaseDatabase.getInstance().getReference("Supplier")
        deleteRef.child(deleteSupplier).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Job deleted successfully",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed operation",Toast.LENGTH_SHORT).show()
        }
    }
}