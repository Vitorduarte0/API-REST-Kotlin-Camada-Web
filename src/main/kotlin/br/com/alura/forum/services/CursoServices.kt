package br.com.alura.forum.services

import br.com.alura.forum.model.Curso
import br.com.alura.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoServices(
    private val cursosRepository: CursoRepository
) {

    fun buscarCursoPorId(id: Long): Curso {
        return cursosRepository.findById(id).get()
    }
}
