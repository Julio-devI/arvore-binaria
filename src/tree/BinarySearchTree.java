package tree;

import estrutura.Tree;

import java.util.ArrayList;
import java.util.List;

class Node {
    int valor;
    Node esquerdo, direito;

    public Node(int item) {
        this.valor = item;
        this.esquerdo = null;
        this.direito = null;
    }
}

public class BinarySearchTree implements Tree{
    private Node raiz;

    @Override
    public boolean buscaElemento(int valor) {
        return buscarElementoArvore(raiz, valor);
    }

    private boolean buscarElementoArvore(Node node, int valor) {
        if (node == null)
        {
            return false;
        }

        if (valor == node.valor)
        {
            return true;
        }

        if (valor < node.valor)
        {
            return buscarElementoArvore(node.esquerdo, valor);
        }
        else
        {
            return buscarElementoArvore(node.direito, valor);
        }
    }

    @Override
    public int minimo() {
        if (raiz == null)
        {
            System.out.println("Árvore vazia");
        }

        Node current = raiz;
        while (current.esquerdo != null){
            current = current.esquerdo;
        }

        return current.valor;
    }

    @Override
    public int maximo() {
        if (raiz == null)
        {
            throw new IllegalStateException("Árvore Vazia");
        }

        Node current = raiz;
        while (current.direito != null)
        {
            current = current.direito;
        }

        return current.valor;
    }

    @Override
    public void insereElemento(int valor) {
        raiz = insereElementoArvore(raiz, valor);
    }

    private Node insereElementoArvore(Node node, int valor) {
        if (node == null)
        {
            return new Node(valor);
        }

        if (valor < node.valor)
        {
            node.esquerdo = insereElementoArvore(node.esquerdo, valor);
        }
        else if (valor > node.valor)
        {
            node.direito = insereElementoArvore(node.direito, valor);
        }

        return node;
    }

    private int encontraSucessor(Node node) {
        int minimo = node.valor;
        while (node.esquerdo != null) {
            minimo = node.esquerdo.valor;
            node = node.esquerdo;
        }
        return minimo;
    }

    private Node removeArvore(Node node, int valor){
        if (node == null) {
            return null;
        }

        // Busca pelo nó a ser removido
        if (valor < node.valor) {
            node.esquerdo = removeArvore(node.esquerdo, valor);
        } else if (valor > node.valor) {
            node.direito = removeArvore(node.direito, valor);
        } else {
            // Nó encontrado, trata os casos de remoção
            // Caso 1: O nó é uma folha ou tem um único filho
            if (node.esquerdo == null) {
                return node.direito;
            } else if (node.direito == null) {
                return node.esquerdo;
            }

            // Caso 2: O nó tem dois filhos
            // Encontra o sucessor in-order (o menor nó na subárvore direita)
            node.valor = encontraSucessor(node.direito);

            // Remove o sucessor in-order
            node.direito = removeArvore(node.direito, node.valor);
        }

        return node;
    }

    @Override
    public void remove(int valor) {
        raiz = removeArvore(raiz, valor);
    }

    @Override
    public int[] preOrdem() {
        List<Integer> elementos = new ArrayList<>();
        preOrdenarArvore(raiz, elementos);

        int[] array = new int[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            array[i] = elementos.get(i);
        }
        return array;
    }

    private void preOrdenarArvore(Node node, List<Integer> folhas) {
        if (node != null)
        {
            folhas.add(node.valor);
            preOrdenarArvore(node.esquerdo, folhas);
            preOrdenarArvore(node.direito, folhas);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> folhas = new ArrayList<>();
        OrdenarArvore(raiz, folhas);

        int [] array = new int[folhas.size()];
        for (int i = 0; i < folhas.size(); i++)
        {
            array[i] = folhas.get(i);
        }

        return array;
    }

    private void OrdenarArvore(Node node, List<Integer> folhas) {
        if (node != null)
        {
            OrdenarArvore(node.esquerdo, folhas);
            folhas.add(node.valor);
            OrdenarArvore(node.direito, folhas);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> elementos = new ArrayList<>();
        posOrdenarArvore(raiz, elementos);

        int[] array = new int[elementos.size()];
        for (int i = 0; i < elementos.size(); i++) {
            array[i] = elementos.get(i);
        }
        return array;
    }

    private void posOrdenarArvore(Node node, List<Integer> elementos) {
        if (node != null)
        {
            posOrdenarArvore(node.esquerdo, elementos);
            posOrdenarArvore(node.direito, elementos);
            elementos.add(node.valor);
        }
    }

}