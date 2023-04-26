package com.jones.honorsmobileapps.firebaseexperiment10.RecyclerViewClasses

import androidx.recyclerview.widget.RecyclerView
import com.jones.honorsmobileapps.firebaseexperiment10.Teacher
import com.jones.honorsmobileapps.firebaseexperiment10.databinding.ListItemLayoutBinding

class TeacherViewHolder(val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentTeacher: Teacher

    fun bindTeacher(teacher: Teacher) {
        currentTeacher = teacher
        binding.nameTextView.text = currentTeacher.name
        binding.subjectTextView.text = currentTeacher.subject
        binding.yearsInTextView.text = currentTeacher.yearsIn.toString()
    }
}

