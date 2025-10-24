SISTEMA DE INTEGRAÇÃO DE APIS DE MÍDIA SOCIAL COM PADRÃO ADAPTER

Este projeto implementa um sistema unificado para gerenciar publicações em múltiplas plataformas de mídia social (Twitter, Instagram, LinkedIn), utilizando o Padrão Adapter para lidar com APIs heterogêneas.

O objetivo é fornecer uma interface de publicação única, independentemente dos modelos de dados e protocolos específicos de cada rede social, garantindo uma arquitetura flexível e extensível.

Padrões de Design Utilizados

O projeto se apoia fortemente em três padrões de design:

    Adapter: Utilizado para unificar as interfaces de APIs incompatíveis. O sistema principal interage apenas com a interface IAdapterMidiaSocial, enquanto os Adapters (como TwitterAdapter) traduzem as chamadas para as APIs originais. (Visto em *Adapter.java e IAdapterMidiaSocial.java)

    Factory Method: Implementado para criar dinamicamente a instância do Adapter apropriado com base na plataforma configurada, permitindo a configuração dinâmica do sistema. (Visto em AdapterFactory.java)

    Strategy (Implícito): O GerenciadorMidiaSocial usa o objeto IAdapterMidiaSocial<?> para executar a estratégia de publicação (o método publicar), trocando a estratégia em tempo de execução para a plataforma desejada. (Visto em GerenciadorMidiaSocial.java)

Arquitetura do Sistema

A estrutura de diretórios do projeto é organizada por responsabilidade:

    core: Contém o núcleo do sistema: modelos de dados unificados (Conteudo.java), o formato de resposta unificado (RespostaPublicacao.java, usando Generics) e a interface principal do cliente (GerenciadorMidiaSocial.java, que é o TARGET).

    adapters: Contém a interface TARGET (IAdapterMidiaSocial.java) e todas as implementações concretas do Adapter (TwitterAdapter.java, InstagramAdapter.java, etc.), que fazem a conversão entre o núcleo e as APIs.

    apis (MOCKS): Contém as classes que simulam as APIs originais (*API.java). Essas classes não são modificadas.

    factory: Contém a classe AdapterFactory.java, responsável pela criação dinâmica dos Adapters.

    Main.java: A classe principal de demonstração e execução.

Requisitos Técnicos Atendidos

O projeto adere estritamente aos requisitos técnicos:

    Não Modificar APIs Originais: As classes em apis/*.java são tratadas como código legado imutável.

    Composition over Inheritance: Os adapters usam composição (possuem instâncias das APIs originais) em vez de herança.

    Tratamento de Erro Granular: As exceções específicas das APIs são capturadas nos adapters e traduzidas em mensagens claras dentro da RespostaPublicacao.

    Uso de Generics: A classe RespostaPublicacao<T> usa Generics para encapsular o ID nativo da plataforma (ex: Long para Twitter, String para Instagram).

    Thread-safe: O GerenciadorMidiaSocial utiliza um ExecutorService para processamento assíncrono e ReentrantLock nos Adapters para garantir a segurança das chamadas de API.

Como Executar o Projeto

1. Pré-requisitos

    Java Development Kit (JDK) 8 ou superior.

2. Compilação e Execução (Linha de Comando)

Assumindo que você está no diretório raiz do projeto (socialmedia-adapter-system):

    Crie a estrutura de diretórios e organize os arquivos Java.

    Compile os arquivos:
    Bash

javac -d out core/*.java adapters/*.java apis/*.java factory/*.java Main.java

Execute a classe principal:
Bash

    java -cp out Main

Saída Esperada

A execução demonstrará o uso do sistema com diferentes tipos de conteúdo, incluindo falhas esperadas (ex: tentar postar apenas texto no Instagram, que requer mídia) e o sucesso na publicação nas plataformas compatíveis.

--- SISTEMA DE INTEGRAÇÃO DE APIS DE MÍDIA SOCIAL (ADAPTER) ---

... (Logs de execução e chamadas de API) ...

--- INÍCIO: Publicação Somente Texto ---
...
-> RESULTADO: RespostaPublicacao [Plataforma=Instagram, Sucesso=false, ID Nativo=N/A, Erro=ERRO (Conteúdo): Instagram requer mídia (imagem/vídeo).]
-> RESULTADO: RespostaPublicacao [Plataforma=Twitter, Sucesso=true, ID Nativo=10001, Erro=Nenhum]
..
