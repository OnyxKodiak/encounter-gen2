package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Encounter;

public class EncounterDAO {
	
	public static Encounter getEncounter(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from encounters where id=:id")
					.addParameter("id", id)
					.executeAndFetchFirst(Encounter.class);
		}
	}


}
