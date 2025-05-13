public class ConversaoDasMoedas {

    private String base;
    private String destino;
    private String urlCompleta;

    public ConversaoDasMoedas(int escolhaUsuario, String enderecoBase) {
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
                System.out.println("Opção inválida.");
                base = null;
                destino = null;
                return;
            }
        }
        if (base != null) {
            urlCompleta = "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/" + base;
        }
    }

    public String getBase() {
        return base;
    }

    public String getDestino() {
        return destino;
    }

    public String getUrlCompleta() {
        return "https://v6.exchangerate-api.com/v6/97fac4ce92c661165ca63e3c/latest/"+base;
    }

    public boolean conversaoValida() {
        return base != null && destino != null;
    }
}
