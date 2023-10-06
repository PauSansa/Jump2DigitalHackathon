package com.sansa.jumpdigitalhackathon.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sansa.jumpdigitalhackathon.dto.SkinDTO;
import com.sansa.jumpdigitalhackathon.dto.SkinToDTO;
import com.sansa.jumpdigitalhackathon.exception.SkinAlreadyBoughtException;
import com.sansa.jumpdigitalhackathon.exception.SkinNotFoundException;
import com.sansa.jumpdigitalhackathon.model.Skin;
import com.sansa.jumpdigitalhackathon.model.User;
import com.sansa.jumpdigitalhackathon.repository.SkinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkinService {
    private final SkinRepository data;
    private final SkinToDTO toDTO;
    private final String rutaArchivo = "src/main/resources/data/skins.json";

    // This method is the one that reads from a File
    public List<Skin> getAvailable() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();;
        List<Skin> skins = objectMapper.readValue(new File(rutaArchivo), new TypeReference<List<Skin>>() {});
        return skins;
    }

    // This method saves the bought skin in the database and removes it from the local file
    public SkinDTO buy(User user, String skinId) throws IOException {
        List<Skin> skins = getAvailable();

        Skin skin = skins.stream()
                .filter(item -> item.getId().equals(UUID.fromString(skinId)))
                .findFirst()
                .orElseThrow(() -> new SkinNotFoundException("Skin not found"));

        if(skin.getUser() != null) {
            throw new SkinAlreadyBoughtException("Skin already bought");
        } else {
            skins.remove(skin);
            skin.setUser(user);
            data.save(skin);
            saveSkins(skins);
        }

        return toDTO.apply(skin);

    }

    // This method saves the skins in the local file
    private void saveSkins(List<Skin> skins) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(rutaArchivo), skins);
    }

}
