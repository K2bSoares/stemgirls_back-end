package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.models.Evento;
import com.br.femmcode.femmcode.services.FavoritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    // GET /favoritos/{usuarioId} -> ["id1","id2"]
    @GetMapping("/{usuarioId}")
    public List<String> listarIds(@PathVariable String usuarioId) {
        return favoritoService.listarIds(usuarioId);
    }

    // GET /favoritos/{usuarioId}/eventos -> eventos completos
    @GetMapping("/{usuarioId}/eventos")
    public List<Evento> listarEventos(@PathVariable String usuarioId) {
        return favoritoService.listarEventosFavoritos(usuarioId);
    }

    // POST /favoritos/{usuarioId}/{eventoId} -> alterna e devolve o array atualizado
    @PostMapping("/{usuarioId}/{eventoId}")
    public List<String> alternar(@PathVariable String usuarioId,
                                 @PathVariable String eventoId) {
        return favoritoService.alternar(usuarioId, eventoId);
    }

    // DELETE /favoritos/{usuarioId}/{eventoId}
    @DeleteMapping("/{usuarioId}/{eventoId}")
    public List<String> remover(@PathVariable String usuarioId,
                                @PathVariable String eventoId) {
        return favoritoService.remover(usuarioId, eventoId);
    }
}
