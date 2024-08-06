package com.dlearning.restController;

import com.dlearning.dto.response.CourseDTO;
import com.dlearning.entity.Course;
import com.dlearning.service.CloudinaryService;
import com.dlearning.service.CourseService;
import com.dlearning.utils.enums.CourseLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CourseController {

    private final CourseService courseService;
    private final CloudinaryService cloudinaryService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(@RequestParam(name = "page") Optional<Integer> pageOptional,
                                                         @RequestParam(name = "size", defaultValue = "1") int size,
                                                         @RequestParam(name = "name") Optional<String> nameOptional) {
        int page = pageOptional.orElse(1);
        String name = nameOptional.orElse("");

        Pageable pageable = PageRequest.of(page - 1, size);
        List<CourseDTO> courseList = courseService.fetchCourses(pageable, name);
        return ResponseEntity.ok().body(courseList);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") double price,
                                    @RequestParam("duration") int duration,
                                    @RequestParam("language") String language,
                                    @RequestParam("level") String level,
                                    @RequestParam("thumbnail") MultipartFile thumbnail) {
        try {
            Map<?, ?> thumbnailUploadResult = cloudinaryService.uploadFile(thumbnail, "upload");
            String thumbnailUrl = (String) thumbnailUploadResult.get("url");

            CourseDTO newCourse = new CourseDTO();
            newCourse.setTitle(title);
            newCourse.setDescription(description);
            newCourse.setPrice(price);
            newCourse.setDuration(duration);
            newCourse.setLanguage(language);
            newCourse.setLevel(CourseLevel.valueOf(level));
            newCourse.setThumbnail(thumbnailUrl);

            courseService.saveCourse(newCourse);

            return ResponseEntity.ok().body("Khóa học đã được thêm thành công!");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi thêm khóa học: " + e.getMessage());
        }
    }

    @GetMapping("/course-detail/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        Course course = courseService.findCourseById(id);
        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
        }
        return ResponseEntity.ok().body(course);
    }
}
