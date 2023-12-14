package dev.patika.veteriner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
