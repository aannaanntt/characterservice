package com.example.demo.dto;

import com.example.demo.enums.RPGClass;

public class GetCharacterDto {
	private int characterId;
	private String name="Frodo";
	
	
	private int  hitPoints=100;
	
	private int strength=10;
	private int defense;
	private int intelligence;
	
	
	private RPGClass rpgClass = RPGClass.knight;


	public int getCharacterId() {
		return characterId;
	}


	public void setCharacterId(int characterId) {
		this.characterId = characterId;
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


	public RPGClass getRpgClass() {
		return rpgClass;
	}


	public void setRpgClass(RPGClass rpgClass) {
		this.rpgClass = rpgClass;
	}


	public GetCharacterDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public GetCharacterDto(int characterId, String name, int hitPoints, int strength, int defense, int intelligence,
			RPGClass rpgClass) {
		super();
		this.characterId = characterId;
		this.name = name;
		this.hitPoints = hitPoints;
		this.strength = strength;
		this.defense = defense;
		this.intelligence = intelligence;
		this.rpgClass = rpgClass;
	}
	
	
	

}
