package br.com.alura.forum.services

import br.com.alura.forum.dto.AtualizaForm
import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

// Para o spring conseguir gerenciar as coisas automatico é preciso que tenha pelo menos uma anotacao

@Service
class TopicoService(
    private var topicos: MutableList<Topico>,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico Não encontrado!"
) {

    fun listarTopicos(): List<TopicoView> {
        return topicos.map {topico: Topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun burcarTopicoPorId(id: Long): TopicoView {
        return topicos.filter {
            id == it.id
        }.map { topico: Topico ->
            topicoViewMapper.map(topico)
        }.first()
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(topicoForm)
        topico.id = topicos.size.toLong() + 1

        topicos.add(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizarTopico(atualizaForm: AtualizaForm): TopicoView {
        val topico = topicos.first {topico ->
            topico.id == atualizaForm.id
        }

        val topicoAtualizado = Topico(
            id = atualizaForm.id,
            titulo = atualizaForm.titulo,
            mensagem = atualizaForm.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos.remove(topico)
        topicos.add(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletarTopico(id: Long) {
        //usando o stream do java para uasr o recurso do orElseThrow
        val topico = topicos.stream()
            .filter { topico ->  topico.id == id}
            .findFirst()
            .orElseThrow{NotFoundException(notFoundMessage)}

        topicos.remove(topico)
    }
}