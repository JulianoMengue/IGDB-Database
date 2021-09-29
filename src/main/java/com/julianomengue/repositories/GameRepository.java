package com.julianomengue.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julianomengue.entity.Game;

public interface GameRepository extends MongoRepository<Game, String> {
}
