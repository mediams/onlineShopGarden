package de.telran.onlineshopgarden.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer categoryId;

   @Column(nullable = false, unique = true)
    @Size(min = 3, max = 100, message = "{validation.category.nameSize}")
    private String name;

    @Column(nullable = false)
    @Pattern(regexp = "^(https?://)?([\\w\\d.-]+)\\.([a-z]{2,6}\\.?)([/\\w\\d.-]*)*/?$",
            message = "validation.category.imageUrlNotValid")
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
