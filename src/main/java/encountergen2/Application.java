package encountergen2;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2o;

import encountergen2.data.CreatureDAO;
import encountergen2.data.DataType;
import encountergen2.data.EncounterDAO;
import encountergen2.data.InterestDAO;
import encountergen2.data.TreasureDAO;
import encountergen2.data.UserDAO;
import encountergen2.models.Creature;
import encountergen2.models.Encounter;
import encountergen2.models.Interest;
import encountergen2.models.Treasure;
import encountergen2.models.User;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

public class Application {

	static FreeMarkerEngine templateEngine;
	static Sql2o sql2o;

	public static void main(String[] args) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		configuration.setClassForTemplateLoading(Application.class, "/templates");
		templateEngine = new FreeMarkerEngine(configuration);
		sql2o = new Sql2o("jdbc:mysql://localhost:3306/encounter-gen", "root", "");

		get("/", Application::getIndex);
		get("/login", Application::getLogin);
		get("/data/:type", Application::getData);
		get("/data/:type/:itemid", Application::getItem);
		post("/data/:type/:itemid", Application::postItem);
		post("/login", Application::postLogin);
		post("/data/:type", Application::postData);
		post("/register", Application::postRegister);
		get("/register", Application::getRegister);
		get("/logout", Application::getLogout);
		get("/data/encounter_list", Application::getEncounter);
		post("/data/encounter_list", Application::postEncounter);

	}

	public static String getIndex(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
		return templateEngine.render(new ModelAndView(model, "index.ftlh"));
	}

	public static String getLogin(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", "Login");
		return templateEngine.render(new ModelAndView(model, "login.ftlh"));
	}

	public static String getData(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
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
		Session session = request.session();
		model.put("user", session.attribute("user"));
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
		Map<String, Object> model = new HashMap<>();
		model.put("title", "Registration");
		return templateEngine.render(new ModelAndView(model, "register.ftlh"));
	}

	public static String postLogin(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		User user = UserDAO.getUser(sql2o, request.queryParams("name"));
		if (user == null) {
			// TODO: return errors
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
		if (StringUtils.isNotBlank(name)) {
			User user = UserDAO.getUser(sql2o, request.queryParams("name"));
			if (user == null) {
				if (StringUtils.equals(password, verify)) {
					user = new User();
					user.setName(name);
					user.setPassword(password);
					UserDAO.addUser(sql2o, user);
					Session session = request.session();
					session.attribute("user", user);
					response.redirect("/");
				}
				// TODO: return password error
				return "a";
			}
			// TODO: return user exist error
			return "b";
		} else {
			// TODO: return errors
		}
		response.redirect("/");
		return "c";
	}

	public static String postData(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
		model.put("data", new Object[0]);
		String type = request.params(":type");
//		User user = UserDAO.getUser(sql2o, request.queryParams("name"));
//		int userid = 
		if (DataType.isValid(type)) {
			switch (type) {
			case "creatures":
				Creature creature = new Creature();
				creature.setName(request.queryParams("name"));
				creature.setType(request.queryParams("type"));
				creature.setCr(NumberUtils.toFloat(request.queryParams("cr")));
				creature.setStr(NumberUtils.toInt(request.queryParams("str")));
				creature.setDex(NumberUtils.toInt(request.queryParams("dex")));
				creature.setCon(NumberUtils.toInt(request.queryParams("con")));
				creature.setIntl(NumberUtils.toInt(request.queryParams("intl")));
				creature.setWis(NumberUtils.toInt(request.queryParams("wis")));
				creature.setCha(NumberUtils.toInt(request.queryParams("cha")));
				creature.setAc(NumberUtils.toInt(request.queryParams("ac")));
				creature.setHp(NumberUtils.toInt(request.queryParams("hp")));
				creature.setSize(request.queryParams("size"));
				creature.setDescription(request.queryParams("description"));
				creature.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				CreatureDAO.addCreature(sql2o, creature);
				break;
			case "interests":
				Interest interest = new Interest();
				interest.setName(request.queryParams("name"));
				interest.setDescription(request.queryParams("description"));
				interest.setEnvironment(request.queryParams("environment"));
				interest.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				InterestDAO.addInterest(sql2o, interest);
				break;
			case "treasures":
				Treasure treasure = new Treasure();
				treasure.setName(request.queryParams("name"));
				treasure.setType(request.queryParams("type"));
				treasure.setValue(NumberUtils.toInt(request.queryParams("value")));
				treasure.setDescription(request.queryParams("description"));
				treasure.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				TreasureDAO.addTreasure(sql2o, treasure);
				break;
			}
		}
		response.redirect("/data/" + type + "/_list.fthl");
		return null;
	}

	public static String postItem(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
		model.put("data", new Object[0]);
		String type = request.params(":type").toLowerCase();
		int itemid = NumberUtils.toInt(request.params(":itemid"), 0);
		if (DataType.isValid(type) || itemid <= 0) {
			switch (type) {
			case "creatures":
				Creature creature = new Creature();
				creature.setName(request.queryParams("name"));
				creature.setType(request.queryParams("type"));
				creature.setCr(NumberUtils.toFloat(request.queryParams("cr")));
				creature.setStr(NumberUtils.toInt(request.queryParams("str")));
				creature.setDex(NumberUtils.toInt(request.queryParams("dex")));
				creature.setCon(NumberUtils.toInt(request.queryParams("con")));
				creature.setIntl(NumberUtils.toInt(request.queryParams("intl")));
				creature.setWis(NumberUtils.toInt(request.queryParams("wis")));
				creature.setCha(NumberUtils.toInt(request.queryParams("cha")));
				creature.setAc(NumberUtils.toInt(request.queryParams("ac")));
				creature.setHp(NumberUtils.toInt(request.queryParams("hp")));
				creature.setSize(request.queryParams("size"));
				creature.setDescription(request.queryParams("description"));
				creature.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				CreatureDAO.addCreature(sql2o, creature);
				break;
			case "interests":
				Interest interest = new Interest();
				interest.setName(request.queryParams("name"));
				interest.setDescription(request.queryParams("description"));
				interest.setEnvironment(request.queryParams("environment"));
				interest.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				InterestDAO.addInterest(sql2o, interest);
				break;
			case "treasures":
				Treasure treasure = new Treasure();
				treasure.setName(request.queryParams("name"));
				treasure.setType(request.queryParams("type"));
				treasure.setValue(NumberUtils.toInt(request.queryParams("value")));
				treasure.setDescription(request.queryParams("description"));
				treasure.setShared(BooleanUtils.toBooleanObject(request.queryParams("shared")));
				TreasureDAO.addTreasure(sql2o, treasure);
				break;
			}
		}

		response.redirect("/data/" + type + "/_list.fthl");
		return null;

	}

	public static String getLogout(Request request, Response response) {
		Session session = request.session();
		session.invalidate();
		response.redirect("/");
		return null;
	}
	
	public static String getEncounter(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
		if(session.attribute("user") != null) {
			User user = UserDAO.getUser(sql2o, request.queryParams("name"));
			String userid = user.getName();
			EncounterDAO.getEncounter(sql2o, userid);
			return templateEngine.render(new ModelAndView(model, "/data/encounter_list.ftlh"));
		}
		response.redirect("/data/encounter_list.fthl");
		return null;
	}
	
	public static String postEncounter(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		Session session = request.session();
		model.put("user", session.attribute("user"));
		Encounter encounter = new Encounter();
		encounter.setPartylevel(NumberUtils.toInt(request.queryParams("partylevel")));
		encounter.setEnvironment(request.queryParams("environment"));
		encounter.setPartysize(NumberUtils.toInt(request.queryParams("partysize")));
		encounter.setDifficulty(NumberUtils.toInt(request.queryParams("difficulty")));
		encounter.setNumencounters(NumberUtils.toInt(request.queryParams("numencounters")));
		encounter.setFrequency(NumberUtils.toInt(request.queryParams("frequency")));
		encounter.setUserselect(BooleanUtils.toBooleanObject(request.queryParams("userselect")));
		encounter.setLoot(BooleanUtils.toBooleanObject(request.queryParams("loot")));
		encounter.setNummobs(NumberUtils.toInt(request.queryParams("nummobs")));
		
		response.redirect("/data/encounter_list.fthl");
		return null;
	}
}
