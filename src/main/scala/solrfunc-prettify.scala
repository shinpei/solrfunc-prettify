package com.github.shinpei

import scala.util.parsing.combinator._
import java.io.{FileReader, InputStreamReader, BufferedReader}
case class SolrFloat (moreThanOne: String, lessThanOne : String)
case class SolrInt (number: String)
case class SolrString(symbol: String)
case class SolrSymbol(sym: String)
case class SolrFunc(funcname:String, args: List[Any])  

object SolrFuncParser extends RegexParsers {
  def symbol="""[a-zA-Z_]+""".r
  def number="""\d+""".r
  def lparen = "(";
  def rparen = ")";
  def comma = ","
  def dot = "."
  def dquote = "\""

  def string = dquote ~ symbol ~ dquote ^^ {
    case (_ ~ symbol ~ _) => SolrString(symbol)
  }
  def sym = symbol ^^ {
    case (sym) => SolrSymbol(sym)
  }
  def float = number ~ dot ~ number ^^ {
    case (moreThanOne ~ dot ~ lessThanOne)
      => SolrFloat(moreThanOne, lessThanOne)
  }
  def integer = number ^^ {
    case (number) => SolrInt(number)
  }

  def expr = func | string |sym | float | integer ^^ {
    case result => result
  }

  def func : Parser[Any]= symbol ~ lparen ~ repsep(expr, comma) ~ rparen ^^ {
    case (symbol ~ _ ~ exprs ~ _) 
      => SolrFunc(symbol, exprs)
  }
  
  def apply(input:String) : Either [String, Any]  =
    parseAll(expr, input) match {
      case Success(data, next) => Right(data)
      case NoSuccess(errorMessage, next) => Left(errorMessage+ " on line " + next.pos.line + " on column " + next.pos.column);
    }
}

object DebugParser{
  def parse(input: String) : Any=
  {
    val parsed = SolrFuncParser(input)
    return parsed
  }

  def parse(input: InputStreamReader) : Any = 
  {
    val br = new BufferedReader(input)
    val inputString = br.readLine()
    return parse(inputString) match {
      case Right(x) => x
      case Left(x) => "ParseError!"
    }
  }
}

object SolrFuncPrettify {
  val INDENT: String =  "  "

  def prettify (e: Any, level: Int): String = e match {
    case (x:SolrInt) => {
      var str: String = INDENT * level
      str += x.number
      return str
    }
    case (x: SolrFloat) => {
      var str: String = (INDENT * level)
      str += x.moreThanOne + "." + x.lessThanOne
      return str
    }
    case (x : SolrString) => {
      var str = (INDENT* level)
      str += ("\"" + x.symbol + "\"")
      return str
    }	
    case (x : SolrSymbol) => {
      var str = (INDENT* level)
      str += (x.sym)
      return str
    }	
    case (x: SolrFunc) => {
      var str = (INDENT * level)
      str += x.funcname + "(\n"
      if (x.args.length > 0 ){
	for (arg <- x.args) {
	  str += prettify(arg, level+1)
	  str += ",\n"
	}
	// delete last comma and LF
	str = str.substring(0, str.length- 2)
	str += "\n"
      } 
      str += INDENT * level
      str += ")"
      return str
    }

  }

  def main(args: Array[String]) {
    if (args.length == 0) {
      println("""Print Solr function more pretty way

Usage:

      scala SolrFuncPrettify [arguments]

The arguments are:

       - :  Take standard input as an input
        e.g.) cat "add(123)" | java -jar solrfunc-prettify.jar - 
      (file) : Take given string as a file name to open as an input
        e.g.) java -jar solrfunc-prettify.jar solrfuncs.txt
""")
      return
    }
    val reader = if (args(0) == "-") new InputStreamReader(System.in)
		 else new FileReader(args(0))
    val parser = DebugParser
    val msg = prettify(parser.parse(reader), 0)
    println(msg)

  }
}


