package com.gootrip.util

import java.io.File
import java.io.InputStream
import java.io.OutputStream


class FileUtil {
    companion object StaticParams {
        /**
         * 获取文件扩展名
         * @param filename 文件名
         * @return 文件扩展名
         */
        @JvmStatic
        fun getExtensionName(filename: String?): String? {
            if (!filename.isNullOrEmpty()) {
                val dot = filename.lastIndexOf('.')
                if (dot > -1 && dot < filename.length - 1) {
                    return filename.substring(dot + 1)
                }
            }
            return filename
        }

        /**
         * 获取不带扩展名的文件名
         * @param filename 文件名
         * @return 不带扩展名的文件名
         */
        @JvmStatic
        fun getFileNameNoEx(filename: String?): String? {
            if (!filename.isNullOrEmpty()) {
                val dot = filename.lastIndexOf('.')
                if (dot > -1 && dot < filename.length) {
                    return filename.substring(0, dot)
                }
            }
            return filename
        }

        /**
         * 保存文件
         * @param filePath 保存的文件路径
         * @param fileInputStream 需要保存的文件流
         */
        @JvmStatic
        fun saveFile(filePath: String, fileInputStream: InputStream) {
            val tempFile = File(filePath)
            if (!tempFile.exists()) {
                tempFile.outputStream().use { it ->
                    // 1K的数据缓冲
                    val bs = ByteArray(1024)
                    // 读取到的数据长度
                    var len: Int
                    // 开始读取
                    while (fileInputStream.read(bs).also { len = it } != -1) {
                        it.write(bs, 0, len)
                    }
                }


            }
        }
        /**
         * 保存文件
         * @param filePath 保存的文件路径
         * @param bytes 需要保存的文件流
         */
        @JvmStatic
        fun saveFile(filePath: String, bytes: ByteArray) {
            File(filePath).writeBytes(bytes)
        }
    }


}