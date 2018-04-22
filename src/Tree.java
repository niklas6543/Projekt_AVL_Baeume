import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class Tree {

    private int size;
    private Random rand = new Random();
    private TElement root;

    /**
     * constructor from class Tree
     * generates elements and build the tree
     *
     * @param size : int : size of tree / count of elements
     * @version 1.0
     */
    public Tree(int size) {

        this.size = size;

        // get root element
        int data = rand.nextInt((100 - 1) + 1) + 1;
        this.root = new TElement(data, null, null);


        for (int i = 0; i < size-1; i++) {
            // create new element
            data = rand.nextInt((100 - 1) + 1) + 1;
            TElement newElement = new TElement(data, null, null);

            // insert new element
            _insertElement(this.root, newElement);
        }

    }

    /**
     * tests sorting (In-/Pre-/PostOrder)
     *
     * @version 1.0
     */
    public void debug() {
        this.insertElement(new TElement(50, null, null));
        for (TElement e: this.getElementsPreOrder()) {
            e.debug();
        }

        System.out.println("\n*** INORDER ***");

        for (TElement e: this.getElementsInOrder()) {
            System.out.printf(" - %s", e.getData());
        }

        System.out.println("\n\n*** PREORDER ***");

        for (TElement e: this.getElementsPreOrder()) {
            System.out.printf(" - %s", e.getData());
        }

        System.out.println("\n\n*** POSTORDER ***");

        for (TElement e: this.getElementsPostOrder()) {
            System.out.printf(" - %s", e.getData());
        }
    }

    /**
     * insert new elements
     *
     * @param e : TElement : root element to start
     * @param newElement : TElement : element to insert
     * @version 1.0
     */
    private void _insertElement(TElement e, TElement newElement) {
        // exist TElement e?
        if (e == null) {
            return;
        }

        // is newElement higher then e
        if (e.getData() <= newElement.getData()){
            // has the element children
            if (e.getRight() == null) {
                e.setRight(newElement);
            } else {
                // insert new element
                _insertElement(e.getRight(), newElement);
            }

        }else
        {
            // has the element children
            if (e.getLeft() == null) {
                e.setLeft(newElement);
            } else {
                // insert new element
                _insertElement(e.getLeft(), newElement);
            }

        }
    }

    /**
     * recursive function to delete an element
     *
     * @param e : TElement : current element
     * @param delElement : TElement : element to delete
     */
    private void _deleteElement(TElement e, TElement delElement)
    {
        // exists the element
        if (e == null) {
            return;
        }

        // is the delElement greater then e
        if (e.getData() <= delElement.getData())
        {
            // right element of current like delElement
            if (e.getRight() == delElement) {
                // changes right element of the current element to left child of delElement
                e.setRight(delElement.getLeft());
                delElement.getLeft().setRight(delElement.getRight());
                return;
            }
            _deleteElement(e.getRight(), delElement);

        }
        else
        {
            // left element of current like delElement
            if (e.getLeft() == delElement) {
                // changes left element of the current element to right child of delElement
                e.setLeft(delElement.getRight());
                delElement.getRight().setLeft(delElement.getLeft());
                return;
            }
            _deleteElement(e.getLeft(), delElement);
        }
    }

    /**
     * OrderTypes for sorting the tree
     */
    public enum OrderType {
        InOrder,
        PostOrder,
        PreOrder
    }

    /**
     * recursive function to walk through the tree
     * add elements in different orders to a list
     *
     * @param e : TElement : starting element
     * @param orderType : OrderType : the type of order (In-/Pre-/PostOrder)
     * @return result : ArrayList<TElement>
     * @version 1.0
     */
    private ArrayList<TElement> _getElements(TElement e, OrderType orderType){
        ArrayList<TElement> result = new ArrayList<>();

        if (e != null) {
            if (orderType == OrderType.InOrder) {
                // go left - get element - go right
                result.addAll(_getElements(e.getLeft(), orderType));
                result.add(e);
                result.addAll(_getElements(e.getRight(), orderType));

            } else if (orderType == OrderType.PreOrder) {
                // get element - go left - go right
                result.add(e);
                result.addAll(_getElements(e.getLeft(), orderType));
                result.addAll(_getElements(e.getRight(), orderType));

            } else if (orderType == OrderType.PostOrder) {
                // go left - go right - get element
                result.addAll(_getElements(e.getLeft(), orderType));
                result.addAll(_getElements(e.getRight(), orderType));
                result.add(e);
            }
        }

        return result;

    }

    /**
     * insert a new element
     * calls a recursive function _insertElement for inserting
     *
     * @param newElement : TElement : element to insert
     * @return returns true if the item was successfully added
     * @version 1.0
     */
    public void insertElement(TElement newElement) {
        this._insertElement(this.root, newElement);
    }

    public void deleteElement(ArrayList<TElement> delElements)
    {
        for (TElement e : delElements) {
            _deleteElement(root, e);
        }
    }

    /**
     * search for elements by data
     *
     * @param data : int : number of the element
     * @return elements : ArrayList<TElement> : all elements who had the same data
     */
    public ArrayList<TElement> findElementByData(int data) {
        ArrayList<TElement> elements = new ArrayList<>();

        for (TElement e : this._getElements(this.root, OrderType.InOrder)){
            if (e.getData() == data) {
                elements.add(e);
            }
        }

        return elements;
    }

    /**
     * sorts elements inorder
     * calls a recursive function for sorting
     *
     * @return ArrayList<TElement>() : all elements
     * @version 1.0
     */
    public ArrayList<TElement> getElementsInOrder() {
        return this._getElements(this.root, OrderType.InOrder);
    }

    /**
     * sorts elements preorder
     * calls a recursive function for sorting
     *
     * @return ArrayList<TElement>() : all elements
     * @version 1.0
     */
    public ArrayList<TElement> getElementsPreOrder() {
        return this._getElements(this.root, OrderType.PreOrder);
    }

    /**
     * sorts elements postorder
     * calls a recursive function for sorting
     *
     * @return ArrayList<TElement>() : all elements
     * @version 1.0
     */
    public ArrayList<TElement> getElementsPostOrder() {
        return this._getElements(this.root, OrderType.PostOrder);
    }

    /**
     * returns the root element of tree
     *
     * @return root : TElement : root element of tree
     */
    public TElement getRootElement() {
        return this.root;
    }

    /**
     * recursive function to draw the tree
     *
     * first getting the maximal size to draw the current element
     * then draw the element and draw a line to pre element
     *
     * @param pane : AnchorPane : the pane to draw the tree
     * @param e : TElement : current element
     * @param x : double : x coordinate of element
     * @param y : double : y coordinate of element
     * @param width : double : max width of current elements
     * @param height : double : max height of current elements
     * @return ArrayList<Points2D>() : return the current element position
     */
    public Point2D _draw(AnchorPane pane, TElement e, double x, double y, double width, double height)
    {
        // check that the current element exist
        if (e == null) {
            return null;
        }

        // set element size
        double ELEMENT_SIZE = 50.0D;

        // get the right and left part of tree
        ArrayList<TElement> left = _getElements(e.getLeft(), OrderType.InOrder);
        ArrayList<TElement> right = _getElements(e.getRight(), OrderType.InOrder);

        // get left an right size (min 1)
        int leftSize = Math.max(left.size(), 1);
        int rightSize = Math.max(right.size(), 1);

        // calc the space between the elements
        double elementSpace = width / (leftSize + rightSize);

        // draw the element as ellipse
        Ellipse element = new Ellipse(x + width / 2.0D, y + 25.0D, 16.666666666666668D, 16.666666666666668D);

        // set the fill color
        element.setFill(Color.CORNFLOWERBLUE);

        // add the element to pane
        pane.getChildren().add(element);

        // add notice to element
        Text t = new Text();
        t.setFont(new Font(20.0D));
        t.setX(x + width / 2.0D - 11.111111111111112D);
        t.setY(y + 25.0D + 8.333333333333334D);
        t.setText(String.valueOf(e.getData()));
        pane.getChildren().add(t);

        // save the last element position to connect them with a line
        ArrayList<Point2D> points = new ArrayList();

        points.add(_draw(pane, e.getLeft(), x, y + 50.0D, elementSpace * leftSize, height - 50.0D));
        points.add(_draw(pane, e.getRight(), x + elementSpace * leftSize, y + 50.0D, elementSpace * rightSize, height - 50.0D));

        // draw a line between the elements
        for (Point2D point : points) {
            if (point != null)
            {
                pane.getChildren().add(new Line(point.getX(), point.getY(), x + width / 2.0D, y + 25.0D + 16.666666666666668D));
            }
        }

        // return the current element position
        return new Point2D(x + width / 2.0D, y + 25.0D - 16.666666666666668D);
    }

    /**
     * calls a recursive function to draw the tree
     *
     * @param anchorPane : AnchorPane : pane of stage to draw the tree
     */
    public void draw(AnchorPane anchorPane)
    {
        _draw(anchorPane, root, 0.0D, 0.0D, anchorPane.getWidth(), anchorPane.getHeight());
    }


}
