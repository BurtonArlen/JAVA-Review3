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

    get("/appointments", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/schedule.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/appointments/stylist/:name", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      model.put("stylist", Stylist.find(stylistId));
      model.put("template", "templates/appoint.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/schedule/confirmation", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // int userId = Integer.parseInt(request.queryParams("clientId"));
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      String userName = request.queryParams("name");
      Client client = new Client(userName, stylistId);
      Stylist stylist = Stylist.find(stylistId);
      client.save();
      model.put("client", client);
      model.put("stylist", stylist);
      model.put("template", "templates/appConf.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/styList.vtl");
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylistName = request.queryParams("name");
      Stylist newStylist = new Stylist(stylistName);
      newStylist.save();
      response.redirect("/admin");
      return null;
    });

    get("/admin/stylist/:name/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      model.put("clients", stylist.clients());
      model.put("stylist", stylist);
      model.put("template", "templates/stylistStats.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
