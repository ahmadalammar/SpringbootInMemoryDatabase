package com.alammar.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.alammar.entity.Feature;



public interface  FeatureRepository extends CrudRepository<Feature, String> {
	
	@Query("SELECT feature from Feature feature where feature.email =:email and feature.featureName=:featureName")
	Optional<Feature> findUserByUsername(@Param("email") String email,@Param("featureName") String featureName);
	
	
}
