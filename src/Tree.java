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
     * constructor of class Tree
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
     * recursive function to walk through the tree
     * inserts newElement if the child element is null
     *
     * @param e : TElement : current element
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
     * checks cases of deleting an element
     *
     * we have 3 cases to delete an element
     * case1 = left = null or right = null
     * case2 = left and right = null
     * case3 = left and right != null
     *
     * @param e : TElement : parent of delElement
     * @param delElement : TElement : element to delete
     * @return TElement or null : result will be used for replacing delElement
     * @version 2.0
     */
    private TElement _handleDeleteElement(TElement e, TElement delElement) {
        if (delElement.getLeft() == null) {
           if (delElement.getRight() == null) {
               // case2 - left and right = null
               // delete right or left child of e
               return null;
            } else {
                // case1 - left = null and right != null
                // returns right child of delElement
               return delElement.getRight();
            }
        } else if (delElement.getLeft() != null){
            if (delElement.getRight() == null) {
                // case1 - left != null and right = null
                // returns left child of delElement
                return delElement.getLeft();
            } else {
                // case3 - left and right != null
                // move delElement.getRight one layer on top
                e.setRight(delElement.getRight());

                // reinsert delElement.getLeft
                _insertElement(e.getRight(), delElement.getLeft());

                // returns right child of delElement
                return delElement.getRight();
            }
        }
        return null;
    }

    /**
     * recursive function to delete an element
     *
     * the function walks through the tree and searches for the delElement
     * then it calls a function to handle the deletion
     *
     * @param e : TElement : current element
     * @param delElement : TElement : element to delete
     * @return : if an element was delete
     * @version 2.0
     */
    private void _deleteElement(TElement e, TElement delElement)
    {
        // exists the element
        if (e == null) {
            return;
        }

        // is the delElement greater then e
        if (e.getData() <= delElement.getData()) {
            // right element of current element like delElement
            if (e.getRight() == delElement) {
                e.setRight(_handleDeleteElement(e, delElement));
                return;
            } else if (this.root == delElement) {
                if (delElement.getLeft() == null) {
                    if (delElement.getRight() != null) {
                        // case1
                        // set right element as new root
                        this.root = delElement.getRight();
                    }
                } else {
                    if (delElement.getRight() == null) {
                        // case1
                        // set left element as new root
                        this.root = delElement.getLeft();
                    } else {
                        // case3
                        // set right element as new root
                        this.root = delElement.getRight();

                        // save delElement.getLeft in a helper object delElementLeft
                        TElement delElementLeft = delElement.getLeft();

                        // insert delElementLeft
                        _insertElement(this.root, delElementLeft);
                    }
                }
            } else {
                _deleteElement(e.getRight(), delElement);
            }
        } else {
            if (e.getLeft() == delElement) {
                e.setLeft(_handleDeleteElement(e, delElement));
                return;
            } else {
                _deleteElement(e.getLeft(), delElement);
            }
        }
    }


    /**
     * OrderTypes to sort the tree
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

    /**
     * calls a recursive function to delete elements
     *
     * @param delElements
     * @version 2.0
     */
    public void deleteElement(ArrayList<TElement> delElements)
    {
        for (TElement e : delElements) {
            _deleteElement(this.root, e);
        }
    }

    /**
     * search for elements by data
     *
     * gets all elements as a list and search for the element to be searched
     *
     * @param data : int : number of the element
     * @return elements : ArrayList<TElement> : all elements who had the same data
     * @version 1.0
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
     * @version 2.0
     */
    public Point2D _draw(AnchorPane pane, TElement e, double x, double y, double width, double height)
    {
        // check that the current element exist
        if (e == null) {
            return null;
        }

        // set element size
        final double MAX_ELEMENT_SIZE = 50;
        final double RADIUS_X = 20;
        final double RADIUS_Y = 20;

        // get the right and left part of tree
        ArrayList<TElement> left = _getElements(e.getLeft(), OrderType.InOrder);
        ArrayList<TElement> right = _getElements(e.getRight(), OrderType.InOrder);

        // get left an right size (min 1)
        int leftSize = Math.max(left.size(), 1);
        int rightSize = Math.max(right.size(), 1);

        // calc the space between the elements
        double elementSpace = width / (leftSize + rightSize);

        // draw the element as ellipse
        Ellipse element = new Ellipse(x + width / 2, y + (MAX_ELEMENT_SIZE/2), RADIUS_X, RADIUS_Y);

        // set the fill color
        element.setFill(Color.CORNFLOWERBLUE);

        // add the element to pane
        pane.getChildren().add(element);

        // add notice to element
        Text t = new Text();
        t.setFont(new Font(20));
        t.setX(x + width / 2 - 11);
        t.setY(y + (MAX_ELEMENT_SIZE/2) + 8);
        t.setText(String.valueOf(e.getData()));
        pane.getChildren().add(t);

        // save the last element position to connect them with a line
        ArrayList<Point2D> points = new ArrayList();

        points.add(_draw(pane, e.getLeft(), x, y + MAX_ELEMENT_SIZE, elementSpace * leftSize, height - MAX_ELEMENT_SIZE));
        points.add(_draw(pane, e.getRight(), x + elementSpace * leftSize, y + MAX_ELEMENT_SIZE, elementSpace * rightSize, height - MAX_ELEMENT_SIZE));

        // draw a line between the elements
        for (Point2D point : points) {
            if (point != null)
            {
                pane.getChildren().add(new Line(point.getX(), point.getY(), x + width / 2, y + (MAX_ELEMENT_SIZE/2) + RADIUS_Y));
            }
        }

        // return the current element position
        return new Point2D(x + width / 2, y + (MAX_ELEMENT_SIZE/2) - RADIUS_Y);
    }

    /**
     * calls a recursive function to draw the tree
     *
     * @param anchorPane : AnchorPane : pane of stage to draw the tree
     * @version 2.0
     */
    public void draw(AnchorPane anchorPane)
    {
        _draw(anchorPane, this.root, 0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }


}
