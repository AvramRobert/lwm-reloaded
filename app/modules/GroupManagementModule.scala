package modules

import controllers.crud.GroupCRUDController


trait GroupManagementModule {
  self: SemanticRepositoryModule =>
  def groupManagementController: GroupCRUDController
}

trait DefaultGroupManagementModuleImpl extends GroupManagementModule {
  self: SemanticRepositoryModule with BaseNamespace =>
  lazy val groupManagementController: GroupCRUDController = new GroupCRUDController(repository, namespace)
}
