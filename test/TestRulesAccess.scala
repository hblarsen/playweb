/**
  * Created by HEBL on 20-10-2016.
  */
import org.specs2.mutable.Specification
import models.Rules._
import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

class TestRulesAccess extends Specification{

  val f: String = "./data/testconfig.json"

  "Rules" should {

    "exist in JSON file" in {
      //Play.application().classloader().getResource("any_file");
      //Play.application().getFile("")
      //val rulesFile = Play.application().getFile("//data//testconfig.json") //only running applications
      //val rulesFile = ClassLoader.getSystemResource("c:\\tmp\\data.txt").getFile
      val rulesFile = scala.io.Source.fromFile(f).mkString
      rulesFile.length > 0 must beTrue
    }

    "deserialize" in {
      val rulesFile = scala.io.Source.fromFile(f).mkString
      val p = (new JConfigurationFactory).load(rulesFile).parameters
      p.length > 0 must beTrue
    }

  }
}
