package controllers

import play.api.mvc.{Controller, Action}
import models.Configurator

/**
  * Created by HEBL on 17-10-2016.
  */
object ProductConfigurator extends Controller{

  val configurator = Configurator

  val list = Action {
    Ok(views.html.Application.parameters(configurator.getParams.toList))
  }

  def init() = Action{
    configurator.initParams()
    Ok("Hello Init")
  }

  //def show(id: Long) = Action {
    //configurator.findById(id).map { client =>
      //Ok(views.html.Application.params(configurator.list().toList))
    //}.getOrElse(NotFound)
  //}
}
