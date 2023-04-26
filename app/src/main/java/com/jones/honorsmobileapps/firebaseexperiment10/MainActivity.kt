package com.jones.honorsmobileapps.firebaseexperiment10

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var dbRef: DatabaseReference
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = Firebase.database.reference

        // HOW TO: ADD TO DATABASE
        binding.addTeacherButton.setOnClickListener {
            val name = binding.name.text.toString()
            val subject = binding.subject.text.toString()
            val yearsString = binding.yearsIn.text.toString()
            val years = Integer.parseInt(yearsString)
            val teacher = Teacher(name, subject, years)
            // Using .push() puts it under a unique id every time
            dbRef.child("teachers").push().setValue(teacher)
        }

        // HOW TO: DELETE ALL ENTRIES WITHIN A TABLE IN DATABASE
        binding.deleteTeacherButton.setOnClickListener {
            dbRef.child("teachers").removeValue()
        }

        // HOW TO: UPDATE YOUR APP WILL VALUES FROM DATABASE
        // This listener gets called when app first opens + when database is updated on the web-side
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // HOW TO : ACCESS OBJECT WITH ALL ENTRIES WITHIN THE DATABASE
                val allDBEntries = dataSnapshot.children


                var numOfTeachersAdded = 0
                // HOW TO : ACCESS EACH VALUE IN DB (in this case, just logging to make sure it matches db)
                for (allTeacherEntries in allDBEntries) {
                    for (singleTeacherEntry in allTeacherEntries.children) {
                        numOfTeachersAdded++
                        val name = singleTeacherEntry.child("name").getValue()
                        Log.d("MainActivity", "Name is: $name")
                        val subject = singleTeacherEntry.child("subject").getValue()
                        Log.d("MainActivity", "Subject is: $subject")
                        val years = singleTeacherEntry.child("yearsIn").getValue()
                        Log.d("MainActivity", "Years in is: $years")
                    }
                }
                binding.numOfTeachers.text = "$numOfTeachersAdded"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })
    }

}