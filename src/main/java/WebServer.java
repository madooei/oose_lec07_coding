
import static spark.Spark.*;

public class WebServer {
  public static void main(String[] args) {
    get("/", ((request, response) -> {
      return "Welcome to CourseReVU App.";
    }));
  }
}
