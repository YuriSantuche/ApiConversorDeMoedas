package service;

import model.Moedas;
import model.RespostaApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import controller.ConversaoMoedaService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMoedasApp {

    public void ConversorMoedasApp() {

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

            if (!scanner.hasNextInt()) {
                System.out.println("Digite um número valido.");
                scanner.next();
                continue;
            }

            escolhaUsuario = scanner.nextInt();

            if (escolhaUsuario == 7) {
                System.out.println("Finalizando...");
                break;
            }

            String endereco = "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/";

            ConversaoMoedaService conversaoDasMoedas = new ConversaoMoedaService(escolhaUsuario, endereco);
            if (!conversaoDasMoedas.conversaoValida()) {
                continue;
            }
            endereco = conversaoDasMoedas.getUrlCompleta();
            String nomeConversao = conversaoDasMoedas.getDestino();

            System.out.println("Digite a quantidade: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Digite um valor número valido.");
                scanner.next();
                continue;
            }
            double valor = scanner.nextDouble();

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
                soma = conversaoDasMoedas.confirmaMoeda(escolhaUsuario, soma, moedas);

                System.out.println("Valor " + valor + " [" + resposta.base_code() + "] ==> [" + nomeConversao + "] = " + soma * valor);
                System.out.println();

            } catch (IOException | InterruptedException | JsonSyntaxException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
