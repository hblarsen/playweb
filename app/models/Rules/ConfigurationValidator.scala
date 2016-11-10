package models.Rules {

  // A temporary solution used for frontend development

  trait Configurator {

    var rules = Map.empty[String, JRule]
    var parameters = Map.empty[String, JParameter]

    def addParam(name: String): Option[JParameter]

    def getParams: Seq[JParameter]

    def addRule(name: String, rif: Seq[String], rthen: Seq[String] ): Option[JRule]

    def getRules: Seq[JRule]
  }

  object Configurator extends Configurator {

    //private val pseq = new AtomicLong
    //private val rseq = new AtomicLong

    def addParam(name: String): Option[JParameter] = {
      //val id = pseq.incrementAndGet()

      val param = JParameter("HD", "enum", List("SSD", "HDD"))

      parameters += param.pname -> param

      Some(param)
    }

    def getParams: Seq[JParameter] = parameters.values.to[Seq]

    def initParams(): Option[JParameter] = {
      addParam("One")
      addParam("Two")
    }

    def addRule(rname: String, rif: Seq[String], rthen: Seq[String] ): Option[JRule] = {

      //val id = rseq.incrementAndGet()
      val l = JExpr("", JAtom("one", "two"), JAtom("",""))
      val r = JExpr("", JAtom("one", "two"), JAtom("",""))

      val rule = JRule(rname, l, r)
      rules += rule.rname -> rule
      Some(rule)
    }

    def getRules: Seq[JRule] = rules.values.to[Seq]
  }

}