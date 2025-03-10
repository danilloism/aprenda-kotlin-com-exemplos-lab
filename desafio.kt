// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }


data class Usuario(val nome: String, val email: String) {
    override fun hashCode() = email.hashCode()

    override fun equals(other: Any?) = other is Usuario && other.hashCode() == this.hashCode()

}

data class ConteudoEducacional(
    val nome: String,
    val duracao: Int = 60
)


data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    private val inscritos = mutableSetOf<Usuario>()
    val numeroInscritos get() = inscritos.count()

    override fun toString(): String {
        return StringBuilder()
            .appendLine("Formação: $nome")
            .appendLine("Nível: $nivel")
            .appendLine("Conteúdos: $conteudos")
            .toString()
    }

    fun matricular(vararg usuarios: Usuario) {
        for (usuario in usuarios) {
            println("Iniciando matrícula de $usuario")

            if (inscritos.add(usuario)) {
                println("$usuario matriculado com sucesso.\n")
                continue
            }

            println("Matrícula de $usuario não pôde ser realizada. Já existe uma matrícula com o email informado.\n")
        }
    }

    fun cancelarMatricula(vararg usuarios: Usuario) {
        for (usuario in usuarios) {
            println("Iniciando cancelamento de matrícula de $usuario")

            if (inscritos.remove(usuario)) {
                println("Matrícula de $usuario cancelada com sucesso.\n")
                continue
            }

            println("Não foi possível realizar cancelamento da matrícula de $usuario. Email informado não está matriculado.\n")
        }
    }

}

fun main() {
    val conteudosKotlin = listOf(
        ConteudoEducacional("Kotlin: Tipos"),
        ConteudoEducacional("Kotlin: Estruturas de Repetição"),
        ConteudoEducacional("Kotlin: Estruturas de Condição"),
        ConteudoEducacional("Kotlin: Tratamento de exceções")
    )

    val formacaoKotlin = Formacao(
        "Kotlin",
        nivel = Nivel.BASICO,
        conteudos = conteudosKotlin
    )

    println(formacaoKotlin)

    formacaoKotlin.matricular(
        Usuario("Danillo", "danillo@email.com"),
        Usuario("Outro Danillo", "danillo@email.com"),
        Usuario("Fulano", "fulano@email.com")
    )

    println("Número de inscritos na formação ${formacaoKotlin.nome} após realização das matrículas: ${formacaoKotlin.numeroInscritos}")
    println()

    formacaoKotlin.cancelarMatricula(
        Usuario("Danillo", "danillo@email.com"),
        Usuario("Outro", "outro@email.com"),
        Usuario("Siclano", "siclano@email.com")
    )

    println("Número de inscritos na formação ${formacaoKotlin.nome} após cancelamento das matrículas: ${formacaoKotlin.numeroInscritos}")
}
