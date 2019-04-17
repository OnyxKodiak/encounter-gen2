package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Treasure;

public class TreasureDAO {
	
	public static Treasure getTreasure(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from treasures where id=:id")
					.addParameter("id", id)
					.executeAndFetchFirst(Treasure.class);
		}
	}


}
