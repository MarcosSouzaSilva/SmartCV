package com.smartcv.smartcv.repository;

import com.smartcv.smartcv.model.Courses;
import com.smartcv.smartcv.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, String> {
}
