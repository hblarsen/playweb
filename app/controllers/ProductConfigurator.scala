package controllers

import models.Rules.{Configurator, JConfigurationFactory}
import play.api.mvc.{Action, Controller}

// Controller for building the data structures used for displayin the parameters

object ProductConfigurator extends Controller{

  val configurator = Configurator

  val list = Action {
    Ok(views.html.Application.parameters((new JConfigurationFactory).load("./data/testconfig.json").parameters))
  }

  def init() = Action{
    //configurator.initParams()
    Ok("Hello Init")
  }
}
