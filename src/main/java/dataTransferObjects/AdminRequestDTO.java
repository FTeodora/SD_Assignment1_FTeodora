package dataTransferObjects;

import entity.LegalDocumentType;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;
import presentation.util.formConverter.SearchSwingField;
import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;

import java.util.Date;

public class AdminRequestDTO {
    private String id;
    @SearchSwingField(labelName = "User message: ")
    @SwingWindow(positionInWindow = FieldPosition.CENTER)
    private String userMessage;
    @SwingWindow(additionalLabel = "submitted at ",positionInWindow = FieldPosition.LEFT)
    private Date requestedAt;
    @SwingWindow(additionalLabel = "\nstatus: ",positionInWindow = FieldPosition.LEFT)
    private RequestStatus status;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    private String requesterId;

    @SearchSwingField(labelName = "Requester first name:")
    @SwingWindow(additionalLabel = "\nby ",positionInWindow = FieldPosition.RIGHT)
    private String requesterFirstName;

    @SearchSwingField(labelName = "Requester last name:")
    @SwingWindow(additionalLabel = " ",positionInWindow = FieldPosition.RIGHT)
    private String requesterLastName;

    private int typeId;

    @SearchSwingField(labelName = "Document type:")
    @SwingWindow(additionalLabel = " ",positionInWindow = FieldPosition.UP)
    private String typeName;
    public AdminRequestDTO(){}
    public AdminRequestDTO(Request r){
        this.id=r.getId();
        this.userMessage=r.getUserMessage();
        this.requesterId=r.getRequester().getId();
        this.requestedAt=r.getRequestedAt();
        this.requesterFirstName=r.getRequester().getFirstName();
        this.requesterLastName=r.getRequester().getLastName();
        this.status=r.getStatus();
        this.typeId=r.getDocumentType().getTypeId();
        this.typeName=r.getDocumentType().getTypeName();
       // this.forBuildings=r.getDocumentType().isForBuildings();
    }
    public Request toRequest(){
        Request req=new Request();
        req.setId(this.id);
        req.setUserMessage(this.userMessage);
        req.setStatus(this.status);
        req.setRequestedAt(this.requestedAt);

        LegalDocumentType type=new LegalDocumentType();
        //type.setForBuildings(this.forBuildings);
        type.setTypeId(this.typeId);
        type.setTypeName(this.typeName);

        LegalDocumentType typee=new LegalDocumentType();
        typee.setTypeName(typeName);
        typee.setTypeId(typeId);
        req.setDocumentType(typee);
        User u=new User();
        u.setId(this.requesterId);
        u.setFirstName(this.requesterFirstName);
        u.setLastName(this.requesterLastName);
        req.setRequester(u);
        return req;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterFirstName() {
        return requesterFirstName;
    }

    public void setRequesterFirstName(String requesterFirstName) {
        this.requesterFirstName = requesterFirstName;
    }

    public String getRequesterLastName() {
        return requesterLastName;
    }

    public void setRequesterLastName(String requesterLastName) {
        this.requesterLastName = requesterLastName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
