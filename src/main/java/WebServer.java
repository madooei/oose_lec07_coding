
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class WebServer {
  public static void main(String[] args) {
    get("/", ((request, response) -> {
      return new ModelAndView(null, "index.hbs");
    }), new HandlebarsTemplateEngine());

    get("/courses", ((request, response) -> {
      return new ModelAndView(null, "courses.hbs");
    }),  new HandlebarsTemplateEngine());
  }
}

