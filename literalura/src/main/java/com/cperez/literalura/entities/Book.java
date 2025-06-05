package com.cperez.literalura.entities;

import com.cperez.literalura.dtos.JsonBookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column( name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "language")
    private String language;

    @Column( name = "download_count")
    private int downloadCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(JsonBookDTO bookDto){

        this.title = bookDto.title();
        this.downloadCount = bookDto.downloadCount();
        this.language = bookDto.language().getFirst();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
