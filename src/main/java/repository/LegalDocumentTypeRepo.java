package repository;

import entity.LegalDocumentType;

import java.util.List;

public class LegalDocumentTypeRepo extends AbstractRepo<LegalDocumentType> {
    public List<LegalDocumentType> getAllTypes(){
        return this.getAllEntities(LegalDocumentType.class);
    }
    public boolean typeExists(String name){
        LegalDocumentType legalDocumentType=new LegalDocumentType();
        legalDocumentType.setTypeName(name);
        return !super.findByCriteria(legalDocumentType, LegalDocumentType.class).isEmpty();
    }
    public boolean addDocumentType(LegalDocumentType newType){
        return super.insertNewEntity(newType);
    }
    public boolean editDocumentType(LegalDocumentType newType){
        return super.updateInfo(newType,LegalDocumentType.class);
    }
    public List<LegalDocumentType> getBuildingDocumentTypes(){
        LegalDocumentType type=new LegalDocumentType();
        type.setForBuildings(true);
        return super.findByCriteria(type,LegalDocumentType.class);
    }
}
