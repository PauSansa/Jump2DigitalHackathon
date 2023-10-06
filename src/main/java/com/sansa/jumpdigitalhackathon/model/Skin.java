package com.sansa.jumpdigitalhackathon.model;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "skins")
@Data
public class Skin {
    @MongoId
    @Field("_id")
    private UUID id;
    @DBRef
    private User user;
    private String name;
    private String color;
    private String weapon;
    private Boolean hasStatTrack;
    private String rarity;
    private String description;
    private String floatValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skin skin = (Skin) o;
        return Objects.equals(id, skin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
