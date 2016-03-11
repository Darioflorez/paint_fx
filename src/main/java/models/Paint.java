package models;

import command.CommandHandler;
import factory.ShapeFactory;
import interfaces.Shape;
import javafx.scene.canvas.GraphicsContext;
import uiComponents.ResizableCanvas;
import utilities.FileHandler;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by dario on 2016-03-08.
 */
public class Paint implements CommandHandler{

    //Track the current state of the canvas
    private List<Attribute> currentList;
    private GraphicsContext gc;

    private Stack<List<Attribute>> stack;
    private Stack<List<Attribute>> stackRedo;

    private ResizableCanvas canvas;

    public Paint(ResizableCanvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.currentList = new ArrayList<>();
        this.stack = new Stack<>();
        this.stackRedo = new Stack<>();
    }

    public List<Attribute> getCurrentList(){
        return new ArrayList<>(this.currentList);
    }

    public Shape drawOnCanvas(Attribute attr){
        if(attr.getType() == null){
            return null;
        }
        Shape shape = ShapeFactory.getShape(attr);
        shape.draw(gc);
        return shape;
    }

    @Override
    public void draw(Attribute attr){
        if(attr.getType() == null){
            return;
        }
        Shape shape = drawOnCanvas(attr);
        currentList.add(shape.attribute.clone());
        stack.add(getCurrentList());
        Log.i("DRAW STACK SIZE: " + stack.size());
    }

    @Override
    public void undo(){
        if(stack.size() > 1){
            canvas.cleaCanvas();
            stackRedo.add(new ArrayList<>(stack.pop()));
            List<Attribute> oldState = stack.peek();
            this.currentList = new ArrayList<>();
            for(Attribute attr: oldState){
                Shape shape = drawOnCanvas(attr);
                this.currentList.add(shape.attribute.clone());
            }
        } else if (stack.size() == 1) {
            canvas.cleaCanvas();
            stackRedo.add(new ArrayList<>(stack.pop()));
        } else {
            Log.i("STACK IS EMPTY!");
        }
    }

    @Override
    public void redo() {
        Log.i("STACK REDO SIZE: " + stackRedo.size());
        if(stackRedo.size() > 0){
            canvas.cleaCanvas();
            List<Attribute> oldState = new ArrayList<>(stackRedo.pop());
            this.currentList = new ArrayList<>();
            for(Attribute attr: oldState){
                Shape shape = drawOnCanvas(attr);
                this.currentList.add(shape.attribute.clone());
            }
            stack.add(getCurrentList());
        } else {
            Log.i("STACK REDO EMPTY!");
        }

    }

    @Override
    public void save() {
        try{
            FileHandler.save(getCurrentList());
            Log.i("Saving " + getCurrentList().size() + "number of objects");
        }catch(Exception e){
            System.err.println(e);
        }
    }

    @Override
    public void loadCanvas(){
        try{
            this.currentList = new ArrayList<>();
            this.stack = new Stack<>();
            this.stackRedo = new Stack<>();
            List<Attribute> savedState = FileHandler.read();
            canvas.cleaCanvas();
            for(Attribute attr: savedState){
                Shape shape = drawOnCanvas(attr);
                this.currentList.add(shape.attribute.clone());
            }
            this.stack.add(getCurrentList());
        }catch(Exception e){
            System.err.println(e);
        }
    }


}
