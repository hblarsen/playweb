import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

package models {

  /**
    * Created by HEBL on 20-10-2016.
    */
  /*

case class JRules(rules: Seq[JRule])

case class JRule(rname: String,
                 rif: Option[Seq[Any]],
                 rthen: Option[Seq[Any]])
*/

  case class JConfiguration(cname: String, parameters: Seq[JParameter])

  case class JParameter(pname: String,
                        ptype: String,
                        pvalues: Seq[String])


}
