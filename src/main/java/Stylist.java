import java.util.List;
import org.sql2o.*;

public class Stylist {

  private String name;
  private int id;

  public Stylist(String name){
    this.name = name;
  }

  public String styName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public static List<Stylist> all(){
    String sql = "SELECT id, name FROM stylists";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public List<Client> clients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE stylistid = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStyle = (Stylist) otherStylist;
      return this.styName().equals(newStyle.styName()) && this.getId() == newStyle.getId();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int id){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }
}
