package dtos;

import entities.Corona;
import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String userName;
    private String userPass;
    private List<Role> roleList;
    private String role;
    private List <Corona> corona;
    private boolean coronaStatus;



    public UserDTO(String userName, String userPass, List <Role> roleList, List<Corona> corona) {
        this.userName = userName;
        this.userPass = userPass;
        this.roleList = roleList;
        this.corona = corona;
    }


    public UserDTO(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public UserDTO(User user) {
        int lastindex = user.getCorona().size() - 1;
        this.userName = user.getUserName();
        this.coronaStatus = user.getCorona().get(lastindex).isCovidstatus();
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<Corona> getCorona() {
        return corona;
    }

    public void setCorona(List<Corona> corona) {
        this.corona = corona;
    }
}
