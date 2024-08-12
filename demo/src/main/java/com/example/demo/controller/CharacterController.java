package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CharacterService;
import com.example.demo.dto.AddCharacterDTO;
import com.example.demo.dto.GetCharacterDto;
import com.example.demo.dto.UpdateCharacterDto;
import com.example.demo.model.Characters;
import com.example.demo.repo.CharacterRepo;
import com.example.demo.response.ServiceResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("characters")
@Tag(description = "Character management API's" ,name = "character API")
public class CharacterController {
	
	private final CharacterService service;
	
	public CharacterController(CharacterService characterService) {
		this.service=characterService;
		
	}
	
	@GetMapping("name")
	public String getName() {
		return "Hi";
	}
	
	@Operation(summary = "This API will fetch all the data from database",
			description = "Just hit it and see the response",
			tags= {"characters","get"})
	@ApiResponses({
		@ApiResponse
	})
	@GetMapping("/getAll")
	public ServiceResponse<List<GetCharacterDto>> getAll(){
		return service.getAll();
	}
	
	@PostMapping("/save")
	public ServiceResponse<List<GetCharacterDto>> save(@RequestBody  AddCharacterDTO characters) {
		return service.addCharacter(characters);
	}
	
	@DeleteMapping("/delete/{id}")
	public ServiceResponse<List<GetCharacterDto>> delete(@PathVariable Integer id){
		return service.deleteCharacter(id);
		
	}
	
	@PutMapping("/update")
	public ServiceResponse<GetCharacterDto> update(@RequestBody UpdateCharacterDto characters) {
		return service.updateCharacter(characters);
	}

}
