package br.com.alura.forum.dto

data class NovoTopicoDTO (
    val titulo: String,
    val messagem: String,
    val idCurso: Long,
    val idAutor: Long
)