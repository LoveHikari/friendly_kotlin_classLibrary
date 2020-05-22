package com.gootrip.util

import kotlin.random.Random

class RandomUtil {
    /**
     * 生成随机字母与数字
     * @param length 生成长度
     * @return 随机字母与数字
     */
    fun Random.nextStr(length: Int): String? {
        val pattern = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
        var result = ""
        val n = pattern.size
        for (i in 0 until length) {
            val rnd: Int = Random.nextInt(n)
            result += pattern[rnd]
        }
        return result
    }
}