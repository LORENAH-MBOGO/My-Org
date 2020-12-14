package dao;

import models.organizationUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oOrganizationUserDaoTest {

    private static Sql2oOrganizationUserDao userDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/database.sql'";
        Sql2o sql2o = new Sql2o(connectionString,""," ");

        userDao = new Sql2oOrganizationUserDao (sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        userDao.clearAllUsers();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    organizationUser testUser = new organizationUser("Skylar","Intern","Programming",1);

    @Test
    public void checkWhetherAUserIsAddedInTheDB(){
        userDao.addUser(testUser);
        assertEquals(1,userDao.getAllUsers().size());
    }

    @Test
    public void checkSetsIdWhenAUserIsAdded(){
        int theTestUserId = testUser.getUserId();
        userDao.addUser(testUser);
        assertNotEquals(theTestUserId, testUser.getUserId());
    }

    @Test
    public void retrievesAllUsersThatAreAdded(){
        organizationUser testUser1 = new organizationUser("Skylar","Intern","Programming",1);
        userDao.addUser(testUser);
        userDao.addUser(testUser1);
        assertEquals(2,userDao.getAllUsers().size());
    }

//    @Test
//    public void retrievesAUserInAnOrganizationByAnId(){
//        userDao.addUser(testUser);
//        organizationUser retrievedUser = userDao.findUserById(testUser.getUserId());
//        assertEquals(testUser,retrievedUser);
//    }

    @Test
    public void updateCorrectlyAllUserFields() throws Exception {
        userDao.addUser(testUser);
        userDao.updateUser(testUser.getUserId(), "Alpha", "HR", "Manager", 2);
        organizationUser retrievedUser = userDao.findUserById(testUser.getUserId());
        assertNotEquals(testUser.getUserName(), retrievedUser.getUserName());
        assertNotEquals(testUser.getUserPosition(), retrievedUser.getUserPosition());
        assertNotEquals(testUser.getUserRole(), retrievedUser.getUserRole());
        assertNotEquals(testUser.getUserDepartmentId(), retrievedUser.getUserDepartmentId());
    }

    @Test
    public void deletesAUserByTheId(){
        organizationUser testUser1 = new organizationUser("Skylar","Intern","Programming",1);
        userDao.addUser(testUser);
        userDao.addUser(testUser1);
        userDao.deleteUserById(testUser1.getUserId());
        assertEquals(1, userDao.getAllUsers().size());
    }

    @Test
    public void deletesAllUserFromTheDB(){
        organizationUser testUser1 = new organizationUser("Skylar","Intern","Programming",1);
        userDao.addUser(testUser);
        userDao.addUser(testUser1);
        userDao.clearAllUsers();
        assertEquals(0, userDao.getAllUsers().size());
    }

}