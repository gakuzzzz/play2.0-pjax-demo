package controllers

import play.api.mvc._
import play.api.templates.Html
import models._

object Complex extends Controller {

  private def fullTemplate(user: User)(title: String)(content: Html): Html =
    views.html.complex.fullTemplate(user)(title)(content)

  private def pjaxTemplate(title: String)(content: Html): Html =
    views.html.complex.pjaxTemplate(title)(content)

  private def pjaxAction(f: (String => Html => Html) => Result): Action[AnyContent] =
    Action { request =>
      if (request.headers.keys("X-PJAX")) {
        f(pjaxTemplate)
      } else {
        val user = User.findById(10).get // call domain logic
        f(fullTemplate(user))
      }
    }

  def index = pjaxAction { implicit template =>
    Ok(views.html.complex.index(Gist.findAll))
  }

  def detail(id: Int) = pjaxAction { implicit template =>
    Ok(views.html.complex.detail(Gist.findById(id)))
  }

}
