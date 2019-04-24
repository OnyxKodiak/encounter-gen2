package encountergen2;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2o;

import encountergen2.data.CreatureDAO;
import encountergen2.data.DataType;
import encountergen2.data.InterestDAO;
import encountergen2.data.TreasureDAO;
import encountergen2.data.UserDAO;
import encountergen2.models.User;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

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
		get("/data/:type/:itemid", Application::getItem);
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
		String type = request.params(":type");
		if (DataType.isValid(type)) {
			if (request.queryParamOrDefault("add", "").length() > 0) {
				return templateEngine.render(new ModelAndView(model, "/data/" + type + "_create.ftlh"));
			}
			return templateEngine.render(new ModelAndView(model, "/data/" + type + "_list.ftlh"));
		}
		response.status(HttpStatus.NOT_FOUND_404);
		return null;
	}

	public static String getItem(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("data", new Object[0]);
		String type = request.params(":type").toLowerCase();
		int itemid = NumberUtils.toInt(request.params(":itemid"), 0);
		String action = request.queryParamOrDefault("action", "").toLowerCase();
		if (DataType.isValid(type) || itemid <= 0) {
			Object item = null;
			switch (type) {
			case "creatures":
				item = CreatureDAO.getCreature(sql2o, itemid);
				break;
			case "interests":
				item = InterestDAO.getInterest(sql2o, itemid);
				break;
			case "treasures":
				item = TreasureDAO.getTreasure(sql2o, itemid);
				break;
			}
			if (item != null) {
				if (action.toLowerCase() == "delete") {
					switch (type) {
					case "creatures":
						CreatureDAO.deleteCreature(sql2o, itemid);
						break;
					case "interests":
						InterestDAO.deleteInterest(sql2o, itemid);
						break;
					case "treasures":
						TreasureDAO.deleteTreasure(sql2o, itemid);
						break;
					}
				}

				return templateEngine.render(new ModelAndView(model, "/data/" + type + "_edit.ftlh"));
			}
		}

		response.status(HttpStatus.NOT_FOUND_404);
		return null;

	}

	public static String getRegister(Request request, Response response) {
		Map<String, String> model = new HashMap<>();
		model.put("title", "Registration");
		return templateEngine.render(new ModelAndView(model, "register.ftlh"));
	}

	public static String postLogin(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		User user = UserDAO.getUser(sql2o, request.queryParams("name"));
		if(user==null) {
			//TODO: return errors
			return null;
		}
		Session session = request.session();
		session.attribute("user", user);
		model.put("user", user);
		response.redirect("/");
		return null;
	}

	public static String postRegister(Request request, Response response) {
		String name = request.queryParams("name");
		String password = request.queryParams("password");
		String verify = request.queryParams("verify");
		if(StringUtils.isNotBlank(name)) {
			
		}else {
			//TODO: return errors
		}
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
