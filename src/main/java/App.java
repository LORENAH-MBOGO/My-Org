import com.google.gson.Gson;
import dao.Sql2oOrganizationDepartmentDao;
import dao.Sql2oOrganizationNewsDao;
import dao.Sql2oOrganizationUserDao;
import models.organizationDepartment;
import models.organizationDepartmentNews;
import models.organizationGeneralNews;
import models.organizationUser;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        Sql2oOrganizationDepartmentDao departmentDao;
        Sql2oOrganizationNewsDao newsDao;
        Sql2oOrganizationNewsDao departmentNewsDao;
        Sql2oOrganizationUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:/database.sql'";
        Sql2o sql2o = new Sql2o(connectionString,""," ");

        departmentDao = new  Sql2oOrganizationDepartmentDao (sql2o);
        userDao = new Sql2oOrganizationUserDao(sql2o);
        newsDao = new Sql2oOrganizationNewsDao(sql2o);
        departmentNewsDao = new Sql2oOrganizationNewsDao(sql2o);
        conn = sql2o.open();

        post("/departments/new", "application/json", (req, res) -> {
            organizationDepartment department = gson.fromJson(req.body(), organizationDepartment.class);
            departmentDao.addDepartment(department);
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });

        get("/departments", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentDao.getAllDepartments());
        });

        get("/departments/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findDepartmentById(departmentId));
        });

        post("/users/new", "application/json", (req, res) -> {
            organizationUser user = gson.fromJson(req.body(), organizationUser.class);
            userDao.addUser(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        get("/users", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(userDao.getAllUsers());
        });

        get("/users/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(userDao.findUserById(userId));
        });

        post("/news/new", "application/json", (req, res) -> {
            organizationGeneralNews news = gson.fromJson(req.body(), organizationGeneralNews.class);
            newsDao.addOrganizationGeneralNews(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        get("/news", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(newsDao.getAllGeneralNews());
        });

        get("/news/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int newsId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(newsDao.findGeneralNewsById(newsId));
        });

        post("/departmentNews/new", "application/json", (req, res) -> {
            organizationDepartmentNews departmentNews = gson.fromJson(req.body(), organizationDepartmentNews.class);
            departmentNewsDao.addOrganizationDepartmentsNews(departmentNews);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departmentNews);
        });

        get("/news", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentNewsDao.getAllDepartmentNews());
        });

        get("/news/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int newsId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentNewsDao.findDepartmentNewsById(newsId));
        });
    }
}
