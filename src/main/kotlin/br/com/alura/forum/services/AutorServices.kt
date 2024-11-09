package br.com.alura.forum.services

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class AutorServices (private val autorRepository: UsuarioRepository) {

    fun buscarAutorPorId(idAutor: Long): Usuario {
        return autorRepository.findById(idAutor).get()
    }
}
