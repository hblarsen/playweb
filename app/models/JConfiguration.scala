import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

package models {

  class JConfigurationFactory() extends Exception{

    def createConfig: JConfiguration = {

      val f: String = "./data/testconfig.json"

      val rulesFile = scala.io.Source.fromFile(f).mkString

      implicit val parameterBuilder = (
        (JsPath \ "pname").read[String] and
          (JsPath \ "ptype").read[String] and
          (JsPath \ "pvalues").read[Seq[String]]
        ) (JParameter.apply _)

      implicit val configBuilder = (
        (JsPath \ "cname").read[String] and
          (JsPath \ "parameters").read[Seq[JParameter]]) (JConfiguration.apply _)

      val inputJson: JsValue = Json.parse(rulesFile)

      inputJson.validate[JConfiguration] match {
        case s: JsSuccess[JConfiguration] =>
          s.get
        case e: JsError =>
          throw new Exception("Error")
      }
    }
  }

  case class JConfiguration(cname: String, parameters: Seq[JParameter])

  case class JParameter(pname: String,
                        ptype: String,
                        pvalues: Seq[String])

  case class JRule(rname: String,
                   rif: Option[Seq[Any]],
                   rthen: Option[Seq[Any]])

}
