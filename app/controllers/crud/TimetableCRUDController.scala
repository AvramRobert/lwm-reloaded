//package controllers.crud
//
//import java.util.UUID
//
//import models.UriGenerator
//import models.timetable.{TimetableProtocol, Timetable}
//import org.w3.banana.binder.{ClassUrisFor, FromPG, ToPG}
//import org.w3.banana.sesame.Sesame
//import play.api.libs.json.{Reads, Writes}
//import play.api.mvc.Result
//import store.{Namespace, SesameRepository}
//
//import scala.collection.Map
//
//class TimetableCRUDController(val repository: SesameRepository, val namespace: Namespace) extends AbstractCRUDController[TimetableProtocol, Timetable] {
//   override implicit def rdfWrites: ToPG[Sesame, Timetable] = defaultBindings.TimetableBinding.timetableBinder
//
//   override implicit def rdfReads: FromPG[Sesame, Timetable] = defaultBindings.TimetableBinding.timetableBinder
//
//   override implicit def classUrisFor: ClassUrisFor[Sesame, Timetable] = defaultBindings.TimetableBinding.classUri
//
//   override implicit def uriGenerator: UriGenerator[Timetable] = Timetable
//
//   override implicit def reads: Reads[Timetable] = Timetable.reads
//
//   override implicit def writes: Writes[Timetable] = Timetable.writes
//
//   override def getWithFilter(queryString: Map[String, Seq[String]]): Result = ???
//
//   override protected def fromInput(input: TimetableProtocol, id: Option[UUID]): Timetable = ???
//}
