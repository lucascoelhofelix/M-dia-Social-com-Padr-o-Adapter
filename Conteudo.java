package core;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa o conteúdo a ser publicado de forma unificada.
 */
public class Conteudo {
    private final String id;
    private final String texto;
    private final String urlMidia;
    private final TipoConteudo tipo;

    public enum TipoConteudo {
        TEXTO, IMAGEM, VIDEO
    }

    public Conteudo(String texto, String urlMidia, TipoConteudo tipo) {
        this.id = UUID.randomUUID().toString();
        this.texto = Objects.requireNonNull(texto);
        this.urlMidia = urlMidia;
        this.tipo = Objects.requireNonNull(tipo);
    }

    // Getters
    public String getId() { return id; }
    public String getTexto() { return texto; }
    public String getUrlMidia() { return urlMidia; }
    public TipoConteudo getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Conteudo{" +
                "texto='" + texto.substring(0, Math.min(20, texto.length())) + "..." + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
