package com.nova.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-06T19:43:42.781Z")

@Data
public class User   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

}

