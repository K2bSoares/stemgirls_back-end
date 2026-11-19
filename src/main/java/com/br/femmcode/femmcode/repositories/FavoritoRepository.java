package com.br.femmcode.femmcode.repositories;

import com.br.femmcode.femmcode.models.Favorito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FavoritoRepository extends MongoRepository<Favorito, String> {
    Optional<Favorito> findByUsuarioId(String usuarioId);
}
