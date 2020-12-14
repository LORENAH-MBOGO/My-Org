package models;

import java.util.Objects;

public class organizationUser {
    private int userId;
    private String userName;
    private String userPosition;
    private String userRole;
    private int userDepartmentId;


    public organizationUser(String userName, String userPosition, String userRole, int userDepartmentId) {
        this.userName = userName;
        this.userPosition = userPosition;
        this.userRole = userRole;
        this.userDepartmentId =  userDepartmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(int userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        organizationUser that = (organizationUser) o;
        return userId == that.userId &&
                userName.equals(that.userName) &&
                userPosition.equals(that.userPosition) &&
                userRole.equals(that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPosition, userRole);
    }
}