package com.gootrip.util

import java.util.regex.Pattern

class StringUtil {
    companion object StaticParams {
        /**
         * 获取链接的后缀名
         * @return
         */
        @JvmStatic
        @Throws(Exception::class)
        fun parseSuffix(url: String): String {
            val pattern = Pattern.compile("\\S*[?]\\S*")
            val matcher = pattern.matcher(url)
            val spUrl = url.split("/")
            val len = spUrl.size
            val endUrl = spUrl[len - 1]
            if(matcher.find()) {
                val spEndUrl = endUrl.split("\\?")
                return spEndUrl[0].split("\\.")[1]
            }
            return endUrl.split("\\.")[1]
        }
    }
}