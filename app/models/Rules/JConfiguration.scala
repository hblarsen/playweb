import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

// This class deserializes the rules configuration file format from Json to classes
// Couldnt get the recursion to work, and moved to a parser combinator solution instead
// See RulesParser class

package models.Rules {

  class JConfigurationFactory extends Exception {

    //Implicit constructor code
    val f: String = "./data/testconfig.json"

    val rulesFile = scala.io.Source.fromFile(f).mkString

    implicit val atomBuilder = (
      (JsPath \ "n").read[String] and
        (JsPath \ "v").read[String]
      ) (JAtom.apply _)

    implicit val exprBuilder = (
      (JsPath \ "o").read[String] and
        (JsPath \ "l").read[JAtom] and
        (JsPath \ "r").read[JAtom]
      ) (JExpr.apply _)

    implicit val parameterBuilder = (
      (JsPath \ "pname").read[String] and
        (JsPath \ "ptype").read[String] and
        (JsPath \ "pvalues").read[Seq[String]]
      ) (JParameter.apply _)

    implicit val ruleBuilder = (
      (JsPath \ "rname").read[String] and
        (JsPath \ "rif").read[JExpr] and
        (JsPath \ "rthen").read[JExpr]
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


  sealed abstract class JPart(){
    override def toString: String = {
      "Partly"
    }
  }

  case class JConfiguration(cname: String, parameters: Seq[JParameter], rules:Seq[JRule]) extends JPart{

  }

  case class JParameter(pname: String,
                        ptype: String,
                        pvalues: Seq[String]) extends JPart

  case class JRule(rname: String,
                   rif: JLeafs,
                   rthen: JLeafs) extends JPart

  abstract class JLeafs

  case class JExpr(o: String,
                   l: JAtom,
                   r: JAtom) extends JLeafs

  case class JAtom(n: String, v: String) extends JLeafs{
    override def toString: String = "Atom"
  }

}