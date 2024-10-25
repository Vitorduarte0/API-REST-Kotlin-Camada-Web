package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.services.AutorServices
import br.com.alura.forum.services.CursoServices
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoServices,
    private val autorService: AutorServices
) : Mapper<TopicoForm, Topico> {
    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarCursoPorId(t.idCurso),
            autor = autorService.buscarAutorPorId(t.idAutor)
        )
    }
}
