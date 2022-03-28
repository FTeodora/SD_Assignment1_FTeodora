package entity;

import org.hibernate.annotations.ColumnDefault;
import presentation.util.formConverter.SwingField;
import presentation.util.formConverter.fields.CheckBoxFieldForm;
import presentation.util.formConverter.fields.JTextAreaFieldForm;
import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="legal_document_type")
public class LegalDocumentType {
    @Id
    @Column(name="document_type_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int typeId;
    @OneToMany(mappedBy = "documentType",fetch=FetchType.LAZY)
    private Set<Request> referencedRequests;
    @Column(unique = true)
    @SwingField(labelName = "Type name")
    @SwingWindow(positionInWindow = FieldPosition.UP)
    private String typeName;
    @Column
    @SwingField(componentType = JTextAreaFieldForm.class)
    @SwingWindow(positionInWindow = FieldPosition.LEFT)
    private String description;

    @Column
    @ColumnDefault("false")
    @SwingField(labelName = "is related to buildings?",componentType = CheckBoxFieldForm.class)
    @SwingWindow(additionalLabel = "\nBuilding related: ",positionInWindow = FieldPosition.RIGHT)
    private boolean forBuildings;

    public boolean isForBuildings() {
        return forBuildings;
    }

    public void setForBuildings(boolean forBuildings) {
        this.forBuildings = forBuildings;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Set<Request> getReferencedDocuments() {
        return referencedRequests;
    }

    public void setReferencedDocuments(Set<Request> referencedDocuments) {
        this.referencedRequests = referencedDocuments;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return this.typeName;
    }
}
