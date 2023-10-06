package com.sansa.jumpdigitalhackathon.controller;

import com.sansa.jumpdigitalhackathon.dto.SkinDTO;
import com.sansa.jumpdigitalhackathon.model.Skin;
import com.sansa.jumpdigitalhackathon.model.User;
import com.sansa.jumpdigitalhackathon.request.ColorRequest;
import com.sansa.jumpdigitalhackathon.response.BasicMessageResponse;
import com.sansa.jumpdigitalhackathon.response.PurchaseResponse;
import com.sansa.jumpdigitalhackathon.service.SkinService;
import com.sansa.jumpdigitalhackathon.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skins")
public class SkinController {
    private final SkinService skinService;

    @GetMapping("/available")
    public List<SkinDTO> available() throws IOException {
        return skinService.getAvailable();
    }

    @PostMapping("/buy/{skinId}")
    public ResponseEntity<PurchaseResponse> buy(@PathVariable String skinId) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SkinDTO skin = skinService.buy(user, skinId);
        return ResponseEntity.ok(new PurchaseResponse("Skin bought successfully", skin));
    }

    @GetMapping("/myskins")
    public ResponseEntity<List<SkinDTO>> mySkins() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SkinDTO> skins = skinService.mySkins(user);
        if(skins.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(skins);
        }
    }

    @PutMapping("/color")
    public ResponseEntity<BasicMessageResponse> changeColor(@RequestBody ColorRequest cr){
        if(!RequestValidator.validateNotNullFields(cr)){
            return ResponseEntity.badRequest().body(new BasicMessageResponse("Color and skinId are required"));
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        skinService.changeColor(user, cr);
        return ResponseEntity.ok(new BasicMessageResponse("Color changed successfully"));
    }

    @DeleteMapping("/delete/{skinId}")
    public ResponseEntity<BasicMessageResponse> deleteSkin(@PathVariable String skinId) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        skinService.deleteSkin(user, skinId);
        return ResponseEntity.ok(new BasicMessageResponse("Skin deleted successfully"));
    }

    @GetMapping("/getskin/{skinId}")
    public SkinDTO getSkin(@PathVariable String skinId) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return skinService.getSkin(user, skinId);
    }

}
