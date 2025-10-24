package apis;

import java.util.UUID;

/**
 * Mock da API do Instagram.
 * Usa uma String (UUID) para o ID de publicacao.
 */
public class InstagramAPI {

    public String uploadMedia(byte[] mediaData, String legenda) throws Exception {
        if (mediaData == null || mediaData.length == 0) {
            throw new NullPointerException("Mídia não pode ser nula no Instagram.");
        }
        System.out.println(">>> INSTAGRAM API: Uploading mídia com legenda: " + legenda.substring(0, 15) + "...");
        // Simula falha ocasional
        if (Math.random() < 0.1) {
             throw new Exception("Falha de limite de taxa do Instagram.");
        }
        return "IG-" + UUID.randomUUID().toString();
    }
}
