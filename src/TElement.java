public class TElement {

    private int data;
    private TElement left;
    private TElement right;

    /**
     * constructor from class TElement
     *
     * @param data : int : data from element
     * @param left : TElement : left element
     * @param right : TElement : right element
     * @version 1.0
     */
    public TElement(int data, TElement left, TElement right) {
        this.data = data;
        this.left = left;
        this.right = right;

    }

    /**
     * prints element information
     * @version 1.0
     */
    public void debug() {
        System.out.printf("*** DATA: %s - LEFT: %s - RIGHT: %s ***\n",
                this.getData(),
                this.getLeft() != null ? this.getLeft().getData() : null,
                this.getRight() != null ? this.getRight().getData() : null
        );

    }

    // getter and setters

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TElement getLeft() {
        return left;
    }

    public void setLeft(TElement left) {
        this.left = left;
    }

    public TElement getRight() {
        return right;
    }

    public void setRight(TElement right) {
        this.right = right;
    }
}
