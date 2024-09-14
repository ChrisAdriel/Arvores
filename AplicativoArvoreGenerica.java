import java.util.*;

class No<T> {
    T item;
    List<No<T>> filhos;

    public No(T item) {
        this.item = item;
        this.filhos = new ArrayList<>();
    }

    public void adicionarFilho(No<T> filho) {
        filhos.add(filho);
    }

    public List<No<T>> getFilhos() {
        return filhos;
    }

    public T getItem() {
        return item;
    }
}

class ArvoreGenerica<T> {
    private No<T> raiz;

    public ArvoreGenerica() {
        raiz = null;
    }

    public void inserir(T valor, T valorPai) {
        No<T> novo = new No<>(valor);
        if (raiz == null) {
            raiz = novo;
            return;
        }

        No<T> pai = buscar(raiz, valorPai);
        if (pai != null) {
            pai.adicionarFilho(novo);
        } else {
            System.out.println("Nó pai não encontrado.");
        }
    }

    private No<T> buscar(No<T> no, T valor) {
        if (no == null) return null;
        if (no.getItem().equals(valor)) return no;

        for (No<T> filho : no.getFilhos()) {
            No<T> resultado = buscar(filho, valor);
            if (resultado != null) return resultado;
        }
        return null;
    }

    public boolean remover(T valor) {
        return remover(raiz, valor, null);
    }

    private boolean remover(No<T> no, T valor, No<T> pai) {
        if (no == null) return false;
        if (no.getItem().equals(valor)) {
            if (pai == null) {
                raiz = null;
            } else {
                pai.getFilhos().remove(no);
            }
            return true;
        }

        for (No<T> filho : no.getFilhos()) {
            if (remover(filho, valor, no)) return true;
        }
        return false;
    }

    public No<T> buscar(T valor) {
        return buscar(raiz, valor);
    }

    public void caminhar() {
        System.out.println("\n***********************************\n");
        System.out.println("Árvore:\n");
        imprimirArvore(raiz, "", 0);
        System.out.println("\n***********************************\n");
        System.out.println("Graus dos nós:\n");
        imprimirGraus(raiz, "", 0);
        System.out.println("\n***********************************\n");
        System.out.println("Altura dos nós:\n");
        imprimirAltura(raiz, "", 0);
        System.out.println("\n***********************************\n");
        System.out.println("Nós Folha:\n");
        imprimirNosFolha(raiz, "", 0);
        System.out.println("\n***********************************\n");
        System.out.println("Profundidade dos nós:\n");
        imprimirProfundidade(raiz, "", 0);
        System.out.println("\n***********************************\n");
        System.out.println("Níveis dos nós:\n");
        imprimirNiveis(raiz, 0);
    }

    public void exibirSubarvores() {
        System.out.println("\n***********************************\n");
        System.out.println("Subárvores:\n");
        exibirSubarvores(raiz, "");
    }

    private void exibirSubarvores(No<T> no, String indentacao) {
        if (no != null) {
            System.out.println(indentacao + "Subárvore com raiz: " + no.getItem());
            imprimirArvore(no, indentacao + "  ", 0);
            System.out.println();
            for (No<T> filho : no.getFilhos()) {
                exibirSubarvores(filho, indentacao + "  ");
            }
        }
    }

    private void imprimirArvore(No<T> no, String indentacao, int profundidade) {
        if (no != null) {
            System.out.println(indentacao + no.getItem());
            for (No<T> filho : no.getFilhos()) {
                imprimirArvore(filho, indentacao + "  ", profundidade + 1);
            }
        }
    }

    private void imprimirGraus(No<T> no, String indentacao, int profundidade) {
        if (no != null) {
            int grau = no.getFilhos().size();
            System.out.println(indentacao + "Nó " + no.getItem() + " tem grau: " + grau);
            for (No<T> filho : no.getFilhos()) {
                imprimirGraus(filho, indentacao + "  ", profundidade + 1);
            }
        }
    }

    private void imprimirAltura(No<T> no, String indentacao, int profundidade) {
        if (no != null) {
            int altura = altura(no);
            System.out.println(indentacao + "Nó " + no.getItem() + " tem altura: " + altura);
            for (No<T> filho : no.getFilhos()) {
                imprimirAltura(filho, indentacao + "  ", profundidade + 1);
            }
        }
    }

    private void imprimirNosFolha(No<T> no, String indentacao, int profundidade) {
        if (no != null) {
            if (no.getFilhos().isEmpty()) {
                System.out.println(indentacao + "Nó folha: " + no.getItem());
            }
            for (No<T> filho : no.getFilhos()) {
                imprimirNosFolha(filho, indentacao + "  ", profundidade + 1);
            }
        }
    }

    private void imprimirProfundidade(No<T> no, String indentacao, int profundidade) {
        if (no != null) {
            System.out.println(indentacao + "Nó " + no.getItem() + " tem profundidade: " + profundidade);
            for (No<T> filho : no.getFilhos()) {
                imprimirProfundidade(filho, indentacao + "  ", profundidade + 1);
            }
        }
    }

    private void imprimirNiveis(No<T> no, int nivel) {
        if (no != null) {
            System.out.println("Nó " + no.getItem() + " está no nível: " + nivel);
            for (No<T> filho : no.getFilhos()) {
                imprimirNiveis(filho, nivel + 1);
            }
        }
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(No<T> no) {
        if (no == null) return -1;
        int alturaMaxima = -1;
        for (No<T> filho : no.getFilhos()) {
            alturaMaxima = Math.max(alturaMaxima, altura(filho));
        }
        return 1 + alturaMaxima;
    }

    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(No<T> no) {
        if (no == null) return 0;
        int contagem = 1;
        for (No<T> filho : no.getFilhos()) {
            contagem += contarNos(filho);
        }
        return contagem;
    }

    public int folhas() {
        return folhas(raiz);
    }

    private int folhas(No<T> no) {
        if (no == null) return 0;
        if (no.getFilhos().isEmpty()) return 1;
        int contagem = 0;
        for (No<T> filho : no.getFilhos()) {
            contagem += folhas(filho);
        }
        return contagem;
    }
}

public class AplicativoArvoreGenerica {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArvoreGenerica<Integer> arvore = new ArvoreGenerica<>();
            int opcao;
            int valor;
            Integer valorPai;
            
            System.out.println("\n\nÁrvore Genérica");
            
            do {
                System.out.println("\n***********************************");
                System.out.println("Escolha uma opção:");
                System.out.println("--> 1: Inserir");
                System.out.println("--> 2: Excluir");
                System.out.println("--> 3: Pesquisar");
                System.out.println("--> 4: Exibir");
                System.out.println("--> 5: Exibir Subárvores");
                System.out.println("--> 6: Sair do programa");
                System.out.println("***********************************\n");
                System.out.print("-> ");
                opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1 -> {
                        System.out.print("\nInforme o valor do nó a ser inserido: ");
                        valor = scanner.nextInt();
                        System.out.print("Informe o valor do nó pai (ou digite 'null' para definir como raiz): ");
                        String inputPai = scanner.next();
                        valorPai = inputPai.equals("null") ? null : Integer.valueOf(inputPai);
                        arvore.inserir(valor, valorPai);
                    }
                    case 2 -> {
                        System.out.print("Informe o valor do nó a ser removido: ");
                        valor = scanner.nextInt();
                        if (!arvore.remover(valor)) {
                            System.out.println("Valor não encontrado!");
                        }
                    }
                    case 3 -> {
                        System.out.print("Informe o valor a ser pesquisado: ");
                        valor = scanner.nextInt();
                        if (arvore.buscar(valor) != null) {
                            System.out.println("Valor Encontrado");
                        } else {
                            System.out.println("Valor não encontrado!");
                        }
                    }
                    case 4 -> {
                        if (arvore.altura() == -1) {
                            System.out.println("Árvore vazia! Insira os dados a serem exibidos!");
                        } else {
                            arvore.caminhar();
                        }
                    }
                    case 5 -> {
                        if (arvore.altura() == -1) {
                            System.out.println("Árvore vazia! Insira os dados a serem exibidos!");
                        } else {
                            arvore.exibirSubarvores();
                        }
                    }
                }
            } while (opcao != 6);
        }
    }
}
