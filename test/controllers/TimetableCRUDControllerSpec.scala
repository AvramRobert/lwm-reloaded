package controllers

import models.timetable.Timetable
import play.api.libs.json.Writes

class TimetableCRUDControllerSpec extends AbstractCRUDControllerSpec[Timetable] {
  override val entityToPass: Timetable = Timetable()

  override def entityTypeName: String = "Timetable"

  override val controller: AbstractCRUDController[Timetable] = new TimetableCRUDController(repository, namespace)

  override val entityToFail: Timetable = Timetable()

  override implicit val jsonWrites: Writes[Timetable] = Timetable.writes

  override val mimeType: String = "application/json" //TODO: this should be a proper content type
}
