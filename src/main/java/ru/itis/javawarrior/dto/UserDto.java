package ru.itis.javawarrior.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author lnurullina
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String login;
    private Long level;
    private String token;
}
