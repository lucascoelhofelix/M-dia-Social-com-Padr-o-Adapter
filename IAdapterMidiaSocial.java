package adapters;

import core.Conteudo;
import core.RespostaPublicacao;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Interface TARGET unificada para todos os adapters (Padrão Adapter).
 * Usa Generics para manter o tipo de ID nativo.
 * A lock é usada para garantir Thread-Safety (Requisito 5) na execução da publicação,
 * embora a complexidade da concorrência esteja mais no Gerenciador.
 */
public interface IAdapterMidiaSocial<T> {

    // Lock para garantir a thread-safety nas operações críticas do adapter
    ReentrantLock LOCK = new ReentrantLock();

    /**
     * Publica o conteúdo na mídia social específica.
     * @param conteudo O objeto Conteudo unificado.
     * @return Uma RespostaPublicacao unificada contendo o ID nativo da plataforma.
     */
    RespostaPublicacao<T> publicar(Conteudo conteudo);

    /**
     * Obtém o nome da plataforma.
     * @return O nome da plataforma (ex: "Twitter", "Instagram").
     */
    String getPlataforma();
}
