package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoForm (
    @field:NotEmpty
    @field:Size(min = 5, max = 100, message = "O campo deve conter no minimo 5 caracteres e no maximo 100")
    val titulo: String,
    @field:NotEmpty(message = "não deve estar vazio")
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)