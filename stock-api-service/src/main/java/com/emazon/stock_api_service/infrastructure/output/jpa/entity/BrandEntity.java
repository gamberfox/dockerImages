package com.emazon.stock_api_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<ArticleEntity> articles;
}
