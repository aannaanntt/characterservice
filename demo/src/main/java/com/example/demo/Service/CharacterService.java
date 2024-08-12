package com.example.demo.Service;

import java.util.List;

import com.example.demo.dto.AddCharacterDTO;
import com.example.demo.dto.GetCharacterDto;
import com.example.demo.dto.UpdateCharacterDto;
import com.example.demo.model.Characters;
import com.example.demo.response.ServiceResponse;


public interface CharacterService {

	ServiceResponse<List<GetCharacterDto>> getAll();
	
	ServiceResponse<GetCharacterDto> getById(int id);
	ServiceResponse<List<GetCharacterDto>>  addCharacter(AddCharacterDTO character);
	ServiceResponse<List<GetCharacterDto>> deleteCharacter(int id);
	
	ServiceResponse<GetCharacterDto> updateCharacter(UpdateCharacterDto character);
	
}
