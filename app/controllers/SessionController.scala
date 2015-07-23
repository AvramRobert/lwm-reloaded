package controllers

import java.util.UUID

import models.{Session, Login}
import play.api.http.LazyHttpErrorHandler
import play.api.http.Status._
import play.api.libs.json.{JsValue, JsError, Json}
import play.api.mvc._
import services.SessionHandlingService
import utils.{LWMMimeTypes, LWMAccepts}

import scala.concurrent.Future

object SessionController {
  val sessionId = "session-id"
}

class SessionController(sessionRepository: SessionHandlingService) extends Controller {

  import scala.concurrent.ExecutionContext.Implicits.global

  def login = Action.async(LWMBodyParser.loginJson) { implicit request =>
    request.body.validate[Login].fold(
      errors => {
        Future.successful(BadRequest(Json.obj(
          "status" -> "KO",
          "errors" -> JsError.toJson(errors)
        )))
      },
      success => {
        sessionRepository.newSession(success.username, success.password).map {
          case s: Session =>
            Ok.withSession(
              SessionController.sessionId -> s.id.toString,
              Security.username -> s.user
            ).as(LWMMimeTypes.loginV1Json)
        }
      }
    )
  }

  def logout = Action.async { implicit request =>
    request.session.get(SessionController.sessionId) match {
      case Some(id) =>
        sessionRepository.deleteSession(UUID.fromString(id)).map { result =>
          if (result) Ok.withNewSession else BadRequest
        }
      case None =>
        Future.successful(Unauthorized)
    }
  }

  def header = Action { implicit request =>
    NoContent.as(LWMMimeTypes.loginV1Json)
  }
}

object LWMBodyParser extends BodyParsers {

  def loginJson: BodyParser[JsValue] = parse.when (
    _.contentType.exists(m => m.equalsIgnoreCase(LWMMimeTypes.loginV1Json)),
    parse.tolerantJson(parse.DefaultMaxTextLength),
    createBadResult(s"Expecting ${LWMMimeTypes.loginV1Json} body", UNSUPPORTED_MEDIA_TYPE)
  )

  private def createBadResult(msg: String, statusCode: Int = BAD_REQUEST): RequestHeader => Future[Result] = { request =>
    LazyHttpErrorHandler.onClientError(request, statusCode, msg)
  }
}