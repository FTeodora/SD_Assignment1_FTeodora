package presentation.view;

import entity.LegalDocumentType;
import presentation.controller.AdminController;
import presentation.controller.ManageDocumentTypesController;
import presentation.util.formConverter.Form;

import javax.swing.*;

public class ManageDocumentTypesWindow extends ManagementWindow<LegalDocumentType>{
    Form<LegalDocumentType> newTypeForm;
    JButton newTypeButton=new JButton("Add new type");
    public ManageDocumentTypesWindow(AdminWindow previous) {
        super("Manage document types",previous,"There are no document available types at the moment","Why don't you try creating one yourself?");
        this.controller=new ManageDocumentTypesController((AdminController)previous.controller);
        this.managedEntities.enableEditingMode(generateListenerForEdit("type",
                o->((ManageDocumentTypesController)controller).editType(o)),x->true);
        this.addInsertNewEntityOption(LegalDocumentType.class,o->((ManageDocumentTypesController)controller).addType(o),"type",true);
        refresh();

    }
    public void refresh(){
        this.managedEntities.refresh(((ManageDocumentTypesController)controller).fetchDocumentTypes());
        super.refresh();
    }
}
