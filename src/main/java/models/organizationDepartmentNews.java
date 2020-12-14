package models;

import java.util.Objects;

public class organizationDepartmentNews extends organizationGeneralNews {
    private int departmentNewsId;

    public organizationDepartmentNews( String newsInformation, String newsType, int newsUserId, int departmentNewsId) {
        super( newsInformation, newsType, newsUserId);
        this.departmentNewsId = departmentNewsId;
    }

    public int getDepartmentNewsId() {
        return departmentNewsId;
    }

    public void setDepartmentNewsId(int departmentNewsId) {
        this.departmentNewsId = departmentNewsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        organizationDepartmentNews that = (organizationDepartmentNews) o;
        return departmentNewsId == that.departmentNewsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentNewsId);
    }
}