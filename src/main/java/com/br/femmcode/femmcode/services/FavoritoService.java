package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.models.Favorito;
import com.br.femmcode.femmcode.repositories.EventoRepository;
import com.br.femmcode.femmcode.repositories.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final EventoRepository eventoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository,
                           EventoRepository eventoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.eventoRepository = eventoRepository;
    }

    // Retorna o array de IDs favoritados
    public List<String> listarIds(String usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId)
                .map(Favorito::getEventosIds)
                .orElseGet(ArrayList::new);
    }

    // Retorna os eventos completos (galeria de favoritos)
    public List<Evento> listarEventosFavoritos(String usuarioId) {
        List<String> ids = listarIds(usuarioId);
        return eventoRepository.findAllById(ids);
    }

    // Adiciona se não existe, remove se já existe. Retorna o array atualizado
    public List<String> alternar(String usuarioId, String eventoId) {
        Favorito favorito = favoritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> new Favorito(usuarioId));

        List<String> ids = favorito.getEventosIds();

        if (ids.contains(eventoId)) {
            ids.remove(eventoId);
        } else {
            ids.add(eventoId);
        }

        return favoritoRepository.save(favorito).getEventosIds();
    }

    public List<String> remover(String usuarioId, String eventoId) {
        Favorito favorito = favoritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> new Favorito(usuarioId));

        favorito.getEventosIds().remove(eventoId);
        return favoritoRepository.save(favorito).getEventosIds();
    }
}
