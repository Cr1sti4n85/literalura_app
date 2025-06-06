package com.cperez.literalura.entities;

import com.cperez.literalura.dtos.JsonAuthorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(JsonAuthorDTO authorDto){
        this.name = authorDto.name() != null ? authorDto.name() : "Unknown";
        this.birthYear = authorDto.birthYear();
        this.deathYear = authorDto.deathYear();
    }

    public void addBook(Book b){
        books.add(b);
        b.setAuthor(this);
    }

    @Override
    public String toString() {
        var titleBooks = books.stream().map(Book::getTitle).toList();
        return "#############################" + "\n" +
                "Nombre: '" + name + "\n" +
                "Año de nacimiento: " + birthYear + "\n" +
                "Año de fallecimiento: " + deathYear + "\n" +
                "Libros: " + titleBooks + "\n";
    }
}
