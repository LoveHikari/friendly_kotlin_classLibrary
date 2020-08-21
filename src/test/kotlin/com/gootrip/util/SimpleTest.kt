package com.gootrip.util

import org.junit.Assert.assertTrue
import org.junit.Test

class SimpleTest {
    @Test
    fun unitTestingWorks() {
        val url = "https://api.fczbl.vip/qr/?m=1&e=L&p=5&url=https://www.fczbl.vip/"
        val html = HttpUtil.sendGet(url)

        assertTrue(true)
    }
}