package encountergen2.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import encountergen2.models.Creature;

public class CreatureDAO {
	
	public static Creature getCreature(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			return con.createQuery("SELECT * FROM creatures WHERE id=:id")
					.addParameter("id", id)
					.executeAndFetchFirst(Creature.class);
		}
	}
	
	public static void deleteCreature(Sql2o sql2o, Integer id) {
		try(Connection con = sql2o.open()){
			con.createQuery("DELETE FROM creatures WHERE id=:id")
					.addParameter("id", id)
					.executeUpdate();
		}
	}
	
	public static void updateCreature(Sql2o sql2o, Creature creature) {
		try(Connection con = sql2o.open()){
			con.createQuery("UPDATE creatures SET name=:name, type=:type, cr=:cr, str=:str, dex=:dex, con=:con, intl=:intl, wis=:wis, cha=:cha, ac=:ac, hp=:hp, size=:size,"
					+ "description=:description, shared=:shared WHERE id=:id")
					.addParameter("id", creature.getId())
					.addParameter("name", creature.getName())
					.addParameter("type", creature.getType())
					.addParameter("cr", creature.getCr())
					.addParameter("str", creature.getStr())
					.addParameter("dex", creature.getDex())
					.addParameter("con", creature.getCon())
					.addParameter("intl", creature.getIntl())
					.addParameter("wis", creature.getWis())
					.addParameter("cha", creature.getCha())
					.addParameter("ac", creature.getAc())
					.addParameter("hp", creature.getHp())
					.addParameter("size", creature.getSize())
					.addParameter("description", creature.getDescription())
					.addParameter("shared", creature.getShared())
					.executeUpdate();
		}
	}
	
	public static void addCreature(Sql2o sql2o, Creature creature) {
		try(Connection con = sql2o.open()){
			con.createQuery("INSERT INTO creatures (name, type, cr, str, dex, con, intl, wis, cha, ac, hp, size, description, userid, shared)"
					+ " VALUES (:name, :type, :cr, :str, :dex, :con, :intl, :wis, :cha, :ac, :hp, :size, :description, :userid, :shared)")
					.addParameter("name", creature.getName())
					.addParameter("type", creature.getType())
					.addParameter("cr", creature.getCr())
					.addParameter("str", creature.getStr())
					.addParameter("dex", creature.getDex())
					.addParameter("con", creature.getCon())
					.addParameter("intl", creature.getIntl())
					.addParameter("wis", creature.getWis())
					.addParameter("cha", creature.getCha())
					.addParameter("ac", creature.getAc())
					.addParameter("hp", creature.getHp())
					.addParameter("size", creature.getSize())
					.addParameter("description", creature.getDescription())
					.addParameter("userid", creature.getUserid())
					.addParameter("shared", creature.getShared())
					.executeUpdate();
		}
	}

}
