package presentation.view;


import entity.PersonalProperty;
import presentation.controller.AfterLogInController;
import presentation.controller.ManagePropertiesController;

public class ManageMyPropertiesWindow extends ManagementWindow<PersonalProperty> {

    public ManageMyPropertiesWindow( AfterLogInWindow previous) {
        super("Manage my private properties in Cluj", previous,"You have no private properties at the moment","Why don't you try adding one?");
        this.addInsertNewEntityOption(PersonalProperty.class,
                p->((ManagePropertiesController)controller).addProperty(p),"personal property",true
        );
        this.controller=new ManagePropertiesController((AfterLogInController) previous.controller);
        this.managedEntities.addAction("delete",this.generateListenerForWindowButton(
                o->((ManagePropertiesController)controller).deletePersonalProperty(o),"delete"),o->true);
        refresh();
    }
    public void refresh(){
        this.managedEntities.refresh(((ManagePropertiesController)controller).fetchProperties());
        super.refresh();
    }
}
