package ru.itis.javawarrior.util.ban;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    private boolean isValid;
    private String message;

    public Validation(boolean isValid, List<String> messages) {
        this.isValid = isValid;
        StringBuilder builder = new StringBuilder();
        for (String line : messages) {
            builder.append(line);
        }
        this.message = builder.toString();
    }
}
