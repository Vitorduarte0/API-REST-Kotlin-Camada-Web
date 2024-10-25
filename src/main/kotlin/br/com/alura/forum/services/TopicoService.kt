package br.com.alura.forum.services

import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

// Para o spring conseguir gerenciar as coisas automatico é preciso que tenha pelo menos uma anotacao

@Service
class TopicoService(
    private var topicos: MutableList<Topico>,
    private val cursoService: CursoServices,
    private val autorService: AutorServices
) {

    fun listarTopicos(): List<Topico> {
        return topicos
    }

    fun burcarTopicoPorId(id: Long): Topico {
        return topicos.filter {
            id == it.id
        }.first()
    }

    fun cadastrar(novoTopicoDTO: NovoTopicoDTO) {
        val topico = Topico(
            id = topicos.size.toLong() + 1,
            titulo = novoTopicoDTO.titulo,
            mensagem = novoTopicoDTO.messagem,
            curso = cursoService.buscarCursoPorId(novoTopicoDTO.idCurso),
            autor = autorService.buscarAutorPorId(novoTopicoDTO.idAutor)
        )

        topicos.add(topico)
    }
}