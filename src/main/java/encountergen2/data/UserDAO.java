package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.User;

public class UserDAO {
	
	public static User getUser(Sql2o sql2o, String name) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from users where name=:name")
					.addParameter("name", name)
					.executeAndFetchFirst(User.class);
		}
	}
	
	public static void updateUser(Sql2o sql2o, User user) {
		try(Connection con = sql2o.open()){
			con.createQuery("UPDATE users SET password=:password, WHERE id=:id")
					.addParameter("id", user.getId())
					.addParameter("name", user.getName())
					.addParameter("password", user.getPassword())
					.executeUpdate();
		}
	}
	
	public static void addUser(Sql2o sql2o, User user) {
		try(Connection con = sql2o.open()){
			con.createQuery("INSERT INTO users (name, password, createdate, updated)"
					+ " VALUES (:name, :password, :createdate, :updated)")
					.addParameter("id", user.getId())
					.addParameter("name", user.getName())
					.addParameter("password", user.getPassword())
					.executeUpdate();
		}
	}
}
