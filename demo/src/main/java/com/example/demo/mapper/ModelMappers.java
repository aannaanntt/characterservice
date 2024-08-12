package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.AddCharacterDTO;
import com.example.demo.dto.GetCharacterDto;
import com.example.demo.dto.UpdateCharacterDto;
import com.example.demo.model.Characters;

public class ModelMappers {
	
	private static  ModelMapper mp = new ModelMapper();
	
	public static GetCharacterDto getCharacterDTO(Characters chars) {
	return	mp.map(chars, GetCharacterDto.class);
		
	}
	
	public static Characters getCharacterEntity(GetCharacterDto characterDto) {
		return mp.map(characterDto, Characters.class);
		
	}
	
	public static Characters getCharacterFromAdd(AddCharacterDTO addCharacterDTO) {
		return mp.map(addCharacterDTO, Characters.class);
	}
	
	public static Characters getCharacterFromUp(UpdateCharacterDto characterDto) {
		return mp.map(characterDto, Characters.class);
	}
	

	
	
	

}
