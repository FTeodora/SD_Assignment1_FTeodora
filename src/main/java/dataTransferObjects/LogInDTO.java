package dataTransferObjects;

import entity.User;

public class LogInDTO {
    private final String eMail;
    private final char[] password;
    private User user;
    public LogInDTO(String eMail,char[] password){
        this.eMail=eMail;
        this.password=password;
    }
    public String geteMail(){return this.eMail;}
    public char[] getPassword(){return this.password;}
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){return this.user;}

}
