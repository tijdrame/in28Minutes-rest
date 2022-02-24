package com.emard.api.restfulwebservice.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Le nom doit avoir une longueur de 2 charactéres au moins.")
    private String name;
    @Past(message = "La date doit être dans le passé.")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}
