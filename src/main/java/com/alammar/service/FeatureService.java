package com.alammar.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alammar.entity.Feature;
import com.alammar.model.FeatureDto;
import com.alammar.repo.FeatureRepository;


@Service
public class FeatureService {

	@Autowired
	FeatureRepository featureRepository;

	public boolean saveOrUpdate(FeatureDto featureDto) {
		Feature feature= new Feature();
		feature.setEmail(featureDto.getEmail());
		feature.setFeatureName(featureDto.getFeatureName());
		feature.setEnabled(featureDto.isEnable());
		feature.setId(UUID.randomUUID().toString());
		featureRepository.save(feature);
		return featureRepository.findById(feature.getId()).isPresent();
	}
	
	public Optional<Feature> getByEmailAndFeatureName(String email,String featureName) {
		
		return featureRepository.findUserByUsername(email,featureName);
	
	} 
	

}
