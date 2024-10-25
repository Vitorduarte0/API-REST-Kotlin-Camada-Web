package br.com.alura.forum.services

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class AutorServices (private val autores: MutableList<Usuario>) {
    init {
        val autor = Usuario(
            id = 1,
            nome = "Vitor",
            email = "vitor.email.com"
        )
        autores.add(autor)
    }

    fun buscarAutorPorId(idAutor: Long): Usuario {
        return autores.first { autor -> autor.id == idAutor }
    }
}
