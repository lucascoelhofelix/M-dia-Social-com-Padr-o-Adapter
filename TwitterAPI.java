package apis;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Mock da API do Twitter.
 * Usa um Long para o ID de publicacao.
 */
public class TwitterAPI {
    private static final AtomicLong NEXT_ID = new AtomicLong(10000);

    public Long postTweet(String tweetText, String mediaUrl) throws Exception {
        if (tweetText == null || tweetText.length() > 280) {
            throw new IllegalArgumentException("Texto inválido para Twitter.");
        }
        System.out.println(">>> TWITTER API: Postando tweet: " + tweetText.substring(0, 15) + "...");
        // Simula falha ocasional
        if (Math.random() < 0.1) {
             throw new Exception("Falha de autenticação do Twitter.");
        }
        return NEXT_ID.getAndIncrement();
    }
}
