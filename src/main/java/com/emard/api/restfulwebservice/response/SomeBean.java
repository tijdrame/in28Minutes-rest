package com.emard.api.restfulwebservice.response;


import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties(value = {"field2"})
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;
}
