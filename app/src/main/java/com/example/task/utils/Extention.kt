package com.example.task.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun calculateAge(birthDate: String?): Int {

    return if (birthDate != "" && birthDate != null) {
        val format: SimpleDateFormat = if(validDateFormat("yyyy-MM-dd", birthDate)){
            SimpleDateFormat("yyyy-MM-dd")
        }else{
            SimpleDateFormat("dd-MM-yyyy")
        }

        var userDate: Date? = null
        try {
            userDate = format.parse(birthDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val birth = Calendar.getInstance()
        birth.time = userDate
        val today = Calendar.getInstance()
        var yearDifference = (today[Calendar.YEAR]
                - birth[Calendar.YEAR])
        if (today[Calendar.MONTH] < birth[Calendar.MONTH]) {
            yearDifference--
        } else {
            if (today[Calendar.MONTH] == birth[Calendar.MONTH]
                && today[Calendar.DAY_OF_MONTH] < birth[Calendar.DAY_OF_MONTH]
            ) {
                yearDifference--
            }
        }
        yearDifference
    } else {
        0
    }
}

fun convertDateFormat(date: String): String {

    return if (validDateFormat("yyyy-MM-dd", date)) {
        var spf = SimpleDateFormat("yyyy-MM-dd")
        val newDate = spf.parse(date)
        spf = SimpleDateFormat("dd.MM.yyyy")
        spf.format(newDate)
    } else {
       return if (date!="" && date!= null) {
            var spf = SimpleDateFormat("dd-MM-yyyy")
            val newDate = spf.parse(date)
            spf = SimpleDateFormat("dd.MM.yyyy")
            spf.format(newDate)
        } else {
           ""
       }
    }
}

fun validDateFormat(format: String, value: String): Boolean {
    return if(value!="" && value != null ) {
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat(format)
            date = sdf.parse(value)
            if (value != sdf.format(date)) {
                date = null
            }
        } catch (ex: ParseException) {
            ex.printStackTrace()
        }
        date != null
    }else{
        false
    }

}