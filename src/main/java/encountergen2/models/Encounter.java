package encountergen2.models;

public class Encounter {
	private Integer Id;
	private Integer partylevel;
	private String environment;
	private Integer partysize;
	private Integer difficulty;
	private Integer numencounters;
	private Integer frequency;
	private Integer userid;
	private Boolean userselect;
	private Boolean loot;
	private Integer nummobs;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getPartylevel() {
		return partylevel;
	}
	public void setPartylevel(Integer partylevel) {
		this.partylevel = partylevel;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Integer getPartysize() {
		return partysize;
	}
	public void setPartysize(Integer partysize) {
		this.partysize = partysize;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public Integer getNumencounters() {
		return numencounters;
	}
	public void setNumencounters(Integer numencounters) {
		this.numencounters = numencounters;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Boolean getUserselect() {
		return userselect;
	}
	public void setUserselect(Boolean userselect) {
		this.userselect = userselect;
	}
	public Boolean getLoot() {
		return loot;
	}
	public void setLoot(Boolean loot) {
		this.loot = loot;
	}
	public Integer getNummobs() {
		return nummobs;
	}
	public void setNummobs(Integer nummobs) {
		this.nummobs = nummobs;
	}

}
