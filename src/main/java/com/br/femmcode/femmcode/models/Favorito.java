package com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "favoritos")
public class Favorito {

    @Id
    private String id;

    @Indexed(unique = true)
    private String usuarioId;

    private List<String> eventosIds = new ArrayList<>();

    public Favorito() {}

    public Favorito(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public List<String> getEventosIds() { return eventosIds; }
    public void setEventosIds(List<String> eventosIds) { this.eventosIds = eventosIds; }
}
