import java.io.StringReader

import models.Rules.{DummyParsers, RulesParser, RuleTextParser}
import org.specs2.mutable.Specification

import scala.util.parsing.input.StreamReader
/**
  * Created by HEBL on 22-10-2016.
  */
class TestRulesParser extends Specification {

  val f: String = "./data/ruleconfig.json"
  val tf: String = "./data/testfile.txt"

  "Regexp" should {

    "work" in {

      val x = "bla".r
      println( x.getClass())
      val y = x.findAllIn("blabla bla") //Iterator
      printf(y.toList.toString())
      true must beTrue
    }
  }

  "Parser" should {

    "work" in {

      val reader = StreamReader(new StringReader("bla"))
      val myparser = DummyParsers.apply("test")
      val parseResult = myparser.ALT.apply(reader)

      if(parseResult.successful){
        println("OK Dummy" + parseResult.next)
      }
      else{
        println("Error Dummy")
      }

      true must beTrue
    }
  }

  "Configuration Rules" should {

    "parse" in {
      val rulesFile = scala.io.Source.fromFile(f).mkString

      object JSONTest extends RulesParser {

        def p = {
          //val reader = StreamReader(new java.io.FileReader(f))
          val tokens = new lexical.Scanner(rulesFile)
          println(phrase(configrules)(tokens))
        }
      }

      JSONTest.p

      rulesFile.length > 0 must beTrue
    }
  }

  "Testfile" should {

    "parse" in {
      val rulesFile = scala.io.Source.fromFile(tf).mkString

      object JSONTest extends RuleTextParser {

        def p = {
          //val reader = StreamReader(new java.io.FileReader(f))
          val tokens = new lexical.Scanner(rulesFile)

          val out = phrase(rulesfile)(tokens)
          println(out)
        }
      }

      JSONTest.p

      rulesFile.length > 0 must beTrue
    }
  }
}
