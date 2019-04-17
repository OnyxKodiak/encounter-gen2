package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Interest;

public class InterestDAO {
	
	public static Interest getInterest(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from interests where id=:id")
					.addParameter("id", id)
					.executeAndFetchFirst(Interest.class);
		}
	}


}
