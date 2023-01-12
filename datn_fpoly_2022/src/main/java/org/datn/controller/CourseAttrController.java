package org.datn.controller;

import lombok.RequiredArgsConstructor;
import org.datn.entity.CourseAttr;
import org.datn.service.CourseAttrService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/course-attribute")
@RequiredArgsConstructor
public class CourseAttrController {
    private final CourseAttrService courseAttrService;
    @PostMapping("/save")
    public CourseAttr save(@RequestBody CourseAttr entity){
        return courseAttrService.save(entity);
    }
    @PutMapping("/update/{id}")
    public CourseAttr update(@PathVariable Long id,@RequestBody CourseAttr entity){
        return courseAttrService.update(id,entity);
    }
    @DeleteMapping("/delete/{id}")
    public Map deleteById(@PathVariable Long id){
        return courseAttrService.deleteById(id);
    }
    @GetMapping("/find/{id}")
    public CourseAttr findById(@PathVariable Long id){
        return courseAttrService.findById(id);
    }
    @GetMapping("/find-by-url/{url}")
    public CourseAttr findByUrl(@PathVariable String url){
        return courseAttrService.findByUrl(url);
    }
    @GetMapping("/find-by-course-id/{courseId}")
    public Iterable<CourseAttr> findByCourseId(@PathVariable Long courseId){
        return courseAttrService.findByCourseId(courseId);
    }

}
