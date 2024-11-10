package br.com.alura.forum.services

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class AutorServices (private val autorRepository: UsuarioRepository): UserDetailsService {

    fun buscarAutorPorId(idAutor: Long): Usuario {
        return autorRepository.findById(idAutor).get()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = autorRepository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }
}
