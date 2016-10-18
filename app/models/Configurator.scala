package models

import java.util.concurrent.atomic.AtomicLong

import scala.collection.concurrent.TrieMap

/**
  * Created by HEBL on 17-10-2016.
  */
  case class Param (id: Long, name: String)

  trait Configurator {
    def getParams(): Seq[Param]
    def addParam(name: String): Option[Param]

    //def get(id: Long): Option[Params]
    //def update(id: Long, name: String): Option[Params]
    //def delete(id: Long): Boolean
  }

  object Configurator extends Configurator{

    private val params = TrieMap.empty[Long, Param]
    private val seq = new AtomicLong

    def getParams(): Seq[Param] = params.values.to[Seq]

    def addParam(name: String): Option[Param] = {
      val id = seq.incrementAndGet()
      val param = Param(id, name)
      params.put(id, param)
      Some(param)
    }

    def initParams(): Option[Param] = {
      addParam("One")
      addParam("Two")
    }
  }