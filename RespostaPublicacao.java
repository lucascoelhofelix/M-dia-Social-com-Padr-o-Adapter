package core;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Resposta unificada de uma operação de publicação, usando Generics para o ID específico.
 * @param <T> O tipo de ID nativo da plataforma (ex: String para Twitter, Long para Instagram)
 */
public class RespostaPublicacao<T> {
    private final boolean sucesso;
    private final String plataforma;
    private final Optional<T> idNativo;
    private final Optional<String> mensagemErro;
    private final LocalDateTime timestamp;

    // Construtor de Sucesso
    public RespostaPublicacao(String plataforma, T idNativo) {
        this.sucesso = true;
        this.plataforma = plataforma;
        this.idNativo = Optional.of(idNativo);
        this.mensagemErro = Optional.empty();
        this.timestamp = LocalDateTime.now();
    }

    // Construtor de Falha
    public RespostaPublicacao(String plataforma, String mensagemErro) {
        this.sucesso = false;
        this.plataforma = plataforma;
        this.idNativo = Optional.empty();
        this.mensagemErro = Optional.of(mensagemErro);
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public boolean isSucesso() { return sucesso; }
    public String getPlataforma() { return plataforma; }
    public Optional<T> getIdNativo() { return idNativo; }
    public Optional<String> getMensagemErro() { return mensagemErro; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "RespostaPublicacao [Plataforma=" + plataforma +
               ", Sucesso=" + sucesso +
               ", ID Nativo=" + idNativo.map(Object::toString).orElse("N/A") +
               ", Erro=" + mensagemErro.orElse("Nenhum") +
               "]";
    }
}
