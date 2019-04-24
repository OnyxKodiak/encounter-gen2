package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Encounter;

public class EncounterDAO {
	
	public static Encounter getEncounter(Sql2o sql2o, String userid) {
		try(Connection con = sql2o.open()){
			return con.createQuery("select * from encounters where userid=:userid")
					.addParameter("userid", userid)
					.executeAndFetchFirst(Encounter.class);
		}
	}

	public static void deleteEncounter(Sql2o sql2o, String userid) {
		try(Connection con = sql2o.open()){
			con.createQuery("DELETE FROM encounters WHERE userid=:userid")
					.addParameter("userid", userid)
					.executeUpdate();
		}
	}
	
	public static void updateEncounter(Sql2o sql2o, Encounter encounter) {
		try(Connection con = sql2o.open()){
			con.createQuery("UPDATE encounters SET partylevel=:partylevel, environment=:environment, partysize=:partysize, difficulty=:difficulty, numencounters=:numencounters,"
					+ " frequency=:frequency, userselect=:userselect, loot=:loot, nummobs=:nummobs, WHERE userid=:userid")
					.addParameter("userid", encounter.getUserid())
					.addParameter("partylevel", encounter.getPartylevel())
					.addParameter("environment", encounter.getEnvironment())
					.addParameter("partysize", encounter.getPartysize())
					.addParameter("difficulty", encounter.getDifficulty())
					.addParameter("numencounters", encounter.getNumencounters())
					.addParameter("frequency", encounter.getFrequency())
					.addParameter("userselect", encounter.getUserselect())
					.addParameter("loot", encounter.getLoot())
					.addParameter("nummobs", encounter.getNummobs())
					.executeUpdate();
		}
	}
	
	public static void addEncounter(Sql2o sql2o, Encounter encounter) {
		try(Connection con = sql2o.open()){
			con.createQuery("INSERT INTO encounters (userid, partylevel, environment, partysize, difficulty, numencounters, frequency, userselect, loot, nummobs)"
					+ " VALUES (:userid, :partylevel, :environment, :partysize, :difficulty, :numencounters, :frequency, :userselect, :loot, :nummobs)")
			.addParameter("userid", encounter.getUserid())
			.addParameter("partylevel", encounter.getPartylevel())
			.addParameter("environment", encounter.getEnvironment())
			.addParameter("partysize", encounter.getPartysize())
			.addParameter("difficulty", encounter.getDifficulty())
			.addParameter("numencounters", encounter.getNumencounters())
			.addParameter("frequency", encounter.getFrequency())
			.addParameter("userselect", encounter.getUserselect())
			.addParameter("loot", encounter.getLoot())
			.addParameter("nummobs", encounter.getNummobs())
					.executeUpdate();
		}
	}
}
