package dao;

import models.organizationUser;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oOrganizationUserDao implements organizationUserDao {

    private final Sql2o sql2o;

    public Sql2oOrganizationUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addUser(organizationUser user) {
        String sql ="INSERT INTO users (name, position, role, departmentId) values (:userName, :userPosition, :userRole, :userDepartmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setUserId(id);
        }
    }

    @Override
    public List<organizationUser> getAllUsers() {
        String sql="SELECT * FROM users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(organizationUser.class);
        }

    }

    @Override
    public organizationUser findUserById(int userId) {
        String sql ="SELECT * FROM users where id = :userId ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("userId",userId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(organizationUser.class);
        }
    }

    @Override
    public void updateUser(int userId, String userName, String userPosition, String userRole, int userDepartmentId) {
        String sql = "UPDATE users SET  (name,position,role,departmentId) = (:userName,:userPosition,:userRole,:userDepartmentId) where id= :userId ";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("userName",userName)
                    .addParameter("userPosition",userPosition)
                    .addParameter("userRole",userRole)
                    .addParameter("userDepartmentId",userDepartmentId)
                    .addParameter("userId",userId)
                    .executeUpdate();

//            user.setUserName(userName);
//            user.setUserPosition(userPosition);
//            user.setUserRole(userRole);
//            user.setUserDepartmentId(userDepartmentId);
        }
    }

    @Override
    public void deleteUserById(int userId) {
        String sql = "DELETE FROM users WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", userId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllUsers() {
        String sql = "DELETE FROM users ";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }
}