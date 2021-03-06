package controllers.crud

import java.util.UUID

import models.{Degree, DegreeProtocol}
import play.api.libs.json.{JsValue, Json, Writes}

class DegreeCRUDControllerSpec extends AbstractCRUDControllerSpec[DegreeProtocol, Degree] {
  override val entityToPass: Degree = Degree("label to pass", Degree.randomUUID)

  override val controller: AbstractCRUDController[DegreeProtocol, Degree] = new DegreeCRUDController(repository, namespace) {
    override protected def fromInput(input: DegreeProtocol, id: Option[UUID]): Degree = entityToPass
  }

  override val entityToFail: Degree = Degree("label to fail", Degree.randomUUID)

  override implicit val jsonWrites: Writes[Degree] = Degree.writes

  override val mimeType: String = "application/json" //TODO: this should be a proper content type

  override def entityTypeName: String = "Degree"

  override val inputJson: JsValue = Json.obj(
    "label" -> "label input"
  )
}