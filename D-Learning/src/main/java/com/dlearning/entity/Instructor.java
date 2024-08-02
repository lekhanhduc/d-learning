package com.dlearning.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "instructors")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    private String bio;
    private Double rating;
    private Integer experienceYears;

}

