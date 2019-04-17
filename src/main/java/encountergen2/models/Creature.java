package encountergen2.models;

import java.util.Date;

public class Creature {
	
    private Integer id;
    private String name;
    private String type;
    private Float cr;
    private Integer str;
    private Integer dex;
    private Integer con;
    private Integer intl;
    private Integer wis;
    private Integer cha;
    private Integer ac;
    private Integer hp;
    private String size;
    private String description;
    private Integer userid;
    private Date createdate;
    private Date updated;
    private Boolean shared;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getCr() {
		return cr;
	}
	public void setCr(Float cr) {
		this.cr = cr;
	}
	public Integer getStr() {
		return str;
	}
	public void setStr(Integer str) {
		this.str = str;
	}
	public Integer getDex() {
		return dex;
	}
	public void setDex(Integer dex) {
		this.dex = dex;
	}
	public Integer getCon() {
		return con;
	}
	public void setCon(Integer con) {
		this.con = con;
	}
	public Integer getIntl() {
		return intl;
	}
	public void setIntl(Integer intl) {
		this.intl = intl;
	}
	public Integer getWis() {
		return wis;
	}
	public void setWis(Integer wis) {
		this.wis = wis;
	}
	public Integer getCha() {
		return cha;
	}
	public void setCha(Integer cha) {
		this.cha = cha;
	}
	public Integer getAc() {
		return ac;
	}
	public void setAc(Integer ac) {
		this.ac = ac;
	}
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Boolean getShared() {
		return shared;
	}
	public void setShared(Boolean shared) {
		this.shared = shared;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
}
