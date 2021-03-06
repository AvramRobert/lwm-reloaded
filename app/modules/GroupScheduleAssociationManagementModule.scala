package modules

import controllers.crud.GroupScheduleAssociationCRUDController


trait GroupScheduleAssociationManagementModule {
  self: SemanticRepositoryModule =>
  def groupScheduleAssociationManagementController: GroupScheduleAssociationCRUDController
}

trait DefaultGroupScheduleAssociationManagementModuleImpl extends GroupScheduleAssociationManagementModule {
  self: SemanticRepositoryModule with BaseNamespace =>
  lazy val groupScheduleAssociationManagementController: GroupScheduleAssociationCRUDController = new GroupScheduleAssociationCRUDController(repository, namespace)
}
