package com.aaron.encryption.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlgorithmWithKeyRequest {
    private String text;
    private String key;
}
