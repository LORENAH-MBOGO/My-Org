package models;

import java.util.Objects;

public class organizationDepartment {
    private int departmentId;
    private String departmentName;
    private String departmentDescription;

    public organizationDepartment(String departmentName, String departmentDescription) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        organizationDepartment that = (organizationDepartment) o;
        return departmentId == that.departmentId &&
                departmentName.equals(that.departmentName) &&
                departmentDescription.equals(that.departmentDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, departmentDescription);
    }
}
