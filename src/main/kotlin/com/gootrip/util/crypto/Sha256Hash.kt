package com.gootrip.util.crypto

import java.security.MessageDigest

/**
 * Sha256Hash.
 * Created by 子不语(houkunlin@ibona.cn) on 2020-06-09.
 *
 * @author 子不语
 * @date 2020-06-09
 * @see 'https://www.jianshu.com/p/ac6c57285646'
 */
class Sha256Hash {
    fun encrypt(str: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(str.toByteArray())
        return toHex(result)
    }

    private fun toHex(byteArray: ByteArray): String {
        //转成16进制后是32字节
        return with(StringBuilder()) {
            byteArray.forEach {
                val hex = it.toInt() and (0xFF)
                val hexStr = Integer.toHexString(hex)
                if (hexStr.length == 1) {
                    append("0").append(hexStr)
                } else {
                    append(hexStr)
                }
            }
            toString()
        }
    }
}