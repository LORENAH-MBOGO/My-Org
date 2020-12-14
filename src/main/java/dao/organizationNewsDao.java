package dao;

import models.organizationDepartmentNews;
import models.organizationGeneralNews;

import java.util.List;

public interface organizationNewsDao {
    //create
    void addOrganizationGeneralNews(organizationGeneralNews generalNews);
    void addOrganizationDepartmentsNews(organizationDepartmentNews departmentNews);

    //read
    List<organizationGeneralNews> getAllGeneralNews();
    List<organizationDepartmentNews> getAllDepartmentNews();
    organizationGeneralNews findGeneralNewsById(int generalNewsId);
    organizationDepartmentNews findDepartmentNewsById(int departmentNewsId);

    //update
    void updateGeneralNews(organizationGeneralNews generalNews, String newsData, int userId);
    void updateDepartmentNews(organizationDepartmentNews departmentNews, String newsData, int departmentId, int userId);

    //delete
    void deleteGeneralNewsById(int newsId);
    void deleteDepartmentNewsById(int newsId);
    void clearGeneralNews();
    void clearDepartmentNews();
    void clearAllNews();
}
