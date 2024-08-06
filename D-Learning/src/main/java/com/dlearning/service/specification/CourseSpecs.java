package com.dlearning.service.specification;

import com.dlearning.entity.Course;
import com.dlearning.entity.Course_;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecs {

    private CourseSpecs(){
    }

    public static Specification<Course> nameLike(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Course_.TITLE), "%"+name+"%");
    }
}
