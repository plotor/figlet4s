package org.zhenchao.figlet

import org.scalatest.FunSuite

import scala.io.Source

/**
  * @author zhenchao.wang 2018-02-07 11:33
  * @version 1.0.0
  */
class FontTypeTest extends FunSuite {

    test("generate") {
        for (line <- Source.fromFile("src/test/resources/flf.names").getLines()) {
            println(s"""val ${line.toUpperCase()}: Value = Value("$line")""")
        }
    }

}
