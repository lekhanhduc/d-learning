package com.dlearning.mapper;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;

public class CourseMapper {

     private CourseMapper(){
     }

     public static CourseDTO toCourseDTO(Course course) {
          if (course == null) {
               return null;
          }

          return CourseDTO.builder()
                  .courseId(course.getCourseId())
                  .title(course.getTitle())
                  .description(course.getDescription())
                  .price(course.getPrice())
                  .duration(course.getDuration())
                  .language(course.getLanguage())
                  .level(course.getLevel())
                  .thumbnail(course.getThumbnail())
                  .videoUrl(course.getVideoUrl())
                  .build();
     }

     public static Course toCourse(CourseDTO courseDTO) {
          if (courseDTO == null) {
               return null;
          }

          return Course.builder()
                  .courseId(courseDTO.getCourseId())
                  .title(courseDTO.getTitle())
                  .description(courseDTO.getDescription())
                  .price(courseDTO.getPrice())
                  .duration(courseDTO.getDuration())
                  .language(courseDTO.getLanguage())
                  .level(courseDTO.getLevel())
                  .thumbnail(courseDTO.getThumbnail())
                  .videoUrl(courseDTO.getVideoUrl())
                  .build();
     }
}
