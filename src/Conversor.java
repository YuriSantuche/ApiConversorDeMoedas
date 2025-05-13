import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {

    public void leituraConversaoMoedas() {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Scanner scanner = new Scanner(System.in);

        int escolhaUsuario = 0;

        while (true) {
            System.out.println("********* Menu *********");
            System.out.println("-Conversor de moedas-");
            System.out.println();
            System.out.println("1: Dolar para BRL: ");
            System.out.println("2: Dolar para EUR: ");
            System.out.println("3: BRL para Dolar");
            System.out.println("4: BRL para EUR");
            System.out.println("5: EUR para BRL");
            System.out.println("6: EUR para Dolar");
            System.out.println("7: para sair.");
            escolhaUsuario = scanner.nextInt();

            if (escolhaUsuario == 7) {
                System.out.println("Finalizando...");
                break;
            }

            String endereco = "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/";

            ConversaoDasMoedas conversaoDasMoedas = new ConversaoDasMoedas(escolhaUsuario, endereco);
            if (!conversaoDasMoedas.conversaoValida()) {
                continue;
            }
            String NewEndereco = conversaoDasMoedas.getUrlCompleta();
            String nomeConversao = conversaoDasMoedas.getDestino();

            scanner.nextLine();
            System.out.println("Digite a quantidade: ");
            double valor = scanner.nextDouble();

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(NewEndereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                RespostaApi resposta = gson.fromJson(json, RespostaApi.class);
                Moedas moedas = resposta.conversion_rates();

                double soma = 0;

                if (escolhaUsuario == 1) {
                    soma = moedas.BRL();
                } else if (escolhaUsuario == 2) {
                    soma = moedas.EUR();
                } else if (escolhaUsuario == 3) {
                    soma = moedas.USD();
                } else if (escolhaUsuario == 4) {
                    soma = moedas.EUR();
                } else if (escolhaUsuario == 5) {
                    soma = moedas.BRL();
                } else if (escolhaUsuario == 6) {
                    soma = moedas.USD();
                }

                System.out.println("Valor " + valor + " [" + resposta.base_code() + "] para [" + nomeConversao + "] = " + soma * valor);

            } catch (IOException | InterruptedException | JsonSyntaxException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
