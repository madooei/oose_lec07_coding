
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

    post("/courses", ((request, response) -> {
      // TODO Capture client's input
      String name = request.queryParams("coursename");
      String url = request.queryParams("courseurl");
      // TODO create (and add) a course
      courseDao.add(new Course(name, url));
      // TODO refresh courses page to show the new addition
      response.redirect("/courses");
      return null;
    }), new HandlebarsTemplateEngine());
  }
}

