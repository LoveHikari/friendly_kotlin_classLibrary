package com.gootrip.util

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * http请求工具类
 */
class HttpUtil {
    companion object StaticParams {
        /**
         * 使用Get方式获取数据
         *
         * @param url 请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
         * @param headers 请求头，包括cookie
         * @param charset 编码方式
         * @return html源代码
         */
        @JvmStatic
        @Throws(Exception::class)
        fun sendGet(url: String, headers: Map<String, String>? = null, charset: String = "utf-8"): String {
            return createHttp(url, "GET", null, headers, charset)
        }

        /**
         * 使用Post方式获取数据
         *
         * @param url 请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
         * @param bodyParams 请求数据
         * @param headers 请求头，包括cookie
         * @param charset 编码方式
         * @return html源代码
         */
        @JvmStatic
        @Throws(Exception::class)
        fun sendPost(url: String, bodyParams: Map<String, String>, headers: Map<String, String>? = null, charset: String = "utf-8"): String {
            return createHttp(url, "POST", bodyParams, headers, charset)
        }

        /**
         * 使用HttpClient方式获取数据
         *
         * @param url 请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
         * @param method 请求方法
         * @param bodyParams 请求数据
         * @param headers 请求头，包括cookie
         * @param charset 编码方式
         * @return html源代码
         */
        private fun createHttp(url: String, method: String, bodyParams: Map<String, String>?, headers: Map<String, String>?, charset: String = "utf-8"): String {
            val bufferResult = StringBuffer()
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = method.toUpperCase()
            conn.doOutput = true
            conn.doInput = true
            conn.useCaches = false
            conn.connectTimeout = 30000
            conn.readTimeout = 30000
            val mmap = headers.orEmpty().toMutableMap()
            setRequestProperty(mmap, conn)

            // 发送POST请求必须设置如下两行


            val out = PrintWriter(OutputStreamWriter(conn.outputStream, charset))
            //发送请求
            out.print(setBodyParams(bodyParams.orEmpty(), mmap))
            //flush 输出流缓冲
            out.flush()
            val inStream = BufferedReader(InputStreamReader(conn.inputStream, charset))
            inStream.use { r ->
                while (true) {
                    val temp = r.readLine()
                    if (temp == null) {
                        break
                    } else {
                        bufferResult.append(temp)
                    }

                }

            }
            out.close()
            inStream.close()
            println(bufferResult.toString())
            return bufferResult.toString()

        }

        /**
         * @Description 设置头属性
         * @param headers 请求头
         * @param conn http连接
         */
        private fun setRequestProperty(headers: MutableMap<String, String>, conn: HttpURLConnection) {

            if (!headers.containsKey("accept")) {
                headers["accept"] = "*/*"
            }
            if (!headers.containsKey("connection")) {
                headers["connection"] = "Keep-Alive"
            }
            if (!headers.containsKey("user-agent")) {
                headers["user-agent"] = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"
            }
            if (!headers.containsKey("Content-Type")) {
                headers["Content-Type"] = "text/html"
            }

            if (headers.isNotEmpty()) {
                for (key in headers.keys) {
                    var value = headers[key]
                    conn.setRequestProperty(key, value)
                }
            }
        }

        /**
         * 设置参数
         *
         * @param bodyParams 请求参数
         * @param headers 请求头
         * @return 请求参数字符串
         */
        private fun setBodyParams(bodyParams: Map<String, String>, headers: Map<String, String>?): String {
            val sb: StringBuilder = StringBuilder()
            if (bodyParams.isNotEmpty()) {

                if (headers == null || !headers.containsKey("Content-Type")) {
                    for (key in bodyParams.keys) {
                        sb.append(key + "=" + bodyParams[key] + "&")
                    }
                    sb.deleteCharAt(sb.length - 1)
                } else if (headers["Content-Type"]?.contains("json")!!) {
                    val json: Gson = Gson()
                    sb.append(json.toJson(bodyParams))
                } else if (headers["Content-Type"]?.contains("json")!!) {
                }


            }
            return sb.toString()
        }
    }
}