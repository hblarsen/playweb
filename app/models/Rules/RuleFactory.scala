package models.Rules

/**
  * Created by HEBL on 24-10-2016.
  * Produces Rules
  * TODO
  */
class RuleFactory {

  def confval(jr: JRule): Boolean = {
    val rif: Seq[String] = jr.rif
    val rthen: Seq[String] = jr.rthen
    //for(jrule <- rif){
    //  if(jrule.contains("AND")){
      rif.head.contains("AND")
    //  }
    //}
  }

  //def
}
