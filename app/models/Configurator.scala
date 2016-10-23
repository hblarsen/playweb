package models

import java.util.concurrent.atomic.AtomicLong

import scala.collection.concurrent.TrieMap

  trait Configurator {

    var rules = Map.empty[String, Rule]
    var parameters = Map.empty[String, Parameter]

    def addParam(name: String): Option[Parameter]
    def getParams: Seq[Parameter]
    def addRule(name: String): Option[Rule]
    def getRules: Seq[Rule]
  }

  object Configurator extends Configurator{

    //private val pseq = new AtomicLong
    //private val rseq = new AtomicLong

    def addParam(name: String): Option[Parameter] = {
      //val id = pseq.incrementAndGet()

      val param = Parameter("HD","enum", List("SSD","HDD"))

      parameters += param.pname -> param

      Some(param)
    }

    def getParams: Seq[Parameter] = parameters.values.to[Seq]

    def initParams(): Option[Parameter] = {
      addParam("One")
      addParam("Two")
    }

    def addRule(name: String): Option[Rule] = {
      //val id = rseq.incrementAndGet()
      val rule = Rule(name)
      rules += rule.rname -> rule
      Some(rule)
    }

    def getRules: Seq[Rule] = rules.values.to[Seq]
  }