package utilities;

import interfaces.Shape;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by johan on 10/03/16.
 */
public class FileHandler {

    public static void save( ArrayList<Shape> shapes ) throws Exception {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("paint_fx.data");

        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

        // Write object out to disk
        obj_out.writeObject(shapes);
    }

    public static ArrayList<Shape> read() throws Exception {
        // Read from disk using FileInputStream
        FileInputStream f_in = new FileInputStream("paint_fx.data");

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = new ObjectInputStream(f_in);

        // Read an object
        return (ArrayList<Shape>) obj_in.readObject();
    }

}
