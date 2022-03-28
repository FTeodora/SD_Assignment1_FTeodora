package dataTransferObjects;

import entity.enums.Roles;

public class SessionDTO {
    private final String id;
    private final String eMail;
    private final Roles permission;
    private final String name;
    public SessionDTO(LogInDTO loggedIn){
        this.eMail= loggedIn.geteMail();
        this.permission=loggedIn.getUser().getRole();
        this.name=loggedIn.getUser().getFirstName();
        this.id=loggedIn.getUser().getId();
    }

    public String geteMail(){ return this.eMail;}
    public Roles getPermission(){ return this.permission;}
    public String getName(){ return this.name;}
    public String getId(){ return this.id;}
}
