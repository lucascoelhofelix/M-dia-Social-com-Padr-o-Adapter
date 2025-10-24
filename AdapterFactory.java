package factory;

import adapters.IAdapterMidiaSocial;
import adapters.InstagramAdapter;
import adapters.LinkedInAdapter;
import adapters.TwitterAdapter;

/**
 * Factory Method para criação dinâmica de Adapters (Tarefa 3).
 * Simula a configuração dinâmica de ambiente.
 */
public class AdapterFactory {

    public enum Plataforma {
        TWITTER, INSTAGRAM, LINKEDIN, TIKTOK // Adicionar mais conforme necessário
    }

    /**
     * Cria e retorna uma instância do Adapter apropriado com base na plataforma.
     * @param plataforma A plataforma desejada.
     * @return O IAdapterMidiaSocial específico.
     * @throws IllegalArgumentException Se a plataforma não for suportada.
     */
    public static IAdapterMidiaSocial<?> getAdapter(Plataforma plataforma) {
        // Uso de Generics '<?>', pois a Factory não precisa saber o tipo exato do ID.
        switch (plataforma) {
            case TWITTER:
                return new TwitterAdapter();
            case INSTAGRAM:
                return new InstagramAdapter();
            case LINKEDIN:
                return new LinkedInAdapter();
            case TIKTOK:
                // Exemplo de plataforma não implementada
                throw new IllegalArgumentException("Adapter TikTok ainda não implementado.");
            default:
                throw new IllegalArgumentException("Plataforma de Mídia Social desconhecida: " + plataforma);
        }
    }
}
