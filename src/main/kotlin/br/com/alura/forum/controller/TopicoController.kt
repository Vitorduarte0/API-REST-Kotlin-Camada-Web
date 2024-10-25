package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.services.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun cadastrarTopico(@RequestBody topicoForm: TopicoForm) {
        return service.cadastrar(topicoForm)
    }
}