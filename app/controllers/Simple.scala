package controllers

import play.api.mvc._
import models._

object Simple extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.simple.index(Gist.findAll))
  }

  def detail(id: Int) = Action { implicit request =>
    Ok(views.html.simple.detail(Gist.findById(id)))
  }

}
