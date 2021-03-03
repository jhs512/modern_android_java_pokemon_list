package com.example.sbs.myapplication.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pokemon {
    public int id;
    public String name;

    public String getImgUrl() {
        return "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
    }

    @JsonCreator
    public Pokemon(@JsonProperty("name") String name, @JsonProperty("url") String url) {
        this.name = name;
        String[] urlBits = url.split("/");
        this.id = Integer.parseInt(urlBits[urlBits.length - 1]);
    }
}