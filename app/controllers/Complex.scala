package controllers

import play.api.mvc._
import play.api.templates.Html
import models._

trait Pjax {
  self: Controller =>

  protected def fullTemplate(user: User)(title: String)(content: Html): Html =
    views.html.complex.fullTemplate(user)(title)(content)

  protected def pjaxTemplate(title: String)(content: Html): Html =
    views.html.complex.pjaxTemplate(title)(content)

  protected def pjaxAction(f: (String => Html => Html) => Result): Action[AnyContent] =
    Action { request =>
      if (request.headers.keys("X-PJAX")) {
        f(pjaxTemplate)
      } else {
        val user = User.findById(10).get // call domain logic
        f(fullTemplate(user))
      }
    }

}

object Complex extends Controller with Pjax {

  def index = pjaxAction { implicit template =>
    Ok(views.html.complex.index(Gist.findAll))
  }

  def detail(id: Int) = pjaxAction { implicit template =>
    Ok(views.html.complex.detail(Gist.findById(id)))
  }

}
