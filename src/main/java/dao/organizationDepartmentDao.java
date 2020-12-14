package dao;

import models.organizationDepartment;
import models.organizationDepartmentNews;
import models.organizationGeneralNews;
import models.organizationUser;

import java.util.List;

public interface organizationDepartmentDao {

    //create
    void addDepartment(organizationDepartment department);

    //read
    List<organizationDepartment> getAllDepartments();
    organizationDepartment findDepartmentById(int departmentId);
//    List<organizationUser> getUsersForADepartment(int userDepartmentId);
//    List<organizationDepartmentNews> getNewsForADepartment(int departmentId);

    //update
    void updateDepartment(organizationDepartment department, String departmentName, String departmentDescription);

    //delete
    void deleteDepartmentById(int departmentId);
    void clearAllDepartments();
}