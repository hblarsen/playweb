package models.Rules

import scala.util.parsing.combinator.RegexParsers

// This is just my sandbox for experimenting with Parsers

case class IDENTIFIER(str: String)

case class DummyParsers(name: String) extends RegexParsers {

  // (returns Parser function)
  // (Definition from From Parsers.Scala)
  // def Parser[T](f: Input => ParseResult[T]): Parser[T]
  //
  // (abstract class Parser in Parsers contains map)
  // (map takes a function f as parameter that transforms T to U)
  // def map[U](f: T => U): ParseResult[U] = Parser{ in => this(in) map(f)} //(map is same as ^^)
  //
  // def ^^ [U](f: T => U): Parser[U] = map(f).named(toString+"^^")
  //
  // scala.util.matching.Regex is returned from """[bla]""".r
  // "\\w[xd]" is the same as """\w[xd]"""
  //
  // From RegexParser.scala:
  // There's an implicit conversion from [[scala.util.matching.Regex]] to `Parser[String]`,
  // so that regex expressions can be used as parser combinators.
  //
  // ("blabla".r is converted to Parser[String])
  // Parser[String] has function map as defined above that takes a function as parameter - here as anonymous function
  //
  // def QUOTED: Parser[String] = { "bla".r ^^ { _.toString } }
  //
    def QUOTED: Parser[String] = { "bla".r.map { x => x + "s"} }

    def ALT: Parser[String] = { "bla".r.map { _.concat("zzz") } }
}

//val QUOTED: Parser[String] = "([^"]*)".r.map { _ dropRight 1 substring 1}
//val x: Parser[String] = """\d+""".r.map { _ dropRight 1 substring 1}

//val identifier: Parser[String] = """\^?[a-zA-Z]+""".r ^^ { s => if (s startsWith "^") s substring 1 else s }