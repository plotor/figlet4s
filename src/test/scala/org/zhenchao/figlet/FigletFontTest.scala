package org.zhenchao.figlet

import org.scalatest.FunSuite
import org.zhenchao.figlet.enums.FontType

import scala.util.Properties

/**
  * @author zhenchao.wang 2018-02-07 15:30
  * @version 1.0.0
  */
class FigletFontTest extends FunSuite {

    val LINE_ENDING: String = Properties.lineSeparator

    test("testConstruct") {
        val ff = FigletFont(FontType.STANDARD)
        assert("Standard" == ff.fontName)
        assert('$' == ff.hardBlank)
        assert(6 == ff.height)
        assert(5 == ff.heightWithoutDescenders)
        assert(16 == ff.maxLine)
        assert(15 == ff.smushMode)
        //space
        assert(' ' == ff.font(32)(0)(0))
        assert(' ' == ff.font(32)(0)(1))
        assert(' ' == ff.font(32)(1)(0))
        assert(' ' == ff.font(32)(1)(1))
        assert(' ' == ff.font(32)(2)(0))
        assert(' ' == ff.font(32)(2)(1))
        assert(' ' == ff.font(32)(3)(0))
        assert(' ' == ff.font(32)(3)(1))
        assert(' ' == ff.font(32)(4)(0))
        assert(' ' == ff.font(32)(4)(1))
        assert(' ' == ff.font(32)(5)(0))
        assert(' ' == ff.font(32)(5)(1))
    }

    test("testConstructNoName") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard-without-name.flf"))
        assert("" == ff.fontName)
        assert('$' == ff.hardBlank)
        assert(6 == ff.height)
        assert(5 == ff.heightWithoutDescenders)
        assert(16 == ff.maxLine)
        assert(15 == ff.smushMode)
        //space
        assert(' ' == ff.font(32)(0)(0))
        assert(' ' == ff.font(32)(0)(1))
        assert(' ' == ff.font(32)(1)(0))
        assert(' ' == ff.font(32)(1)(1))
        assert(' ' == ff.font(32)(2)(0))
        assert(' ' == ff.font(32)(2)(1))
        assert(' ' == ff.font(32)(3)(0))
        assert(' ' == ff.font(32)(3)(1))
        assert(' ' == ff.font(32)(4)(0))
        assert(' ' == ff.font(32)(4)(1))
        assert(' ' == ff.font(32)(5)(0))
        assert(' ' == ff.font(32)(5)(1))
    }

    test("testConstructNoNameNoComments") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard-without-name-no-comments.flf"))
        assert("" == ff.fontName)
        assert('$' == ff.hardBlank)
        assert(6 == ff.height)
        assert(5 == ff.heightWithoutDescenders)
        assert(16 == ff.maxLine)
        assert(15 == ff.smushMode)
        //space
        assert(' ' == ff.font(32)(0)(0))
        assert(' ' == ff.font(32)(0)(1))
        assert(' ' == ff.font(32)(1)(0))
        assert(' ' == ff.font(32)(1)(1))
        assert(' ' == ff.font(32)(2)(0))
        assert(' ' == ff.font(32)(2)(1))
        assert(' ' == ff.font(32)(3)(0))
        assert(' ' == ff.font(32)(3)(1))
        assert(' ' == ff.font(32)(4)(0))
        assert(' ' == ff.font(32)(4)(1))
        assert(' ' == ff.font(32)(5)(0))
        assert(' ' == ff.font(32)(5)(1))
    }

    test("testGetFont") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard.flf"))
        assert(' ' == ff.font(32)(0)(0))
        assert(' ' == ff.font(32)(0)(1))
        assert(' ' == ff.font(32)(1)(0))
        assert(' ' == ff.font(32)(1)(1))
        assert(' ' == ff.font(32)(2)(0))
        assert(' ' == ff.font(32)(2)(1))
        assert(' ' == ff.font(32)(3)(0))
        assert(' ' == ff.font(32)(3)(1))
        assert(' ' == ff.font(32)(4)(0))
        assert(' ' == ff.font(32)(4)(1))
        assert(' ' == ff.font(32)(5)(0))
        assert(' ' == ff.font(32)(5)(1))
    }

    test("testGetChar") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard.flf"))
        assert(' ' == ff.font(32)(0)(0))
        assert(' ' == ff.font(32)(0)(1))
        assert(' ' == ff.font(32)(1)(0))
        assert(' ' == ff.font(32)(1)(1))
        assert(' ' == ff.font(32)(2)(0))
        assert(' ' == ff.font(32)(2)(1))
        assert(' ' == ff.font(32)(3)(0))
        assert(' ' == ff.font(32)(3)(1))
        assert(' ' == ff.font(32)(4)(0))
        assert(' ' == ff.font(32)(4)(1))
        assert(' ' == ff.font(32)(5)(0))
        assert(' ' == ff.font(32)(5)(1))
    }

    test("testGetCharLineString") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard.flf"))
        assert("  " == ff.getCharLineString(32, 0))
        assert("  " == ff.getCharLineString(32, 1))
        assert("  " == ff.getCharLineString(32, 2))
        assert("  " == ff.getCharLineString(32, 3))
        assert("  " == ff.getCharLineString(32, 4))
        assert("  " == ff.getCharLineString(32, 5))
    }

    test("testGetCharLineStringWithNullLine") {
        val ff = FigletFont(getClass.getClassLoader.getResourceAsStream("standard-null-line.flf"))
        assert("  " == ff.getCharLineString(32, 0))
        assert("  " == ff.getCharLineString(32, 1))
        assert("  " == ff.getCharLineString(32, 2))
        assert("  " == ff.getCharLineString(32, 3))
        assert("  " == ff.getCharLineString(32, 4))
        assert("" == ff.getCharLineString(32, 5))
    }

    test("testConvert") {
        val asciiArt = FigletFont(getClass.getClassLoader.getResourceAsStream("standard.flf")).convert("jfiglet")
        assert(asciiArt == figletString)
    }

    def figletString: String = {
        "    _    __   _           _          _   " + LINE_ENDING +
                "   (_)  / _| (_)   __ _  | |   ___  | |_ " + LINE_ENDING +
                "   | | | |_  | |  / _` | | |  / _ \\ | __|" + LINE_ENDING +
                "   | | |  _| | | | (_| | | | |  __/ | |_ " + LINE_ENDING +
                "  _/ | |_|   |_|  \\__, | |_|  \\___|  \\__|" + LINE_ENDING +
                " |__/             |___/                  " + LINE_ENDING
    }

}
