package org.zhenchao.figlet

import org.zhenchao.figlet.enums.FontType

import scala.util.Random

/**
  * @author zhenchao.wang 2018-02-07 15:19
  * @version 1.0.0
  */
object AsciiArtist {

    def getAsciiArt(word: String): String = {
        val types = FontType.values.toArray
        val ft = types(Random.nextInt(types.length))
        this.getAsciiArt(word, ft)
    }

    def getAsciiArt(word: String, font: FontType.Value): String = {
        FigletFont(font).convert(word)
    }

}
