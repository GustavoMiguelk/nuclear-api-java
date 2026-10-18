package br.com.nuclearnodiadia.controller;

import br.com.nuclearnodiadia.dto.ConteudoRequest;
import br.com.nuclearnodiadia.dto.ConteudoResponse;
import br.com.nuclearnodiadia.model.Categoria;
import br.com.nuclearnodiadia.service.ConteudoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conteudos")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConteudoResponse> listar(@RequestParam(required = false) Categoria categoria) {
        return service.listar(categoria);
    }

    @GetMapping("/busca")
    public List<ConteudoResponse> buscar(@RequestParam String texto) {
        return service.buscarPorTexto(texto);
    }

    @GetMapping("/{id}")
    public ConteudoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ConteudoResponse> criar(@Valid @RequestBody ConteudoRequest req) {
        ConteudoResponse criado = service.criar(req);
        URI location = URI.create("/api/conteudos/" + criado.id());
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ConteudoResponse atualizar(@PathVariable Long id,
                                      @Valid @RequestBody ConteudoRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}