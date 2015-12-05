package com.github.shinpei

import org.scalatest._
class MySpec extends UnitSpec{
  "Prettify" should "read integer" in {
    val base = "1"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "1")
  }
  it should "parse float" in {
    val base ="3.14"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "3.14")
  }


  it should "read string" in {
    val base = "\"string\""
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "\"string\"")
  }
  
  it should "read function" in {
    val base = "abs(1)"
    val parsed = SolrFuncParser(base) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
    assert(SolrFuncPrettify.prettify(parsed,0) == "abs(\n" + "  1\n"+")")

  }
}

