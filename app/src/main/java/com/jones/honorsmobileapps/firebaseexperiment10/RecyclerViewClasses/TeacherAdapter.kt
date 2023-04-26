package com.jones.honorsmobileapps.firebaseexperiment10.RecyclerViewClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jones.honorsmobileapps.firebaseexperiment10.Teacher
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.ListItemLayoutBinding

class TeacherAdapter(val teacherList: List<Teacher>) : RecyclerView.Adapter<TeacherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val currentTeacher = teacherList[position]
        holder.bindTeacher(currentTeacher)
    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

}