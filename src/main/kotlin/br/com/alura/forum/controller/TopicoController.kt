package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.services.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController (private val service: TopicoService) {

    @GetMapping
    fun listarTopicos(): List<TopicoView> {
        return service.listarTopicos()
    }

    @GetMapping("/{id}")
    fun buscarTopicoPorId(@PathVariable id: Long): TopicoView {
        return service.burcarTopicoPorId(id)
    }

    @PostMapping
    fun cadastrarTopico(
        @RequestBody @Valid topicoForm: TopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(topicoForm)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    fun atualizarTopico(@RequestBody @Valid atualizaForm: AtualizaForm): ResponseEntity<TopicoView> {
        val topicoViewAtualizado = service.atualizarTopico(atualizaForm)

        return ResponseEntity.ok(topicoViewAtualizado)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarTopico(@PathVariable id: Long) {
        return service.deletarTopico(id)
    }
}