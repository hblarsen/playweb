package controllers

import play.api.mvc._

object ConfiguratorManager extends Controller {

  def index = Action {
    Ok(views.html.main())
  }

}
