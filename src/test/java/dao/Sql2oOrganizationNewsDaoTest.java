package dao;

import models.organizationDepartmentNews;
import models.organizationGeneralNews;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oOrganizationNewsDaoTest {
    private static Sql2oOrganizationNewsDao newsDao;
    private static Sql2oOrganizationNewsDao departmentNewsDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:/database.sql'";
        Sql2o sql2o = new Sql2o(connectionString,""," ");

        newsDao = new Sql2oOrganizationNewsDao(sql2o);
        departmentNewsDao= new Sql2oOrganizationNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        newsDao.clearAllNews();
    }
    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }

    organizationGeneralNews testNews = setUpGeneralNews();

    @Test
    public void setsIdOnAddingNews(){
        int originalUserId = testNews.getNewsId();
        newsDao.addOrganizationGeneralNews(testNews);
        assertNotEquals(originalUserId,testNews.getNewsId());
    }

    @Test
    public void addedGeneralNewsAreReturnedFromGetAllGeneralNews() throws Exception {
        newsDao.addOrganizationGeneralNews(testNews);
        assertEquals(0, newsDao.getAllGeneralNews().size());
    }

    @Test
    public void returnsEmptyListIfNoGeneralNewsExists() throws Exception {
        assertEquals(0, newsDao.getAllGeneralNews().size());
    }

    @Test
    public void deletesAGeneralNewsOnDeletionById() throws Exception {
        newsDao.addOrganizationGeneralNews(testNews);
        newsDao.deleteGeneralNewsById(testNews.getNewsId());
        assertEquals(0, newsDao.getAllGeneralNews().size());
    }

    @Test
    public void clearAll() throws Exception {
        organizationGeneralNews otherGeneralNews = setUpGeneralNews();
        newsDao.clearGeneralNews();
        assertEquals(0, newsDao.getAllGeneralNews().size());
    }



    // helpers

    public organizationGeneralNews setUpGeneralNews(){
        return new organizationGeneralNews("This is news","General",1);
    }

    public organizationDepartmentNews setUpDepartmentNews(){
        return new organizationDepartmentNews("This is news","General",1,1);
    }

}