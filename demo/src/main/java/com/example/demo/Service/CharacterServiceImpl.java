package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JWTService;
import com.example.demo.dto.AddCharacterDTO;
import com.example.demo.dto.GetCharacterDto;
import com.example.demo.dto.UpdateCharacterDto;
import com.example.demo.mapper.ModelMappers;
import com.example.demo.model.Characters;
import com.example.demo.repo.CharacterRepo;
import com.example.demo.response.ServiceResponse;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	private final CharacterRepo characterRepo;
	private final JWTService jwtService; 
	
	public CharacterServiceImpl(CharacterRepo characterRepo,JWTService jwtService)  {
		this.characterRepo=characterRepo;
		this.jwtService=jwtService;
	
	}
	
	public String getUserId() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getCredentials().toString());
		
		if(authentication!=null && authentication.getCredentials()  instanceof String) {
			String token = (String) authentication.getCredentials();
			return jwtService.extractUserId(token);
			
		}
		return null;
	}
	
	


	@Override
	public ServiceResponse<List<GetCharacterDto>> getAll() {
		var serviceResponse= new ServiceResponse<List<GetCharacterDto>>();
		System.out.println("User id of logged in user"+ getUserId());
		List<Characters> list = characterRepo.findAll().stream().filter(c->c.getUser().getId()==Integer.parseInt(getUserId())).toList();
		if(!list.isEmpty()) {
		serviceResponse.setData(list.stream().map(ModelMappers::getCharacterDTO).collect(Collectors.toList()));
			serviceResponse.setSuccess(true);
			serviceResponse.setMessage("No. of records found"+list.size());
		}else {
			serviceResponse.setSuccess(false);
			serviceResponse.setMessage("data not in db");
		}
		
		return serviceResponse;
	}

	@Override
	public ServiceResponse<GetCharacterDto> getById(int id) {
		var serviceResponse= new ServiceResponse<GetCharacterDto>();
		 Characters characters = characterRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
		 serviceResponse.setData(ModelMappers.getCharacterDTO(characters));
		 serviceResponse.setMessage("Data lelo");
		 serviceResponse.setSuccess(true);
		 return serviceResponse;
	}

	@Override
	public ServiceResponse<List<GetCharacterDto>> addCharacter(AddCharacterDTO character) {
		var serviceResponse= new ServiceResponse<List<GetCharacterDto>>();
	
		characterRepo.save(ModelMappers.getCharacterFromAdd(character));
		List<Characters> list = characterRepo.findAll().stream().filter(c->c.getUser().getId()== Integer.parseInt(getUserId())).toList();
		if(!list.isEmpty()) {
			serviceResponse.setData(list.stream().map(ModelMappers::getCharacterDTO).toList());
			serviceResponse.setSuccess(true);
			serviceResponse.setMessage("No. of records found"+list.size());
		}else {
			serviceResponse.setSuccess(false);
			serviceResponse.setMessage("data not in db");
		}
		
	
		
		return serviceResponse;
		
		
	}

	@Override
	public	ServiceResponse<List<GetCharacterDto>>  deleteCharacter(int id) {
		var serviceRes = new ServiceResponse<List<GetCharacterDto>>();
		characterRepo.deleteById(id);          
		List<Characters> list = characterRepo.findAll();
		serviceRes.setData(list.stream().map(ModelMappers::getCharacterDTO).toList());
		serviceRes.setSuccess(true);
		serviceRes.setMessage("ho gya delete");
		return serviceRes;
		
		
	}

	@Override
	public ServiceResponse<GetCharacterDto> updateCharacter(UpdateCharacterDto character) {
		
		var serviceRes = new ServiceResponse<GetCharacterDto>();
		Characters characters = characterRepo.findById(ModelMappers.getCharacterFromUp(character).getCharacterId()).orElseThrow(()-> new RuntimeException("Not Found"));
		characters.setCharacterId(character.getCharacterId());
		characters.setDefense(character.getDefense());
		characters.setHitPoints(character.getHitPoints());
		characters.setName(character.getName());
		characters.setStrength(character.getStrength());
		characters.setIntelligence(character.getIntelligence());
		serviceRes.setData(ModelMappers.getCharacterDTO(characters));
		
		serviceRes.setSuccess(true);
		serviceRes.setMessage("Ho gya update");

		return serviceRes;
	}



}
