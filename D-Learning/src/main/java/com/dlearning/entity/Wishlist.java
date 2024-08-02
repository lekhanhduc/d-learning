package com.dlearning.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "wishlists")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
