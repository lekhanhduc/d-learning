package com.dlearning.mapper;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "thumbnail", source = "thumbnail")
    Course toCourse(CourseDTO courseDTO);

    @Mapping(target = "courseId", source = "courseId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "thumbnail", source = "thumbnail")
    @Mapping(target = "videoUrl", source = "videoUrl")
    CourseDTO toCourseDTO(Course course);
}
