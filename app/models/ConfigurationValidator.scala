package models

import scala.util.parsing.combinator.syntactical.StandardTokenParsers
/**
  * Created by HEBL on 22-10-2016.
  */
class ConfigurationValidator extends StandardTokenParsers {

    lexical.delimiters += ("{", "}", "[", "]", ":", ",")
    lexical.reserved += ("null", "true", "false")

    def value : Parser[Any] = obj | arr | stringLit | numericLit | "null" | "true" | "false"
    def obj : Parser[Any] = "{" ~ repsep(member, ",") ~ "}"
    def arr : Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
    def member: Parser[Any] = stringLit ~ ":" ~ value


}
