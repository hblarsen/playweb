package models.Rules

import scala.language.higherKinds
import scala.language.implicitConversions

import scala.language.postfixOps
import scala.util.parsing.combinator.syntactical.StandardTokenParsers


/**
  * Created by HEBL on 22-10-2016.
  */
class RulesParser extends StandardTokenParsers {

    //Json Syntax
    lexical.delimiters += ("{", "}", "[", "]", ":", ",")
    lexical.reserved += ("null", "true", "false")

    def value : Parser[Any] = obj | arr | stringLit | numericLit | "null" | "true" | "false"
    def obj : Parser[Any] = "{" ~ repsep(member, ",") ~ "}"
    def arr : Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
    def member: Parser[Any] = stringLit ~ ":" ~ value

    //Configuration Syntax
    def configrules : Parser[Any] = "{" ~ keyword("cname") ~ ":" ~ stringLit ~ "," ~ keyword("parameters") ~ ":" ~ parameters ~ "," ~ "rules" ~ ":" ~ rules ~ "}"
    def parameters: Parser[Any] = stringLit ~ ":" ~ "[" ~ repsep(parameter, ",") ~ "]"
    def parameter: Parser[Any] = "{" ~ stringLit ~ ":" ~ stringLit ~ "," ~ stringLit ~ ":" ~ stringLit ~ "," ~ stringLit ~ ":" ~ vrange ~ "}"
    def vrange: Parser[Any] = "[" ~ repsep(stringLit, ",") ~ "]"
    def rules : Parser[Any] = "[" ~ repsep(rule, ",") ~ "]"
    def rule: Parser[Any] = "{" ~ "rname" ~ ":" ~ stringLit ~ "," ~ "rif" ~ ":" ~ rif ~ "," ~ "rthen" ~ ":" ~ rthen ~ "}"
    def rexpr : Parser[Any] = "[" ~ rops ~ "," ~ rif ~ "," ~ rthen ~ "]"
    def rif: Parser[Any] = obj | rexpr
    def rthen: Parser[Any] = rif
    def rops : Parser[Any] = "AND" | "OR" | "NOT"

    //val QUOTED: Parser[String] = "([^"]*)"""".r.map { _ dropRight 1 substring 1}
    //val x: Parser[String] = """\d+""".r.map { _ dropRight 1 substring 1}

    //val identifier: Parser[String] = """\^?[a-zA-Z]+""".r ^^ { s => if (s startsWith "^") s substring 1 else s }
}
