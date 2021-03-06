package utils

import akka.util.Timeout
import play.api.Play.current
import play.api.libs.concurrent.Akka
import play.api.mvc._
import play.api.{Application, GlobalSettings, Logger}
import store.{Namespace, SesameRepository}

import scala.concurrent.duration._

trait DefaultTimeout {
  implicit val timeout = Timeout(5.seconds)
}

object Global extends GlobalDef with DefaultTimeout

trait GlobalDef extends GlobalSettings {
  lazy val serviceName = "lwm"

  implicit def timeout: Timeout

  override def onStart(app: Application) {
    val bindHost = app.configuration.getString("lwm.bindHost").get
    val bindPort = app.configuration.getInt("lwm.bindPort").get
    val dn = app.configuration.getString("lwm.bindDN").get
    val gdn = app.configuration.getString("lwm.groupDN").get
    val lifetime = app.configuration.getInt("lwm.sessions.lifetime").get

    Logger.debug("Application has started")
  }

  override def onStop(app: Application) {
    Logger.debug("Application shutdown...")
  }

  override def onRouteRequest(req: RequestHeader): Option[Handler] = {
    super.onRouteRequest(req)
  }
}