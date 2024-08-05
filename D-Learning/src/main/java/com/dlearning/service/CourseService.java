package com.dlearning.service;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;
import com.dlearning.mapper.CourseMapper;
import com.dlearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(CourseDTO course) {
        Course courseEntity = CourseMapper.toCourse(course);
        courseRepository.save(courseEntity);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
    }
}

