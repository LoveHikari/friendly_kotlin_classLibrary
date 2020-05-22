package com.gootrip.util

import java.io.*

/**
 * 文件处理类
 * @author YUXIAOWEI
 * Created by Administrator on 2016/12/30.
 */
class FileHelper {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * 参考：http://blog.csdn.net/fireson/article/details/6697606
     * @param filePath 文件路径
     * @param encoding 编码
     * @return 文件内容
     */
    fun readTxtFile(filePath: String?, encoding: String?): String {
        var result = ""
        try {
            val file = File(filePath)
            if (file.isFile && file.exists()) { //判断文件是否存在
                val fileLength = file.length()
                val fileContent = ByteArray(fileLength.toInt())
                val `in` = FileInputStream(file)
                `in`.read(fileContent)
                `in`.close()
                result = String(fileContent, charset(encoding!!))
            } else {
                System.err.println("找不到指定的文件")
            }
        } catch (ex: Exception) {
            System.err.println("读取文件内容出错")
            ex.printStackTrace()
        }
        return result
    }

    /**
     * 输出文本，存在则追加，不存在则新建
     * @param filePath 文件路径
     * @param content 文本内容
     * @param encoding 编码
     */
    fun writeTxtFile2(filePath: String?, content: String?, encoding: String?) {
        var result: String? //内容更新
        try {
            val file = File(filePath)
            if (file.exists()) {
                println("文件存在")
            } else {
                println("文件不存在")
                file.createNewFile() // 不存在则创建
            }
            val fileLength = file.length()
            val fileContent = ByteArray(fileLength.toInt())
            val `in` = FileInputStream(file)
            `in`.read(fileContent)
            `in`.close()
            result = String(fileContent, charset(encoding!!))
            result += content
            val write = OutputStreamWriter(FileOutputStream(file), encoding)
            val output = BufferedWriter(write)
            output.write(result)
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}