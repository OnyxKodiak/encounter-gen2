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

	public static void deleteTreasure(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			con.createQuery("DELETE FROM treasures WHERE id=:id")
					.addParameter("id", id)
					.executeUpdate();
		}
	}
	
	public static void updateTreasure(Sql2o sql2o, Treasure treasure) {
		try(Connection con = sql2o.open()){
			con.createQuery("UPDATE treasures SET name=:name, type=:type, value=:value, description=:description, shared=:shared WHERE id=:id")
					.addParameter("id", treasure.getId())
					.addParameter("name", treasure.getName())
					.addParameter("type", treasure.getType())
					.addParameter("value", treasure.getValue())
					.addParameter("description", treasure.getDescription())
					.addParameter("shared", treasure.getShared())
					.executeUpdate();
		}
	}
	
	public static void addTreasure(Sql2o sql2o, Treasure treasure) {
		try(Connection con = sql2o.open()){
			con.createQuery("INSERT INTO treasures (name, type, value, description, userid, shared)"
					+ " VALUES (:name, :type, :value, :description, :userid, :shared)")
					.addParameter("name", treasure.getName())
					.addParameter("type", treasure.getType())
					.addParameter("value", treasure.getValue())
					.addParameter("description", treasure.getDescription())
					.addParameter("userid", treasure.getUserid())
					.addParameter("shared", treasure.getShared())
					.executeUpdate();
		}
	}
}
