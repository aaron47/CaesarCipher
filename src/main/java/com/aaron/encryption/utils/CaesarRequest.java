package com.aaron.encryption.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CaesarRequest {
    private String text;
    private int shift;
}
