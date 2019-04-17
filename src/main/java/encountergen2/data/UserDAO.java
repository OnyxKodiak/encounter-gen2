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

}
