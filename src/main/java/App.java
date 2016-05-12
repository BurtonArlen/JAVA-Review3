import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/login", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/login.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/appointment", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist rick = new Stylist("Rick");
      Stylist morty = new Stylist("Morty");
      Stylist jerry = new Stylist("Jerry");
      Stylist beth = new Stylist("Beth");
      Stylist summer = new Stylist("Summer");
      Stylist bperson = new Stylist("BirdPerson");
      Stylist ghead = new Stylist("Gearhead");
      Stylist mpbh = new Stylist("Mr.PoopyButthole");
      rick.save();
      morty.save();
      jerry.save();
      beth.save();
      summer.save();
      bperson.save();
      ghead.save();
      mpbh.save();
      model.put("template", "templates/appointment.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/schedule/user/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Stylist> stylists = Stylist.all();
      Client user = Client.find(Integer.parseInt(request.params("id")));
      user.styId();
      user.save();
      model.put("template", "templates/appConf.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
