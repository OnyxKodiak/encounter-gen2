package encountergen2;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;

import encountergen2.data.UserDAO;
import encountergen2.models.User;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class Application {

	static FreeMarkerEngine templateEngine;
	static Sql2o sql2o;

	public static void main(String[] args) {
    	Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
    	configuration.setClassForTemplateLoading(Application.class, "/templates");
    	templateEngine = new FreeMarkerEngine(configuration);
    	sql2o = new Sql2o("jdbc:mysql://localhost:3306/encounter-generator", "encounter-gen", "encounter");
    	
        get("/", Application::getIndex);
        get("/login", Application::getLogin);
        get("/data/:type", Application::getData);
        post("/login", Application::postLogin);
        post("/data/:type", Application::postData);
        post("/register", Application::postRegister);
        get("/register", Application::getRegister);
        post("/logout", Application::postLogout);
        
    }

	public static String getIndex(Request request, Response response) {
		Map<String, String> model = new HashMap<>();

		return templateEngine.render(new ModelAndView(model, "index.ftlh"));
	}

	public static String getLogin(Request request, Response response) {
		Map<String, String> model = new HashMap<>();
		model.put("title", "Login");
		return templateEngine.render(new ModelAndView(model, "login.ftlh"));
	}

	public static String getData(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("data", new Object[0]);
		String type=request.params("type");
		return templateEngine.render(new ModelAndView(model, "/data/" + type + "_list.ftlh"));
	}

	public static String getRegister(Request request, Response response) {
		Map<String, String> model = new HashMap<>();
		model.put("title", "Registration");
		return templateEngine.render(new ModelAndView(model, "register.ftlh"));
	}

	public static String postLogin(Request request, Response response) {
		Map<String, String> model = new HashMap<>();
		User user = UserDAO.getUser(sql2o, request.queryParams("name"));
		return templateEngine.render(new ModelAndView(model, "index.ftlh"));
	}

	public static String postRegister(Request request, Response response) {
		response.redirect("/");
		return null;
	}

	public static String postData(Request request, Response response) {
		Map<String, String> model = new HashMap<>();

		return templateEngine.render(new ModelAndView(model, "index.ftlh"));
	}
	public static String postLogout(Request request, Response response) {
		Map<String, String> model = new HashMap<>();

		return templateEngine.render(new ModelAndView(model, "index.ftlh"));
	}
}
