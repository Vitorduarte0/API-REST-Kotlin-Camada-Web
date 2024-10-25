package br.com.alura.forum.services

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoServices(private val cursos: MutableList<Curso>) {
    init {
       val curso = Curso(
            id = 1,
            nome = "Kotlin com spring",
            categoria = "Programacao"
        )

        cursos.add(curso)
    }

    fun buscarCursoPorId(id: Long): Curso {
        return cursos.first { curso -> curso.id == id }
    }
}
