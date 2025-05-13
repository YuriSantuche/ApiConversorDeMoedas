# 💱 Conversor de Moedas em Java

Este projeto é um conversor de moedas desenvolvido em Java, utilizando a API ExchangeRate para obter taxas de câmbio em tempo real. Ele permite a conversão entre diferentes moedas através de uma interface de linha de comando (CLI).

## 🚀 Funcionalidades

- Conversão entre:
  - USD ↔️ BRL
  - USD ↔️ EUR
  - BRL ↔️ EUR
  - BRL ↔️ USD
  - EUR ↔️ BRL
  - EUR ↔️ USD
- Leitura de dados em tempo real via API externa
- Interface via terminal (CLI)
- Validação de entrada do usuário
- Estrutura de código modular seguindo conceitos do padrão MVC

##############################################################

🛠️ Tecnologias e Ferramentas

-Java 17+

-GSON (para desserialização de JSON)

-Java HttpClient (API nativa para chamadas HTTP)

-IntelliJ IDEA 

-Git

##############################################################

📦 Como executar o projeto

1. Clone o repositório:

git clone https://github.com/YuriSantuche/ApiConversorDeMoedas.git

cd ApiConversorDeMoedas

2. Abra o projeto em sua IDE favorita.

3. Certifique-se de ter o Java 17+ instalado.

4. Execute a classe Main.java.

##############################################################

📌 Observações
O projeto segue uma estrutura modular separando responsabilidades entre:

model (modelos de dados)

service (lógica de conversão)

view (interação com o usuário)

controller (coordenação entre view e service)

O código está preparado para futuras expansões, como suporte a mais moedas ou interface gráfica.

##############################################################

🙋‍♂️ Autor
Desenvolvido por Yuri Santuche
