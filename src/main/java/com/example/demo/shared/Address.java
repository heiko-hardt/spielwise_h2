package com.example.demo.shared;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String city;
    private String country;
}
