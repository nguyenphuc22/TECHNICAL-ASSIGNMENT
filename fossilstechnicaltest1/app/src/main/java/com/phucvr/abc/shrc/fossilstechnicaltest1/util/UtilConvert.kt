package com.phucvr.abc.shrc.fossilstechnicaltest1.util

import android.annotation.SuppressLint
import java.lang.Long.signum
import java.lang.String.format
import java.text.CharacterIterator
import java.text.SimpleDateFormat
import java.text.StringCharacterIterator
import java.util.*


class UtilConvert {
    companion object {
        fun convertLongToTime(time : Long) : String {
            val date = Date(time)
            val format = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US)
            return format.format(date)
        }

        @SuppressLint("DefaultLocale")
        fun convertLongToSize(size : Long) : String {
            val absB = if (size == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(size)
            if (absB < 1024) {
                return "$size B"
            }
            var value = absB
            val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
            var i = 40
            while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
                value = value shr 10
                ci.next()
                i -= 10
            }
            value *= signum(size).toLong()
            return format("%.1f %ciB", value / 1024.0, ci.current())
        }

        @SuppressLint("DefaultLocale")
        fun convertLongToSize1000(size: Long) : String {
            var bytes = size
            if (-1000 < bytes && bytes < 1000) {
                return "${bytes} B"
            }
            val ci: CharacterIterator = StringCharacterIterator("kMGTPE")
            while (bytes <= -999950 || bytes >= 999950) {
                bytes /= 1000
                ci.next()
            }
            return format("%.1f %cB", bytes / 1000.0, ci.current())
        }


    }
}