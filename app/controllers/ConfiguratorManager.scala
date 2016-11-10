package controllers

import play.api.mvc._

// Controller for updating and viewing the Rules
// TODO: Implement upload method
// TODO: Implement view method
object ConfiguratorManager extends Controller {

  def index = Action {
    Ok(views.html.main())
  }

}
