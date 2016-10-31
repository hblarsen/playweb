/**
  * Created by HEBL on 31-10-2016.
  */
import org.specs2.mutable.Specification
import models.Rules._
import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

class TestRuleFactory extends Specification{

  "Rules" should {

    "exist in JSON file" in {

      val jr = new JRule("TestRule", Seq("AND","B"), Seq("X","Y"))
      val r = new RuleFactory
      r.confval(jr) must beTrue
    }
  }
}
