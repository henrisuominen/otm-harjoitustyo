/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.Database;
import Dao.PelaajaDao;
import domain.Pelaaja;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrisuominen
 */
public class DaoTest {

    private final Database database;
    private final PelaajaDao pelaajaDao;
    
    public DaoTest() throws ClassNotFoundException {
        this.database = new Database("jdbc:sqlite:PelaajaTest.db");
        this.pelaajaDao = new PelaajaDao(this.database);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
       
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void loytyykoHenri() throws SQLException {
        Pelaaja henri = this.pelaajaDao.findByNameOne("Henri");
        assertEquals(henri,null);
    }
}
