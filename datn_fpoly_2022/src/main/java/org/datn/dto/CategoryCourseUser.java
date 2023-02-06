//package org.datn.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.datn.entity.Course;
//import org.datn.entity.User;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "categories")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CategoryCourseUser {
//    @Id
//    @Column(name = "category_id", nullable = false)
//    private Long id;
//
//
//    @Column(name = "name_")
//    @Type(type = "org.hibernate.type.TextType")
//    private String name;
//
//    @OneToMany(mappedBy = "category")
//    List<Course> courses;
//
//    @OneToMany(mappedBy = "categoryId")
//    List<User> users;
//
//}