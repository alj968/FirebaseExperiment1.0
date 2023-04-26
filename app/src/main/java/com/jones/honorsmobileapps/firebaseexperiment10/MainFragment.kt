package com.jones.honorsmobileapps.firebaseexperiment10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.viewAllTeachersButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.action_mainFragment_to_teacherListFragment)
        }

        // ASSIGN THE DATABASE REFERENCE
        dbRef = Firebase.database.reference

        // ADD TO DATABASE
        binding.addTeacherButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val subject = binding.subjectEditText.text.toString()
            val yearsString = binding.yearsInEditText.text.toString()
            val years = Integer.parseInt(yearsString)
            val teacher = Teacher(name, subject, years)
            // Using .push() puts it under a unique id every time
            dbRef.child("teachers").push().setValue(teacher)
        }

        // DELETE ALL ENTRIES WITHIN A TABLE IN DATABASE
        binding.deleteTeacherButton.setOnClickListener {
            dbRef.child("teachers").removeValue()
        }

        /* NOTE: I pull the db entries into the app from the web in the
        TeacherListFragment */
        return rootView
    }


}