package com.siraj.pickspot;

import com.siraj.pickspot.dto.PickRequest;
import com.siraj.pickspot.dto.PickResponse;
import com.siraj.pickspot.service.PickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class PickspotApplication {

	@Autowired
	private PickerService picker;

	public static void main(String[] args) {
		SpringApplication.run(PickspotApplication.class, args);
	}

	@PostMapping("/pickSpot")
	public ResponseEntity<?> pick(@RequestBody PickRequest req) {
		return picker.chooseBestSlot(req.container(), req.yardMap())
				.<ResponseEntity<?>>map(s ->
						ResponseEntity.ok(new PickResponse(req.container().id(), s.x(), s.y()))
				)
				.orElseGet(() ->
						ResponseEntity.badRequest().body(Map.of("error", "no suitable slot"))
				);
	}

	@GetMapping("/pickSpot")
	public String pickSpotInfo() {
		return "Executed Successful.";
	}

}
