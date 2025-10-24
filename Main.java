import core.Conteudo;
import core.GerenciadorMidiaSocial;
import core.RespostaPublicacao;
import factory.AdapterFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import static core.Conteudo.TipoConteudo;
import static factory.AdapterFactory.Plataforma;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- SISTEMA DE INTEGRAÇÃO DE APIS DE MÍDIA SOCIAL (ADAPTER) ---");

        // --- TAREFA 3: Configuração Dinâmica com Factory ---
        GerenciadorMidiaSocial gerenciador = null;
        try {
            List adapters = Arrays.asList(
                    AdapterFactory.getAdapter(Plataforma.TWITTER),
                    AdapterFactory.getAdapter(Plataforma.INSTAGRAM),
                    AdapterFactory.getAdapter(Plataforma.LINKEDIN)
                    // AdapterFactory.getAdapter(Plataforma.TIKTOK) // Teste de erro de implementação
            );
            
            gerenciador = new GerenciadorMidiaSocial(adapters);

        } catch (IllegalArgumentException e) {
            System.err.println("ERRO DE CONFIGURAÇÃO: " + e.getMessage());
            return;
        }


        // --- CENÁRIO 1: Publicação de Imagem/Mídia (Deve funcionar em IG, LI, TW) ---
        Conteudo postMultimidia = new Conteudo(
                "Confira nossa nova campanha de marketing! #design #adapter",
                "http://midia.com/campanha_img.jpg",
                TipoConteudo.IMAGEM
        );
        System.out.println("\n\n--- INÍCIO: Publicação Multimídia ---");
        List<Future<RespostaPublicacao<?>>> resultados1 = gerenciador.publicarEmTodas(postMultimidia);
        processarResultados(resultados1);


        // --- CENÁRIO 2: Publicação Apenas Texto Curto (Deve funcionar em TW, LI, e falhar em IG) ---
        Conteudo postSomenteTexto = new Conteudo(
                "Um pequeno pensamento para o dia.",
                null,
                TipoConteudo.TEXTO
        );
        System.out.println("\n\n--- INÍCIO: Publicação Somente Texto ---");
        List<Future<RespostaPublicacao<?>>> resultados2 = gerenciador.publicarEmTodas(postSomenteTexto);
        processarResultados(resultados2);


        // --- CENÁRIO 3: Conteúdo Inválido para uma API (Ex: muito curto para LI) ---
        Conteudo postInvalido = new Conteudo(
                "Curto",
                null,
                TipoConteudo.TEXTO
        );
        System.out.println("\n\n--- INÍCIO: Publicação com Conteúdo Inválido (Teste de Erro Granular) ---");
        List<Future<RespostaPublicacao<?>>> resultados3 = gerenciador.publicarEmTodas(postInvalido);
        processarResultados(resultados3);

        // Encerramento
        gerenciador.shutdown();
    }

    /**
     * Processa e exibe os resultados das operações assíncronas.
     */
    private static void processarResultados(List<Future<RespostaPublicacao<?>>> futures) {
        for (Future<RespostaPublicacao<?>> future : futures) {
            try {
                // Espera o resultado da thread (bloqueante, apenas para demonstração)
                RespostaPublicacao<?> resposta = future.get();
                System.out.println("-> RESULTADO: " + resposta);
            } catch (Exception e) {
                System.err.println("ERRO INESPERADO AO OBTER RESULTADO: " + e.getMessage());
            }
        }
    }
}
