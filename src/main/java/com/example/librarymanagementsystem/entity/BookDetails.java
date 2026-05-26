package com.example.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_details")
public class BookDetails {
    @Id
    @Column(name = "book_id")
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    @Column(name = "publication_year")
    private String publicationYear;
    @Column(name = "number_of_pages")
    private String numberOfPages;
    private String language;
    @Column(name = "available_status")
    private String availableStatus;
}
