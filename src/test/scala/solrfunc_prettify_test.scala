package com.github.shinpei

import org.scalatest._
class MySpec extends UnitSpec{
  "Prettify" should "read integer" in {
    val base = "(1)"
    val parsed = SolrFuncParser(base)
    assert(SolrFuncPrettify.prettify(parsed,0) == "1")
  }
}

