package br.com.alura.forum.services

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

// Para o spring conseguir gerenciar as coisas automatico é preciso que tenha pelo menos uma anotacao

@Service
class TopicoService (private var topicos: List<Topico>){

    init {
        val topico = Topico(
            id = 1,
            titulo = "Funcoes no Kotlin",
            mensagem = "Como declarar funcoes no kotlin?",
            autor = Usuario(
                id = 1,
                nome = "Vitor",
                email = "Vitorsanta.cal.10@gmail.com"
            ),
            curso = Curso(
                id = 1,
                nome = "Intruducao Kotlin",
                categoria = "Programacao"
            )
        )
        val topico2 = Topico(
            id = 2,
            titulo = "Funcoes no Kotlin 2",
            mensagem = "Como declarar funcoes no kotlin? 2",
            autor = Usuario(
                id = 1,
                nome = "Vitor",
                email = "Vitorsanta.cal.10@gmail.com"
            ),
            curso = Curso(
                id = 2,
                nome = "Intruducao Kotlin 2",
                categoria = "Programacao"
            )
        )
        val topico3 = Topico(
            id = 3,
            titulo = "Funcoes no Kotlin 3",
            mensagem = "Como declarar funcoes no kotlin? 3",
            autor = Usuario(
                id = 1,
                nome = "Vitor",
                email = "Vitorsanta.cal.10@gmail.com"
            ),
            curso = Curso(
                id = 3,
                nome = "Intruducao Kotlin 3",
                categoria = "Programacao"
            )
        )

        topicos = listOf(topico, topico2, topico3)
    }
    fun listarTopicos(): List<Topico> {
        return topicos
    }

    fun burcarTopicoPorId(id: Long): Topico {
        return topicos.filter {
            id == it.id
        }.first()
    }
}