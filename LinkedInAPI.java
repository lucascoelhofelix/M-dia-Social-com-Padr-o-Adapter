package apis;

import java.time.Instant;

/**
 * Mock da API do LinkedIn.
 * Usa um String (Timestamp) para o ID de publicacao.
 */
public class LinkedInAPI {

    public String shareContent(String text, boolean isCompanyUpdate) throws Exception {
        if (text == null || text.length() < 10) {
            throw new IllegalArgumentException("Conteúdo muito curto para LinkedIn.");
        }
        System.out.println(">>> LINKEDIN API: Compartilhando post (" + (isCompanyUpdate ? "Empresa" : "Pessoal") + "): " + text.substring(0, 15) + "...");
        // Simula falha ocasional
        if (Math.random() < 0.1) {
             throw new Exception("Sessão expirada no LinkedIn.");
        }
        return "LI-" + Instant.now().getEpochSecond();
    }
}
