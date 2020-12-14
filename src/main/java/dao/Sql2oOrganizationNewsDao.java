package dao;

import models.organizationDepartmentNews;
import models.organizationGeneralNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oOrganizationNewsDao implements organizationNewsDao{

    private final Sql2o sql2o;
    public static final String GENERAL_NEWS="general";
    public static final String DEPARTMENT_NEWS="department";

    public Sql2oOrganizationNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addOrganizationGeneralNews(organizationGeneralNews generalNews) {
        String sql = "INSERT INTO news (userId,type,data) values (:newsUserId,:newsType,:newsData) ";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(generalNews)
                    .executeUpdate()
                    .getKey();
            generalNews.setNewsId(id);
        }
    }

    @Override
    public void addOrganizationDepartmentsNews(organizationDepartmentNews departmentNews) {
        String sql =" INSERT INTO news (userId,type,content,departmentId) values (:userId,:type,:content,now(),:departmentId) ";
        try(Connection con = sql2o.open()){
            int id = (int)  con.createQuery(sql,true)
                    .addParameter("userId", departmentNews.getNewsUserId())
                    .addParameter("type",departmentNews.getNewsType())
                    .addParameter("data",departmentNews.getNewsData())
                    .addParameter("departmentId",departmentNews.getDepartmentNewsId())
                    .executeUpdate().getKey();
            departmentNews.setNewsId(id);
        }
    }

    @Override
    public List<organizationGeneralNews> getAllGeneralNews() {
        String sql = "SELECT * FROM news WHERE type=:type";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type",GENERAL_NEWS)
                    .executeAndFetch(organizationGeneralNews.class);
        }

    }

    @Override
    public List<organizationDepartmentNews> getAllDepartmentNews() {
        String sql = "SELECT * FROM news WHERE type=:type";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .executeAndFetch(organizationDepartmentNews.class);
        }
    }

    @Override
    public organizationGeneralNews findGeneralNewsById(int generalNewsId) {
        String sql = "SELECT * FROM news WHERE type=:type and id=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type",GENERAL_NEWS)
                    .addParameter("id",generalNewsId)
                    .executeAndFetchFirst( organizationGeneralNews.class);
        }

    }

    @Override
    public organizationDepartmentNews findDepartmentNewsById(int departmentNewsId) {
        String sql = "SELECT * FROM news WHERE type=:type and id=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .addParameter("id",departmentNewsId)
                    .executeAndFetchFirst(organizationDepartmentNews.class);
        }
    }

    @Override
    public void updateGeneralNews(organizationGeneralNews generalNews, String newsData, int userId) {
        String sql = "UPDATE news SET (userId, content) = (:userId, :content)  where id=:id ";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("data",newsData)
                    .addParameter("id",generalNews.getNewsId())
                    .executeUpdate();
            generalNews.setNewsUserId(userId);
            generalNews.setNewsData(newsData);
        }

    }

    @Override
    public void updateDepartmentNews(organizationDepartmentNews departmentNews, String newsData, int departmentId, int userId){
        String sql = "up (userId, content,departmentId) = (:userId,  :content,:departmentId)  where id=:id ";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("data",newsData)
                    .addParameter("departmentId",departmentId)
                    .addParameter("id",departmentNews.getNewsId())
                    .executeUpdate();
            departmentNews.setNewsUserId(userId);
            departmentNews.setNewsData(newsData);
            departmentNews.setDepartmentNewsId(departmentId);
        }
    }

    @Override
    public void deleteGeneralNewsById(int newsId) {
        String sql = "DELETE FROM news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", newsId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteDepartmentNewsById(int newsId) {
        String sql = "DELETE FROM news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", newsId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearGeneralNews() {
        String sql="DELETE FROM news WHERE type = :type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",GENERAL_NEWS)
                    .executeUpdate();
        }
    }

    @Override
    public void clearDepartmentNews() {
        String sql="DELETE FROM news WHERE type = :type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAllNews() {
        String sql="DELETE FROM news ";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }
}