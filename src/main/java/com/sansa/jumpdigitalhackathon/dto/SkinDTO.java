package com.sansa.jumpdigitalhackathon.dto;

import com.sansa.jumpdigitalhackathon.model.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SkinDTO {
    private String id;
    private String name;
    private String color;
    private String weapon;
    private Boolean hasStatTrack;
    private String rarity;
    private String description;
    private String floatValue;
}
