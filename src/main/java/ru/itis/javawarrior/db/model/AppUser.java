package ru.itis.javawarrior.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.javawarrior.entity.StageTemplate;
import ru.itis.javawarrior.util.TemplateConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private Long level;
    @Convert(converter = TemplateConverter.class)
    private StageTemplate map;

}
