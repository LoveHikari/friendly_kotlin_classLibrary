package com.gootrip.util

import java.text.SimpleDateFormat


/**
 * 时间工具类
 */
class DateUtil {
    companion object StaticParams {
        /**
         * 时间戳转换成日期格式字符串
         *
         * @param _timeStamp 时间戳
         * @param _format 格式， 默认yyyy-MM-dd HH:mm:ss
         * @param _unit 时间精度，秒(s) 毫秒(ms)
         * @return 日期格式字符串
         */
        @JvmStatic
        @Throws(Exception::class)
        fun timeStamp2Date(_timeStamp: Long, _format: String = "", _unit: String = "ms"): String {
            var timeStamp = _timeStamp
            var format = _format
            if (_unit.toLowerCase() != "ms") {
                timeStamp *= 1000
            }
            if (format.isEmpty()) {
                format = "yyyy-MM-dd HH:mm:ss"
            }
            val sdf = SimpleDateFormat(format)
            return sdf.format(timeStamp)
        }

        /**
         * 日期格式字符串转换成时间戳
         *
         * @param _dateStr 字符串日期
         * @param _format 如：yyyy-MM-dd HH:mm:ss
         * @param _unit 时间精度，秒(s) 毫秒(ms)
         * @return 时间戳
         */
        @JvmStatic
        fun date2TimeStamp(_dateStr: String?, _format: String = "", _unit: String = "ms"): Long {
            val sdf = SimpleDateFormat(_format)
            return if (_unit.toLowerCase() != "ms")
                sdf.parse(_dateStr).time / 1000
            else
                sdf.parse(_dateStr).time
        }

        /**
         * 取得当前时间戳（精确到毫秒）
         *
         * @param _unit 时间精度，秒(s) 毫秒(ms)
         * @return 时间戳
         */
        @JvmStatic
        fun timeStampSecond(_unit: String = "ms"): Long {
            val time = System.currentTimeMillis()
            return if (_unit == "ms")
                time
            else
                time / 1000
        }
    }
}