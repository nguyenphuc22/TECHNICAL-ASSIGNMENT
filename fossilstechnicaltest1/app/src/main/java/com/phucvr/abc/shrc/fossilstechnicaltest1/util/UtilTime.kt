package com.phucvr.abc.shrc.fossilstechnicaltest1.util

import java.text.SimpleDateFormat
import java.util.*

class UtilTime {
    companion object {
        fun convertLongToTime(time : Long) : String {
            val date = Date(time)
            val format = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US)
            return format.format(date)
        }
    }
}