package utils

import play.api.http.{MimeTypes, ContentTypes}
import play.api.mvc.Accepting

object LWMMimeTypes {
  /* protocol
  *   username
  *   password */
  val loginV1Json = "application/vnd.fhk.login.V1+json"

  /* protocol
  *   systemId
  *   lastname
  *   firstname
  *   email
  */
  val userV1Json = "application/vnd.fhk.user.V1+json"

  /* protocol
  *   systemId
  *   lastname
  *   firstname
  *   email
  *   registrationId */
  val studentV1Json = "application/vnd.fhk.student.V1+json"
}

object LWMContentTypes extends ContentTypes {

  import play.api.mvc.Codec

  def loginV1ContentType(implicit codec: Codec) = withCharset(LWMMimeTypes.loginV1Json)

  def userV1ContentType(implicit codec: Codec) = withCharset(LWMMimeTypes.userV1Json)

  def studentV1ContentType(implicit codec: Codec) = withCharset(LWMMimeTypes.studentV1Json)
}

object LWMAccepts {
  val LoginV1Accept = Accepting(LWMMimeTypes.loginV1Json)
  val UserV1Accept = Accepting(LWMMimeTypes.userV1Json)
  val StudentV1Accept = Accepting(LWMMimeTypes.studentV1Json)
}
