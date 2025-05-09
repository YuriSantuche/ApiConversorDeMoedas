import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int leitor = 0;

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
            leitor = scanner.nextInt();

            if (leitor == 7) {
                break;
            }

            scanner.nextLine();
            System.out.println("Digite a quantidade: ");
            double valor = scanner.nextDouble();

            String endereco = "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/";

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String nome = "";

            if (leitor == 1) {
                endereco += "USD";
                nome = "BRL";
            } else if (leitor == 2) {
                endereco += "USD";
                nome = "EUR";
            } else if (leitor == 3) {
                endereco += "BRL";
                nome = "Dolar";
            } else if (leitor == 4) {
                endereco += "BRL";
                nome = "EUR";
            } else if (leitor == 5) {
                endereco += "EUR";
                nome = "BRL";
            } else if (leitor == 6) {
                endereco += "EUR";
                nome = "Dolar";
            }


            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                RespostaApi resposta = gson.fromJson(json, RespostaApi.class);
                Moedas moedas = resposta.conversion_rates();

                double soma = 0;

                if (leitor == 1) {
                    soma = moedas.BRL();
                } else if (leitor == 2) {
                    soma = moedas.EUR();
                } else if (leitor == 3) {
                    soma = moedas.USD();
                } else if (leitor == 4) {
                    soma = moedas.EUR();
                } else if (leitor == 5) {
                    soma = moedas.BRL();
                } else if (leitor == 6) {
                    soma = moedas.USD();
                }

                System.out.println("Valor " + valor + " [" + resposta.base_code() + "] para [" + nome + "] = " + soma * valor);

            } catch (IOException | InterruptedException | JsonSyntaxException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }


}

