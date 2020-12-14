package dao;

import models.organizationDepartment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oOrganizationDepartmentDaoTest {

    private static  Sql2oOrganizationDepartmentDao departmentDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/database.sql'";
        Sql2o sql2o = new Sql2o(connectionString,""," ");

        departmentDao = new Sql2oOrganizationDepartmentDao (sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        departmentDao.clearAllDepartments();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    organizationDepartment testDepartment = setUpNewDepartment();

    @Test
    public void addingDepartmentSetsId() throws Exception {
        int originalDepartmentId = testDepartment.getDepartmentId();
        departmentDao.addDepartment(testDepartment);
        assertNotEquals(originalDepartmentId,testDepartment.getDepartmentId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAllDepartments() throws Exception {
        departmentDao.addDepartment(testDepartment);
        assertEquals(1, departmentDao.getAllDepartments().size());
    }

    @Test
    public void returnsEmptyListIfNoDepartmentExists() throws Exception {
        assertEquals(0, departmentDao.getAllDepartments().size());
    }

//    @Test
//    public void getASpecificDepartmentUsingID() throws Exception {
//        departmentDao.addDepartment(testDepartment);
//        organizationDepartment retrievedDepartment = departmentDao.findDepartmentById(testDepartment.getDepartmentId());
//        assertEquals(testDepartment, retrievedDepartment);
//    }

    @Test
    public void deletesADepartmentOnDeletionById() throws Exception {
        departmentDao.addDepartment(testDepartment);
        departmentDao.deleteDepartmentById(testDepartment.getDepartmentId());
        assertEquals(0, departmentDao.getAllDepartments().size());
    }

    @Test
    public void clearAll() throws Exception {
        organizationDepartment otherDepartment = setUpNewDepartment();
        departmentDao.clearAllDepartments();
        assertEquals(0, departmentDao.getAllDepartments().size());
    }


    // helpers

    public organizationDepartment setUpNewDepartment(){
        return new organizationDepartment("IT","Software");
    }


}