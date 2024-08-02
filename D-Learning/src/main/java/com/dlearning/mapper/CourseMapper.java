package com.dlearning.mapper;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
     @Mapping(target = "title", source = "course.title")
     @Mapping(target = "description", source = "course.description")
     @Mapping(target = "price", source = "course.price")
     @Mapping(target = "duration", source = "course.duration")
     @Mapping(target = "language", source = "course.language")
     @Mapping(target = "level", source = "course.level")
     @Mapping(target = "thumbnail", source = "course.thumbnail")
     CourseDTO toCourseDTO(Course course);

     @Mapping(target = "course.title", source = "title")
     @Mapping(target = "course.description", source = "description")
     @Mapping(target = "course.price", source = "price")
     @Mapping(target = "course.duration", source = "duration")
     @Mapping(target = "course.language", source = "language")
     @Mapping(target = "course.level", source = "level")
     @Mapping(target = "course.thumbnail", source = "thumbnail")
     Course toCourse(CourseDTO courseDTO);
}
