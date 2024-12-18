package com.smartcv.smartcv.repository;

import com.smartcv.smartcv.model.Education;
import com.smartcv.smartcv.model.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, String> {
}
