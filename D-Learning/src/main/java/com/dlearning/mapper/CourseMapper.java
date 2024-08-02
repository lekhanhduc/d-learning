package com.dlearning.mapper;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;
import com.dlearning.utils.enums.CourseLevel;

public class CourseMapper {
    private CourseMapper(){
    }

    public static CourseDTO toCourseDTO(Course course){
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .duration(course.getDuration())
                .language(course.getLanguage())
                .level(course.getLevel().toString())
                .thumbnail(course.getThumbnail())
//                .videoUrl(course.getVideoUrl())
                .avatar(course.getAuthor() != null ? course.getAuthor().getAvatar() : null)
                .build();
    }

    public static Course toCourse(CourseDTO courseDTO){
        return Course.builder()
                .courseId(courseDTO.getCourseId())
                .title(courseDTO.getTitle())
                .description(courseDTO.getDescription())
                .price(courseDTO.getPrice())
                .duration(courseDTO.getDuration())
                .language(courseDTO.getLanguage())
                .level(CourseLevel.valueOf(courseDTO.getLevel().toUpperCase()))
                .thumbnail(courseDTO.getThumbnail())
//                .videoUrl(courseDTO.getVideoUrl())
                .build();
    }
}
