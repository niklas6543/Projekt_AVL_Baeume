public class Main {
    public static void main(String[] args){
        Tree tree = new Tree(5);
        System.out.println("*** ROOT ELEMENT: "+tree.getRootElement().getData()+" *** \n");

        for (TElement e: tree.getElementsPreOrder()) {
            e.debug();
        }

        System.out.println();

        tree.debug();

        System.out.println("\n\n*** SEARCHING FOR 10 ***");
        System.out.println(tree.findElementByData(10));
    }
}
