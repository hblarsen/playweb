import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

/*
This class deserializes the rules configuration file format from Json to classes
 */
package models.Rules {

  class JConfigurationFactory extends Exception {

    val f: String = "./data/testconfig.json"

    val rulesFile = scala.io.Source.fromFile(f).mkString

    implicit val parameterBuilder = (
      (JsPath \ "pname").read[String] and
        (JsPath \ "ptype").read[String] and
        (JsPath \ "pvalues").read[Seq[String]]
      ) (JParameter.apply _)

    implicit val ruleBuilder = (
      (JsPath \ "rname").read[String] and
        (JsPath \ "rif").read[Seq[String]] and
        (JsPath \ "rthen").read[Seq[String]]
      ) (JRule.apply _)

    implicit val configBuilder = (
      (JsPath \ "cname").read[String] and
        (JsPath \ "parameters").read[Seq[JParameter]] and
        (JsPath \ "rules").read[Seq[JRule]]
      ) (JConfiguration.apply _)

    def load(path: String): JConfiguration = {
      val inputJson: JsValue = Json.parse(path)
      inputJson.validate[JConfiguration] match {
      case s: JsSuccess[JConfiguration] =>
        s.get
      case e: JsError =>
        throw new Exception("Error")
      }
    }
  }

  case class JConfiguration(cname: String, parameters: Seq[JParameter], rules:Seq[JRule])

  case class JParameter(pname: String,
                        ptype: String,
                        pvalues: Seq[String])

  case class JRule(rname: String,
                   rif: Seq[String],
                   rthen: Seq[String])

}