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

    post("/schedule/:stylistId/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client user = Client.find(Integer.parseInt(request.params("id")));
      user.save();
      model.put("template", "templates/appConf.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
