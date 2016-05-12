import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientQuery = "DELETE FROM clients *;";
      String deleteStylistQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientQuery).executeUpdate();
      con.createQuery(deleteStylistQuery).executeUpdate();
    }
  }

  @Test public void stylist_exists_true(){
    Stylist testSty = new Stylist("Jerry");
    testSty.save();
    assertEquals(true, testSty instanceof Stylist);
  }

  @Test public void styName_stylistHasName_string(){
    Stylist testSty = new Stylist("Rick");
    testSty.save();
    assertEquals("Rick", testSty.styName());
  }

  @Test public void stylistList_startEmpty_int(){
    assertEquals(0, Stylist.all().size());
  }

  @Test public void equiv_TrueIfNameSame_boolean(){
    Stylist testSty1 = new Stylist("BirdPerson");
    Stylist testSty2 = new Stylist("BirdPerson");
    testSty1.save();
    assertEquals(testSty1.styName(), testSty2.styName());
  }
}
