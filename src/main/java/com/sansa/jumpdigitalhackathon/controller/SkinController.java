package com.sansa.jumpdigitalhackathon.controller;

import com.sansa.jumpdigitalhackathon.dto.SkinDTO;
import com.sansa.jumpdigitalhackathon.model.Skin;
import com.sansa.jumpdigitalhackathon.model.User;
import com.sansa.jumpdigitalhackathon.response.PurchaseResponse;
import com.sansa.jumpdigitalhackathon.service.SkinService;
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
    public List<Skin> available() throws IOException {
        return skinService.getAvailable();
    }

    @PostMapping("/buy/{skinId}")
    public ResponseEntity<PurchaseResponse> buy(@PathVariable String skinId) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SkinDTO skin = skinService.buy(user, skinId);
        return ResponseEntity.ok(new PurchaseResponse("Skin bought successfully", skin));
    }

}
