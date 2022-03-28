package entity;

import entity.enums.RequestStatus;
import presentation.util.formConverter.SwingField;
import presentation.util.formConverter.fields.JTextAreaFieldForm;
import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="request")
public class Request {
    @Id
    @Column(name="request_id")
    private String id;
    @Column
    private String userMessage;
    @Column(nullable = false)
    private Date requestedAt;

    @Column
    private RequestStatus status;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requester_id", referencedColumnName = "user_id")
    private User requester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id",referencedColumnName = "document_type_id")
    private LegalDocumentType documentType;
    public Request(){}

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

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public LegalDocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(LegalDocumentType documentType) {
        this.documentType = documentType;
    }
    public void autofillFields(){
        this.id= UUID.randomUUID().toString();
        this.requestedAt=new Date();
        this.status=RequestStatus.WAITING;
    }
}
