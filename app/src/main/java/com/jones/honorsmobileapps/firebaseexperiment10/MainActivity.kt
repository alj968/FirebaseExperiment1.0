package com.jones.honorsmobileapps.firebaseexperiment10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database("https://fir-experiment1-f0f17-default-rtdb.firebaseio.com/")
        database.reference.child("message").setValue("Hello, World")
        //val myRef = database.getReference("message")

    }
}