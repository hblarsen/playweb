package controllers

import play.api.mvc.{Action, Controller}
import models.{Configurator, JConfiguration, JConfigurationFactory}

/**
  * Created by HEBL on 17-10-2016.
  */
object ProductConfigurator extends Controller{

  val configurator = Configurator

  val list = Action {
    Ok(views.html.Application.parameters((new JConfigurationFactory).createConfig.parameters))
  }

  def init() = Action{
    //configurator.initParams()
    Ok("Hello Init")
  }
}
