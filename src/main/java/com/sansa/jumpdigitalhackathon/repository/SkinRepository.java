package com.sansa.jumpdigitalhackathon.repository;

import com.sansa.jumpdigitalhackathon.model.Skin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkinRepository extends MongoRepository<Skin, UUID> {
}
