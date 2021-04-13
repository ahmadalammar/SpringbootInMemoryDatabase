package com.alammar.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alammar.entity.Feature;
import com.alammar.model.FeatureDto;
import com.alammar.model.ResponseObject;
import com.alammar.service.FeatureService;



@RestController
@RequestMapping("/access")
public class AccessController {

	@Autowired
	FeatureService featureService;
	
	@GetMapping("/feature")
	public  ResponseEntity<ResponseObject>  checkFeature(@RequestParam("email") String email, @RequestParam("featureName") String featureName) {
		ResponseObject responseObject = new ResponseObject();
	
		Optional<Feature> feature= featureService.getByEmailAndFeatureName(email, featureName);
		
		if (feature.isPresent()) {
			responseObject.setMsg("canAccess : "+feature.get().isEnabled());
			responseObject.setStatus(0);
			return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		}else {
			responseObject.setMsg("Object Not Found!");
			responseObject.setStatus(-1);
			return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.NOT_FOUND);
		}
	
		
	}

	
	@PostMapping("/feature")
	public  ResponseEntity<ResponseObject> addFeature(@RequestBody FeatureDto featureDto) {
		
		ResponseObject responseObject = new ResponseObject();
		
		boolean status=featureService.saveOrUpdate(featureDto);
		if (status) {
			responseObject.setMsg("SUCCESS");
			responseObject.setStatus(0);
			return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
		}else {
			responseObject.setMsg("ERROR OCCURED!!");
			responseObject.setStatus(-1);
			return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.NOT_MODIFIED);
		}
	
	}
	
	
}