import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantQuery = "DELETE FROM clients *;";
      String deleteCategoriesQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteRestaurantQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

  @Test public void client_exists_true(){
    Client testCli = new Client("Sleepy Gary", 0);
    assertEquals(true, testCli instanceof Client);
  }

  @Test public void getName_InstantiatesWithName_String() {
    Client testCli = new Client("Hamurai", 0);
    assertEquals("Hamurai", testCli.cliName());
  }

  @Test public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstCli = new Client("Ms.Sullivan", 0);
    Client secondCli = new Client("Ms.Sullivan", 0);
    assertTrue(firstCli.equals(secondCli));
  }

  @Test public void save_returnsTrueIfDescriptionsAretheSame() {
    Client testCli = new Client("Baby Wizard", 0);
    testCli.save();
    assertTrue(Client.all().get(0).equals(testCli));
  }

  @Test public void save_assignsIdToObject() {
    Client testCli = new Client("King Jellybean", 0);
    testCli.save();
    Client savedCli = Client.all().get(0);
    assertEquals(testCli.cliId(), savedCli.cliId());
  }

  @Test public void find_findsCliInDatabase_true() {
    Client testCli = new Client("Jan Michael Vincent", 0);
    testCli.save();
    Client savedCli = Client.find(testCli.cliId());
    assertTrue(testCli.equals(savedCli));
  }

  @Test public void save_savesIdIntoDB_true() {
    Stylist testSty = new Stylist("Scary Terry");
    testSty.save();
    Client testCli = new Client("Stealy" ,testSty.getId());
    testCli.save();
    Client savedCli = Client.find(testCli.cliId());
    assertEquals(savedCli.styId(), testSty.getId());
  }


}
