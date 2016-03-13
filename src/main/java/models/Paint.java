package models;

import command.CommandHandler;
import enums.ShapeType;
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

    public void drawOnCanvas(Attribute attr){
        if(attr.getType() != null){
            Shape shape = ShapeFactory.getShape(attr);
            shape.draw(gc);
        }
    }

    @Override
    public void draw(Attribute attr){
        if(attr.getType() == null){
            return;
        }
        currentList.add(attr.clone());
        updateCanvas(getCurrentList());
        submitToStack(getCurrentList());
        stackRedo = new Stack<>();
    }

    @Override
    public void undo(){
        if(stack.size() > 1){
            submitToRedoStack(new ArrayList<>(stack.pop()));
            List<Attribute> oldState = new ArrayList<>(stack.peek());
            updateCanvas(oldState);
            updateCurrentList(oldState);
        } else if (stack.size() == 1) {
            submitToRedoStack(new ArrayList<>(stack.pop()));
            updateCanvas(new ArrayList<>());
            updateCurrentList(new ArrayList<>());
        } else {
            // The stack is empty. There is nothing to paint
            Log.i("STACK IS EMPTY!");
        }

    }

    @Override
    public void redo() {
        if(stackRedo.size() > 0){
            List<Attribute> oldState = new ArrayList<>(stackRedo.pop());
            updateCurrentList(oldState);
            updateCanvas(getCurrentList());
            submitToStack(getCurrentList());
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
            this.stack = new Stack<>();
            this.stackRedo = new Stack<>();
            this.currentList = FileHandler.read();
            updateCanvas(getCurrentList());
            submitToStack(getCurrentList());
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public void fill(double x, double y, String color, int lineSize){
        Attribute attr;
        for (int i = 0; i< this.currentList.size(); i++){
            attr = this.currentList.get(i).clone();
            if (x >= attr.getX() && x <= attr.getX() + 80) {
                if (y < attr.getY() + 80 && y > attr.getY()) {
                    if(attr.getType() == ShapeType.LINE){
                        attr.setColorStroke(color);
                        attr.setLineWidth(lineSize);
                    } else {
                        attr.setColorFill(color);
                        attr.setLineWidth(lineSize);
                    }
                    this.currentList.set(i,attr);
                    updateCanvas(getCurrentList());
                    submitToStack(getCurrentList());
                    stackRedo = new Stack<>();
                    break;

                }
            }
        }

    }

    public void delete(double x, double y){
        Attribute attr;
        for (int i = 0; i< this.currentList.size(); i++){
            attr = this.currentList.get(i).clone();
            if (x >= attr.getX() && x <= attr.getX() + 80) {
                if (y < attr.getY() + 80 && y > attr.getY()) {
                    this.currentList.remove(i);
                    updateCanvas(getCurrentList());
                    submitToStack(getCurrentList());
                    stackRedo = new Stack<>();
                    break;
                }
            }
        }

    }

    private void updateCanvas(List<Attribute> shapeList){
        canvas.cleaCanvas();
        for(Attribute attr: shapeList){
            drawOnCanvas(attr);
        }
    }

    private void updateCurrentList(List<Attribute> list){
        this.currentList = list;
    }

    private void submitToStack(List<Attribute> list){
        this.stack.add(list);
        Log.i("STACK SIZE: " + stack.size());
        Log.i("STACK REDO SIZE: " + stackRedo.size());
    }

    private void submitToRedoStack(List<Attribute> list){
        stackRedo.add(list);
        Log.i("STACK SIZE: " + stack.size());
        Log.i("STACK REDO SIZE: " + stackRedo.size());
    }


}
