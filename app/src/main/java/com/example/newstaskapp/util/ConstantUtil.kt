package com.example.newstaskapp.util

import android.text.format.DateUtils
import com.example.newstaskapp.util.Constant.DATE_IN_FORMAT
import com.example.newstaskapp.util.Constant.DATE_OUT_FORMAT
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ConstantUtil {

    fun dateFormat(inputDate: String): CharSequence {
        var outDate = inputDate
        val simpleDateFormat = SimpleDateFormat(DATE_IN_FORMAT, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        val date: Date?
        val sdfOutPutToSend = SimpleDateFormat(DATE_OUT_FORMAT, Locale.getDefault())
        sdfOutPutToSend.timeZone = TimeZone.getDefault()
        try {
            date = simpleDateFormat.parse(inputDate)
            outDate = sdfOutPutToSend.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val timeStamp = Timestamp.valueOf(outDate)

        return DateUtils.getRelativeTimeSpanString(
            timeStamp.time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS)
    }
}
