package org.zhenchao.figlet

import java.io._
import java.net.URL
import java.util.StringTokenizer

import org.zhenchao.figlet.enums.FontType

import scala.util.Properties

/**
  * @author zhenchao.wang 2018-02-07 11:48
  * @version 1.0.0
  */
class FigletFont {

    val MAX_CHARS: Int = 1024
    val REFULAR_CHARS: Int = 102

    var hardBlank: Char = _
    var height: Int = _
    var heightWithoutDescenders: Int = _
    var maxLine: Int = _
    var smushMode: Int = _
    var font: Array[Array[Array[Char]]] = _
    var fontName: String = ""

    def convertCharCode(input: String): Int = {
        val codeTag: String = input.split("\\s+")(0)
        if (codeTag.matches("^0[xX][0-9a-fA-F]+$")) Integer.parseInt(codeTag.substring(2), 16)
        else if (codeTag.matches("^0[0-7]+$")) Integer.parseInt(codeTag.substring(1), 8)
        else Integer.parseInt(codeTag)
    }

    def convert(message: String): String = {
        var result: String = ""
        for (l <- 0 until this.height) {
            for (c <- 0 until message.length) {
                result += this.getCharLineString(message.charAt(c), l)
            }
            result += Properties.lineSeparator
        }
        result
    }

    def convertOneLine(fontPath: String, message: String): String = {
        var fontStream: InputStream = null
        if (fontPath.startsWith("classpath:")) fontStream = getClass.getResourceAsStream(fontPath.substring(10))
        else if (fontPath.startsWith("http://") || fontPath.startsWith("https://")) fontStream = new URL(fontPath).openStream()
        else fontStream = new FileInputStream(fontPath)
        this.convertOneLine(fontStream, message)
    }

    def convertOneLine(stream: InputStream, message: String): String = {
        FigletFont(stream).convert(message)
    }

    def getCharLineString(c: Int, l: Int): String = {
        if (font(c)(l) == null) null else String.valueOf(font(c)(l))
    }

}

object FigletFont {

    @throws(classOf[IOException])
    def apply(font: FontType.Value): FigletFont = {
        val stream = getClass.getClassLoader.getResourceAsStream(font.toString + ".flf")
        this.apply(stream)
    }

    @throws(classOf[IOException])
    def apply(stream: InputStream): FigletFont = {
        val ff = new FigletFont
        ff.font = Array.ofDim[Char](ff.MAX_CHARS, 0, 0)
        var data: BufferedReader = null
        try {
            data = new BufferedReader(new InputStreamReader(new BufferedInputStream(stream), "UTF-8"))
            var dummyS = data.readLine()
            var st = new StringTokenizer(dummyS, " ")
            val s = st.nextToken
            ff.hardBlank = s.charAt(s.length - 1)
            ff.height = st.nextToken.toInt
            ff.heightWithoutDescenders = st.nextToken.toInt
            ff.maxLine = st.nextToken.toInt
            ff.smushMode = st.nextToken.toInt
            val dummyI = st.nextToken.toInt
            if (dummyI > 0) {
                st = new StringTokenizer(data.readLine, " ")
                if (st.hasMoreElements) ff.fontName = st.nextToken
            }

            var j = 0
            val charsTo = Array.ofDim[Int](ff.REFULAR_CHARS)
            for (c <- 32 to 126) {
                charsTo(j) = c
                j += 1
            }
            for (additional <- Array(196, 214, 220, 228, 246, 252, 223)) {
                charsTo(j) = additional
                j += 1
            }
            for (_ <- 0 until (dummyI - 1)) dummyS = data.readLine

            var charPos = 0
            var charCode = 0
            while (dummyS != null) {
                if (charPos < ff.REFULAR_CHARS) {
                    charCode = charsTo(charPos)
                    charPos += 1
                } else {
                    dummyS = data.readLine
                    if (dummyS != null) charCode = ff.convertCharCode(dummyS)
                }
                for (h <- 0 until ff.height) {
                    dummyS = data.readLine
                    if (dummyS != null) {
                        if (h == 0) ff.font(charCode) = Array.ofDim[Char](ff.height, 0)
                        var t = dummyS.length - 1 - (if (h == ff.height - 1) 1 else 0)
                        if (ff.height == 1) t += 1
                        ff.font(charCode)(h) = Array.ofDim[Char](t)
                        for (l <- 0 until t) {
                            val a = dummyS.charAt(l)
                            ff.font(charCode)(h)(l) = if (a == ff.hardBlank) ' ' else a
                        }
                    }
                }
            }
        } finally {
            if (null != data) data.close()
        }
        ff
    }
}
