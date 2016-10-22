/**
  * Created by HEBL on 20-10-2016.
  */
import org.specs2.mutable.Specification
import models._
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

      implicit val parameterBuilder = (
        (JsPath \ "pname").read[String] and
        (JsPath \ "ptype").read[String] and
        (JsPath \ "pvalues").read[Seq[String]]
        )(JParameter.apply _)
      //implicit val parameterReads = parameterBuilder.apply(JParameter.apply _)

      implicit val configBuilder = (
        (JsPath \ "cname").read[String] and
        (JsPath \ "parameters").read[Seq[JParameter]])(JConfiguration.apply _)

      val inputJson: JsValue = Json.parse(rulesFile)

      inputJson.validate[JConfiguration] match{
        case s: JsSuccess[JConfiguration] => {
          val c: JConfiguration = s.get
          println(c.cname)
        }
        case e: JsError => {
          println(e.toString)
        }
      }

      inputJson.toString().length > 0 must beTrue

    }

  }
}
