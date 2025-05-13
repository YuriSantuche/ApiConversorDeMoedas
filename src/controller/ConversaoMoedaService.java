package controller;

import model.Moedas;

public class ConversaoMoedaService {

    private String base;
    private String destino;
    private String urlCompleta;

    public ConversaoMoedaService(int escolhaUsuario, String enderecoBase) {
        metodoConversao(escolhaUsuario, enderecoBase);
    }

    public void metodoConversao(int escolhaUsuario, String enderecoBase) {

        switch (escolhaUsuario) {
            case 1 -> {
                base = "USD";
                destino = "BRL";
            }
            case 2 -> {
                base = "USD";
                destino = "EUR";
            }
            case 3 -> {
                base = "BRL";
                destino = "USD";
            }
            case 4 -> {
                base = "BRL";
                destino = "EUR";
            }
            case 5 -> {
                base = "EUR";
                destino = "BRL";
            }
            case 6 -> {
                base = "EUR";
                destino = "USD";
            }
            default -> {
                System.out.println("Opção inválida, por favor, digite um número entre 1 e 7.");
                base = null;
                destino = null;
                return;
            }
        }
        if (base != null) {
            urlCompleta = "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/" + base;
        }
    }

    public double confirmaMoeda(int escolhaUsuario, double soma, Moedas moedas) {

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
        return soma;
    }

    public String getBase() {
        return base;
    }

    public String getDestino() {
        return destino;
    }

    public String getUrlCompleta() {
        return "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/" + base;
    }

    public boolean conversaoValida() {
        return base != null && destino != null;
    }
}
