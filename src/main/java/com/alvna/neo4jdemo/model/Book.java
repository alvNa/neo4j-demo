//package com.alvna.neo4jdemo.model;
//
//import lombok.*;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.Node;
//import org.springframework.data.neo4j.core.schema.Property;
//import org.springframework.data.neo4j.core.schema.Relationship;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Node("Book")
//public class Book {
//    @Id
//    private String isbn;
//
//    @Property("name")
//    private String title;
//
//    private Integer year;
//
//    @Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.OUTGOING)
//    private Author author;
//
//    // Constructor, getters and setters
//}