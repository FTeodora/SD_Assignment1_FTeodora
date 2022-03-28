package presentation.controller;

import entity.LegalDocumentType;
import service.AdminService;

import java.util.List;

public class ManageDocumentTypesController extends AfterLogInController{
    public ManageDocumentTypesController(AfterLogInController previous) {
        super(previous);
    }
    public List<LegalDocumentType> fetchDocumentTypes(){
        return ((AdminService)this.service).viewDocumentTypes();
    }
    public boolean addType(LegalDocumentType newType){
        return ((AdminService)this.service).addDocumentType(newType);
    }
    public boolean editType(LegalDocumentType newType){
        return ((AdminService)service).editDocumentType(newType);
    }
}
