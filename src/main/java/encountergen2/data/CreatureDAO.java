package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Creature;

public class CreatureDAO {
	
	public static Creature getCreature(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from creatures where id=:id")
					.addParameter("id", id)
					.executeAndFetchFirst(Creature.class);
		}
	}

}
