package com.example.demo.model;

import java.util.List;

import com.example.demo.enums.RPGClass;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="character")
public class Characters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int characterId;
	private String name="Frodo";
	
	
	private int  hitPoints=100;
	
	private int strength=10;
	private int defense;
	private int intelligence;
	
	
	private RPGClass rpgClass = RPGClass.knight;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private  User user;
	
	
	
	public RPGClass getRpgClass() {
		return rpgClass;
	}
	public void setRpgClass(RPGClass rpgClass) {
		this.rpgClass = rpgClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public Characters() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
}
