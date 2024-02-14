import tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree b = new BinarySearchTree();
        b.insereElemento(1);
        b.insereElemento(2);
        b.insereElemento(3);
        b.insereElemento(10);
        b.insereElemento(20);

        System.out.println(b.buscaElemento(1));

        System.out.println(b.emOrdem());
    }

}
