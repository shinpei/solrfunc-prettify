package com.github.shinpei

import org.scalatest._
class MySpec extends UnitSpec{
  "Prettify" should "prettify integer" in {
    val base = "1"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "1")
  }
  it should "prettify float" in {
    val base ="3.14"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "3.14")
  }


  it should "prettify string" in {
    val base = "\"string\""
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "\"string\"")
  }
  it should "prettify variable" in {
    val base = "var"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "var")
  }
  
  it should "prettify function" in {
    val base = "pi()"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "pi(\n" + ")")

  }
  it should "prettify function with single argument" in {
    val base = "abs(1)"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "abs(\n" + "  1\n" + ")")

  }
  it should "prettify function with multiple arguments" in {
    val base = "add(1,2)"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "add(\n" + "  1,\n" + "  2\n" + ")")

  }
  it should "prettify function with function as argument" in {
    val base = "abs(add(1,2))"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == """abs(
  add(
    1,
    2
  )
)""")

  }
}

