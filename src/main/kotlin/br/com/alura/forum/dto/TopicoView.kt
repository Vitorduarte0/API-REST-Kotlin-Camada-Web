package br.com.alura.forum.dto

import br.com.alura.forum.model.StatusDoTopico
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusDoTopico,
    val dataCriacao: LocalDateTime
)
