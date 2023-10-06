package com.sansa.jumpdigitalhackathon.dto;

import com.sansa.jumpdigitalhackathon.model.Skin;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SkinToDTO implements Function<Skin,SkinDTO> {
    @Override
    public SkinDTO apply(Skin skin) {
        return SkinDTO.builder()
                .id(skin.getId().toString())
                .name(skin.getName())
                .color(skin.getColor())
                .weapon(skin.getWeapon())
                .hasStatTrack(skin.getHasStatTrack())
                .rarity(skin.getRarity())
                .description(skin.getDescription())
                .floatValue(skin.getFloatValue())
                .build();
    }
}
