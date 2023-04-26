package com.jones.honorsmobileapps.firebaseexperiment10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jones.honorsmobileapps.firebaseexperiment10.RecyclerViewClasses.TeacherAdapter
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.FragmentMainBinding
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.FragmentTeacherListBinding


class TeacherListFragment : Fragment() {

    private var _binding: FragmentTeacherListBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    lateinit var teacherList : MutableList<Teacher>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherListBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // ASSIGN THE DATABASE REFERENCE
        dbRef = Firebase.database.reference

        teacherList = mutableListOf()
        // Start adapter with just empty list; we'll update it below
        val adapter = TeacherAdapter(teacherList)
        binding.recyclerView.adapter = adapter

        // UPDATING APP FROM DATABASE VALUES
        // This listener gets called when app first opens + when database is updated on the web-side
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //ACCESS OBJECT WITH ALL ENTRIES WITHIN THE DATABASE
                val allDBEntries = dataSnapshot.children

                var numOfTeachersAdded = 0
                // ACCESS EACH VALUE IN DB, AND ADD TO ARRAYLIST
                for (allTeacherEntries in allDBEntries) {
                    for (singleTeacherEntry in allTeacherEntries.children) {
                        numOfTeachersAdded++
                        val name = singleTeacherEntry.child("name").getValue().toString()
                        val subject = singleTeacherEntry.child("subject").getValue().toString()
                        val yearsString = singleTeacherEntry.child("yearsIn").getValue().toString()
                        val years = Integer.parseInt(yearsString)
                        val currentTeacher = Teacher(name, subject, years)
                        teacherList.add(currentTeacher)

                        //Update recyclerview now that teacherList has data in it
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })

        return rootView
    }


}