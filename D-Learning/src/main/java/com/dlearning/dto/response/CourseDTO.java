package com.dlearning.dto.response;

import com.dlearning.utils.enums.CourseLevel;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private Double price;
    private Integer duration;
    private String language;
    private CourseLevel level;
    private String thumbnail;
    private String videoUrl;
    private String avatar;
}
