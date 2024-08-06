package com.dlearning.entity;

import com.dlearning.utils.enums.CourseLevel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "courses")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @DecimalMin(value = "0", inclusive = false, message = "Price phải lớn hơn 0")
    private Double price;

    private Integer duration; // in hours

    private String language;

    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    private String thumbnail;
    private String videoUrl;

    private LocalDateTime createdAt;
    private String createBy;

    private LocalDateTime updatedAt;
    private String updateBy;


    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private User author;


    @ManyToMany(mappedBy = "courses")
    private Set<Category> categories;
}
