package com.gootrip.util

import java.io.UnsupportedEncodingException

class CharTools {
    companion object StaticParams {
        /**
         * 转换编码 ISO-8859-1到GB2312
         * @param text 111
         * @return
         */
        @JvmStatic
        fun ISO2GB(text: String): String {
            val result: String
            result = try {
                String(text.toByteArray(charset("ISO-8859-1")), charset("GB2312"))
            } catch (ex: UnsupportedEncodingException) {
                ex.toString()
            }
            return result
        }
    }

}