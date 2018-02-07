package org.zhenchao.figlet

import org.scalatest.FunSuite
import org.zhenchao.figlet.enums.FontType

/**
  * @author zhenchao.wang 2018-02-07 16:13
  * @version 1.0.0
  */
class AsciiArtistTest extends FunSuite {

    test("testGetAsciiArt") {
        for (value <- FontType.values) {
            val art = AsciiArtist.getAsciiArt("success", value)
            println(art)
        }
    }

    test("testGetAsciiArt2") {
        val art = AsciiArtist.getAsciiArt("flglet for scala", FontType.SLANT)
        println(art)
    }

    test("testRandomAsciiArt") {
        val art = AsciiArtist.getAsciiArt("success")
        println(art)
    }

}
