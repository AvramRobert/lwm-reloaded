package utils

import controllers._
import modules._
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import router.Routes

class LwmApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new DefaultLwmApplication(context).application
  }
}

trait DefaultHomepageModuleImpl extends HomepageModule {
  lazy val homepageController = new HomepageController
}

trait HomepageModule {
  def homepageController: HomepageController
}

trait AssetsModule {
  self: LwmApplication =>
  def assetsController: Assets
}

trait DefaultAssetsModuleImpl extends AssetsModule {
  self: LwmApplication =>
  lazy val assetsController = new Assets(httpErrorHandler)
}

abstract class LwmApplication(context: Context) extends BuiltInComponentsFromContext(context)
with ConfigurationModule
with BaseNamespace
with HomepageModule
with SemanticRepositoryModule
with DegreeManagementModule
with CourseManagementModule
with EmployeeManagementModule
with GroupManagementModule
with GroupScheduleAssociationManagementModule
//with GroupScheduleManagementModule
with LabworkManagementModule
with RoomManagementModule
with SemesterManagementModule
with StudentManagementModule
with StudentScheduleAssociationManagementModule
//with StudentScheduleManagementModule
//with TimetableManagementModule
with TimetableEntryManagementModule
with SessionRepositoryModule
with SessionControllerModule
with AkkaActorSystemModule
with AssetsModule {
  lazy val router: Router = new Routes(
    httpErrorHandler,
    homepageController,
    degreeManagementController,
    courseManagementController,
    employeeManagementController,
    groupManagementController,
    groupScheduleAssociationManagementController,
    //groupScheduleManagementController,
    labworkManagementController,
    roomManagementController,
    semesterManagementController,
    studentManagementController,
    studentScheduleAssociationManagementController,
    //studentScheduleManagementController,
    //timetableManagementController,
    timetableEntryManagementController,
    sessionController,
    assetsController
  )
}

class DefaultLwmApplication(context: Context) extends LwmApplication(context)
with ConfigurationModuleImpl
with ConfigurableBaseNamespace
with DefaultSemanticRepositoryModuleImpl
with DefaultHomepageModuleImpl
with DefaultDegreeManagementModuleImpl
with DefaultCourseManagementModuleImpl
with DefaultEmployeeManagementModuleImpl
with DefaultGroupManagementModuleImpl
with DefaultGroupScheduleAssociationManagementModuleImpl
//with DefaultGroupScheduleManagementModuleImpl
with DefaultLabworkManagementModuleImpl
with DefaultRoomManagementModuleImpl
with DefaultSemesterManagementModuleImpl
with DefaultStudentManagementModuleImpl
with DefaultStudentScheduleAssociationManagementModuleImpl
//with DefaultStudentScheduleManagementModuleImpl
//with DefaultTimetableManagementModuleImpl
with DefaultTimetableEntryManagementModuleImpl
with LDAPAuthenticatorModule
with DefaultSessionRepositoryModuleImpl
with DefaultAssetsModuleImpl
with DefaultSessionControllerModuleImpl
