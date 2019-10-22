package com.ea.festivalservice.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ea.festivalservice.common.RateLimitException;
import com.ea.festivalservice.model.Festival;
import com.ea.festivalservice.model.MusicFestival;
import com.ea.festivalservice.service.FestivalService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class FestivalController {

	private static final Logger logger = LogManager.getLogger(FestivalController.class);

	private final FestivalService festivalService;

	public FestivalController(FestivalService festivalService) {
		this.festivalService = festivalService;
	}	
	@CrossOrigin
	@GetMapping("/api/v1/festivals")
	//public List<MusicFestival> getFestivals() {
	public ResponseEntity<?> getFestivals() {
		try {
			System.out.println("<<<< in a good condition >>>>");
			return ResponseEntity.status(200).body(festivalService.getAllFestivals());
		}catch(RateLimitException rte) {
			return ResponseEntity.status(429).build();
		}catch(Exception e) {
			
		}
		return null;
		
	}
}
