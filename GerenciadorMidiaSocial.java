package core;

import adapters.IAdapterMidiaSocial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Gerenciador principal que usa o padrão Adapter.
 * Mantém o pool de threads para operações assíncronas (Requisito 5 - Thread-safe).
 * O uso de IAdapterMidiaSocial<?> aqui implementa implicitamente o Padrão Strategy.
 */
public class GerenciadorMidiaSocial {
    private final List<IAdapterMidiaSocial<?>> adapters;
    private final ExecutorService executor;

    public GerenciadorMidiaSocial(List<IAdapterMidiaSocial<?>> adapters) {
        this.adapters = Objects.requireNonNull(adapters);
        // Pool de threads com limite fixo para processar publicações (Thread-safe)
        this.executor = Executors.newFixedThreadPool(Math.min(adapters.size(), 10));
    }

    /**
     * Publica o conteúdo em todas as mídias sociais configuradas de forma assíncrona.
     * @param conteudo O Conteudo unificado a ser publicado.
     * @return Uma lista de RespostaPublicacao (Future) para rastrear os resultados.
     */
    public List<Future<RespostaPublicacao<?>>> publicarEmTodas(Conteudo conteudo) {
        List<Future<RespostaPublicacao<?>>> resultadosFuturos = new ArrayList<>();

        System.out.println("\n[GERENCIADOR]: Agendando publicação do Conteúdo: " + conteudo.getId() + " em " + adapters.size() + " plataformas.");

        for (IAdapterMidiaSocial<?> adapter : adapters) {
            // A submissão da tarefa ao executor garante a thread-safety da operação.
            Future<RespostaPublicacao<?>> future = executor.submit(() -> {
                // Chama o método 'publicar' no adapter específico (Strategy Pattern)
                return adapter.publicar(conteudo);
            });
            resultadosFuturos.add(future);
        }

        return resultadosFuturos;
    }

    /**
     * Encerra o pool de threads.
     */
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Força o encerramento
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("\n[GERENCIADOR]: Serviço de execução encerrado.");
    }
}
