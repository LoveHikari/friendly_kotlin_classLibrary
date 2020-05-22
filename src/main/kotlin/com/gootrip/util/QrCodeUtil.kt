package com.gootrip.util

import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.ByteArrayInputStream
import java.util.*
import javax.imageio.ImageIO

/**
 * 二维码帮助类
 */
class QrCodeUtil {
    companion object StaticParams {
        /**
         * 解析二维码解析,此方法是解析Base64格式二维码图片
         * baseStr:base64字符串,data:image/png;base64开头的
         */
        @JvmStatic
        @Throws(Exception::class)
        fun deEncodeByBase64(base64String: String): String {
            val i = base64String.indexOf("data:image/png;base64,")
            val baseStr = base64String.substring(i + "data:image/png;base64,".length) // 去掉base64图片的data:image/png;base64,部分才能转换为byte[]
            val b = Base64.getDecoder().decode(baseStr) // baseStr转byte[]
            val byteArrayInputStream = ByteArrayInputStream(b)//byte[] 转BufferedImage
            val image = ImageIO.read(byteArrayInputStream)
            val source = BufferedImageLuminanceSource(image)
            val binarizer = HybridBinarizer(source)
            val binaryBitmap = BinaryBitmap(binarizer)
            val hints = HashMap<DecodeHintType, Any>()
            hints[DecodeHintType.CHARACTER_SET] = "UTF-8"
            val result = MultiFormatReader().decode(binaryBitmap, hints)//解码
            // println("图片中内容：  ")
            // println("content： " + result.text)
            val content = result.text
            return content
        }
    }
}