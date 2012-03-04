package controllers

import play.api.mvc._
import play.api.templates.Html
import models._

object Complex extends Controller {

  def index = pjaxAction { implicit template =>
    Ok(views.html.complex.index(Gist.findAll))
  }

  def detail(id: Int) = pjaxAction { implicit template =>
    Ok(views.html.complex.detail(Gist.findById(id)))
  }

  private def pjaxAction(f: (String => Html => Html) => Result) =
    Action { request =>
      if (request.headers.keys("X-PJAX")) {
        f(views.html.complex.pjaxTemplate.apply)
      } else {
        val user = User.findById(10).get // call domain logic
        f(views.html.complex.fullTemplate.apply(user))
      }
    }

}
