package br.com.alura.forum.services

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

// Para o spring conseguir gerenciar as coisas automatico é preciso que tenha pelo menos uma anotacao

@Service
class TopicoService(
    private var topicos: MutableList<Topico>,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
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

    fun cadastrar(topicoForm: TopicoForm) {
        val topico = topicoFormMapper.map(topicoForm)
        topico.id = topicos.size.toLong() + 1

        topicos.add(topico)
    }
}