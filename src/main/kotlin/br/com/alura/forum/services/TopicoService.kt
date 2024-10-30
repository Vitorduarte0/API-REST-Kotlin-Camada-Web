package br.com.alura.forum.services

import br.com.alura.forum.dto.AtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicoRepository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico Não encontrado!"
) {

    fun listarTopicos(): List<TopicoView> {
        return topicoRepository.findAll().map { topico: Topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun burcarTopicoPorId(id: Long): TopicoView {
        return topicoRepository.findById(id).map { topico: Topico ->
            topicoViewMapper.map(topico)
        }.get()
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(topicoForm)
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizarTopico(atualizaForm: AtualizaForm): TopicoView {
        val topico = topicoRepository.findById(atualizaForm.id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        topico.titulo = atualizaForm.titulo
        topico.mensagem = atualizaForm.mensagem
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun deletarTopico(id: Long) {
        val topico = topicoRepository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}

        topicoRepository.delete(topico)
    }
}