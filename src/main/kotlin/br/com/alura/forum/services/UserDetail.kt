package br.com.alura.forum.services

import br.com.alura.forum.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = null

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.email
}
