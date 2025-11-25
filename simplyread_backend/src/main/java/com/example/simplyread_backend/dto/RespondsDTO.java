package com.example.simplyread_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RespondsDTO {

    private String code;
    private String message;
    private Object content;

    public static RespondsDTO of(String code, String message, Object content) {
       return new RespondsDTO(code, message, content);
    }


}
