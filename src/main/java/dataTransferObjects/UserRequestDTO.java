package dataTransferObjects;

import entity.LegalDocumentType;
import entity.Request;
import entity.enums.RequestStatus;
import presentation.util.formConverter.SwingField;
import presentation.util.formConverter.fields.JTextAreaFieldForm;
import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;

import java.util.Date;

public class UserRequestDTO {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SwingField(labelName = "Add a message(optional):", componentType = JTextAreaFieldForm.class)
    @SwingWindow(positionInWindow = FieldPosition.LEFT)
    private String message;
    @SwingWindow(additionalLabel = "status: ", positionInWindow = FieldPosition.RIGHT)
    private RequestStatus status;
    @SwingWindow(additionalLabel = "\nSubmitted:")
    private Date requestedAt;
    @SwingWindow(positionInWindow = FieldPosition.UP)
    private LegalDocumentType type;

    public LegalDocumentType getType() {
        return type;
    }

    public void setType(LegalDocumentType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public UserRequestDTO(){

    }
    public UserRequestDTO(Request request){
        this.message=request.getUserMessage();
        this.requestedAt=request.getRequestedAt();
        this.status=request.getStatus();
        this.type=request.getDocumentType();
        this.id=request.getId();

    }
    public Request toRequest(){
        Request r=new Request();
        r.setId(id);
        r.setUserMessage(message);
        r.setRequestedAt(requestedAt);
        r.setDocumentType(type);
        r.setStatus(status);
        return r;
    }
}
