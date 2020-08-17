package com.gootrip.util

import java.util.*
import java.io.ByteArrayInputStream
import com.google.zxing.MultiFormatReader
import com.google.zxing.DecodeHintType
import java.util.HashMap
import com.google.zxing.BinaryBitmap
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import javax.imageio.ImageIO


/**
 * 图片工具类
 */
class ImageUtil {
    companion object StaticParams {
        @JvmStatic
        @Throws(Exception::class)
        fun base64ToBitmap(base64String: String): String {
            return base64String
        }
    }
}