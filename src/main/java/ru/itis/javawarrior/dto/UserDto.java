package ru.itis.javawarrior.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lnurullina
 */
@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private Long level;
    private String token;
}
