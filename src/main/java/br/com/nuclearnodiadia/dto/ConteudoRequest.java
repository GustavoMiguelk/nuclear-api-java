package br.com.nuclearnodiadia.dto;

import br.com.nuclearnodiadia.model.Categoria;
import br.com.nuclearnodiadia.model.Nivel;

public record ConteudoRequest(
        String titulo,
        Categoria categoria,
        String descricao,
        Nivel nivel,
        String fonte
) {
}