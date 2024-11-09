package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.services.TopicoService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController (private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listarTopicos(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"] ,direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicoView> {
        return service.listarTopicos(nomeCurso, pagination)
    }

    @GetMapping("/{id}")
    @CacheEvict(value = ["topicos"])
    fun buscarTopicoPorId(@PathVariable id: Long): TopicoView {
        return service.burcarTopicoPorId(id)
    }

    @PostMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrarTopico(
        @RequestBody @Valid topicoForm: TopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(topicoForm)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizarTopico(@RequestBody @Valid atualizaForm: AtualizaForm): ResponseEntity<TopicoView> {
        val topicoViewAtualizado = service.atualizarTopico(atualizaForm)

        return ResponseEntity.ok(topicoViewAtualizado)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletarTopico(@PathVariable id: Long) {
        return service.deletarTopico(id)
    }
}