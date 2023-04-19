package com.jones.honorsmobileapps.firebaseexperiment10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbRef = Firebase.database.reference

        addTeachersToDatabase()

        updateFromDatabase()

//        dbRef.child("teachers").removeValue()

//
//        // Child is a "node"
//        database.reference.child("message2").setValue("Goodbye, World")
//        database.reference.child("message2").removeValue()
//
//        database.reference.child("message").setValue("Hello, NEW World")

        //val myRef = database.getReference("message")

    }

    fun addTeachersToDatabase() {
        val teacher1 = Teacher("Amanda Jones", "Comp Sci", 8);
        val teacher2 = Teacher("Chris Sedon", "Comp Sci", 2);
        val teacher3 = Teacher("Vince Pricci", "Math", 20);

        // Push puts each under a unique id within teachers key
        dbRef.child("teachers").push().setValue(teacher1)
        dbRef.child("teachers").push().setValue(teacher2)
        dbRef.child("teachers").push().setValue(teacher3)
    }

    fun updateFromDatabase() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for( childSnapshot in dataSnapshot.children) {
                    for (child in childSnapshot.children) {
                        Log.d("MainActivity", "Name is: ${child.child("name").getValue()}")
                        Log.d("MainActivity", "Subject is: ${child.child("subject").getValue()}")
                        Log.d("MainActivity", "Years in is: ${child.child("yearsIn").getValue()}")
                    }
                }
            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })
    }
}