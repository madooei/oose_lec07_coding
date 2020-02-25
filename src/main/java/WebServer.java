
import dao.CourseDao;
import dao.InMemoryCourseDao;
import model.Course;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebServer {
  public static void main(String[] args) {

    CourseDao courseDao = new InMemoryCourseDao();
    courseDao.add(new Course("OOSE", "jhu-oose.com"));
    courseDao.add(new Course("Gateway", "jhu-gateway.com"));

    get("/", ((request, response) -> {
      return new ModelAndView(null, "index.hbs");
    }), new HandlebarsTemplateEngine());

    get("/courses", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("courseList", courseDao.findAll());
      return new ModelAndView(model, "courses.hbs");
    }),  new HandlebarsTemplateEngine());
  }
}

