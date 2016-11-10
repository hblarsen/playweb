package models.Rules

import models.Rules

import scala.language.higherKinds
import scala.language.implicitConversions
import scala.language.postfixOps
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.json

// First attempt at Parser Combinators
// This class is not used anymore - just kept here for reference
class RulesParser extends StandardTokenParsers {

  //Json Syntax
  def QUOTE = "\""

  lexical.delimiters += ("{", "}", "[", "]", ":", ",")
  lexical.reserved += ("cname", "parameters", "rules")

  //def value : Parser[Any] = obj | arr | stringLit | numericLit | "null" | "true" | "false"
  def value : Parser[Any] = obj | arr | stringLit | numericLit
  def obj : Parser[Any] = "{" ~ repsep(member, ",") ~ "}"
  def arr : Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
  def member: Parser[Any] = stringLit ~ ":" ~ value

  //Configuration Syntax
  def configrules : Parser[Any] = "{" ~ member ~ "," ~ member ~ "," ~ member ~ "}"

}

// Latest version of a parser for the .txt version of rules file
class RuleTextParser extends StandardTokenParsers {

  lexical.delimiters += ("{", "}", "[", "]", ":", ",")
  lexical.reserved += ("cname", "parameters", "rules", "pname", "pvalues", "rname", "rif", "rthen", "AND", "OR", "NOT")

  def value : Parser[Any] = obj | arr | stringLit | numericLit
  def obj : Parser[Any] = "{" ~ repsep(member, ",") ~ "}"
  def arr : Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
  def member: Parser[Any] = stringLit ~ ":" ~ value

  def rulesfile : Parser[Any] = "{" ~ "cname" ~ ":" ~ stringLit ~ "," ~ parameters ~ "," ~ rules ~ "}" ^^ {
    case _ ~ _ ~ _ ~ cn ~ _ ~ _ ~ _ ~ _ ~ _  => ruleModel(cn)
  }

  def parameters: Parser[Any] = "parameters" ~ ":" ~ "[" ~ repsep(parameter, ",") ~ "]"

  def rules : Parser[Any] = "rules" ~ ":" ~ "[" ~ repsep(rule, ",") ~ "]"

  def parameter: Parser[Any] = "{" ~ "pname" ~ ":" ~ stringLit ~ "," ~ "pvalues" ~ ":" ~ vrange ~ "}" ^^ {
    case _ ~ _ ~ _ ~ pn ~ _ ~ _ ~ _ ~ _ ~ _ => ruleModel(pn)
  }

  def vrange: Parser[Any] = "[" ~ repsep(stringLit, ",") ~ "]"

  def rule: Parser[Any] = "{" ~ "rname" ~ ":" ~ stringLit ~ "," ~ rif ~ "," ~ rthen ~ "}" ^^ {
    p1 => ruleModel("rule")
  }

  def rif: Parser[JLeafs] = "rif" ~ ":" ~ (rexpr | obj) ^^ {
    case x ~ obj => JAtom("a","b")
    case x ~ rexpr => JExpr("test", JAtom("a","b"), JAtom("a","b"))
  }

  def rthen: Parser[JLeafs] = "rthen" ~ ":" ~ (rexpr | obj) ^^ { case x ~ obj => JAtom("a","b") }

  def rexpr : Parser[Any] = "[" ~ rops ~ "," ~ obj ~ "," ~ (rexpr | obj) ~ "]"
  def rops : Parser[String] = "AND" | "OR" | "NOT"

}

// This class is used as entry point for building an AST
//
case class ruleModel(t: String){
      println(t)
}