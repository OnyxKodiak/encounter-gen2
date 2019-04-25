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
	public static void deleteInterest(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			con.createQuery("DELETE FROM interests WHERE id=:id")
					.addParameter("id", id)
					.executeUpdate();
		}
	}
	
	public static void updateInterest(Sql2o sql2o, Interest interest) {
		try(Connection con = sql2o.open()){
			con.createQuery("UPDATE interests SET name=:name, environment=:environment, description=:description, shared=:shared WHERE id=:id")
					.addParameter("id", interest.getId())
					.addParameter("name", interest.getName())
					.addParameter("environment", interest.getEnvironment())
					.addParameter("description", interest.getDescription())
					.addParameter("shared", interest.getShared())
					.executeUpdate();
		}
	}
	
	public static void addInterest(Sql2o sql2o, Interest interest) {
		try(Connection con = sql2o.open()){
			con.createQuery("INSERT INTO interests (name, environment, description, shared)"
					+ " VALUES (:name, :environment, :description, :shared)")
					.addParameter("name", interest.getName())
					.addParameter("type", interest.getEnvironment())
					.addParameter("description", interest.getDescription())
					.addParameter("shared", interest.getShared())
					.executeUpdate();
		}
	}

}
