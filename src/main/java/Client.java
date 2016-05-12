import java.util.List;
import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Client {

  private String name;
  private int id;
  private int stylistid;


  public Client(String name, int stylistid){
    this.name = name;
    this.stylistid = stylistid;
  }

  public String cliName(){
    return name;
  }

  public int styId(){
    return stylistid;
  }

  public int cliId(){
    return id;
  }


  public static List<Client> all() {
    String sql = "SELECT id, name, stylistid FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient){
    if(!(otherClient instanceof Client)){
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.cliName().equals(newClient.cliName())
        && this.cliId() == newClient.cliId()
        && this.styId() == newClient.styId();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, stylistid) VALUES (:name, :stylistid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylistid", this.stylistid)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE id = :id";
      Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
    }
  }
}
