package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topico(
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val autor: Usuario,
    val curso: Curso,
    val status: StatusDoTopico = StatusDoTopico.NAO_RESPONDIDO,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val respostas: List<Resposta> = ArrayList<Resposta>()
)
