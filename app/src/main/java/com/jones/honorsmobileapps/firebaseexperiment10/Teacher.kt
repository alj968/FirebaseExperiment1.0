package com.jones.honorsmobileapps.firebaseexperiment10

/* NEED to specify default values for database data retrieval to work
and need to use val so defaults can be overridden */
data class Teacher (var name: String = "", var subject: String = "", var yearsIn: Int = 0) {
}