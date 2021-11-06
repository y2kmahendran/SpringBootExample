package com.techsu.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Category Name must not be empty")
    private String name;

    private LocalDateTime lastUpdate;
    @Column(nullable = false)
    private LocalDateTime createTime;


    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    @PrePersist
    public void setCreateTime() {
        createTime = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdateTime() {
        lastUpdate = LocalDateTime.now();
    }
}
