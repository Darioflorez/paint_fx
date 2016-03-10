package utilities;

import interfaces.Shape;
import models.Attribute;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


/**
 * Created by johan on 10/03/16.
 */
public class FileHandler {

    public static void save( List<Attribute> shapes ) throws Exception {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("paint_fx.data");

        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

        Log.i(FileHandler.class.getResource("/").getPath());

        // Write object out to disk
        obj_out.writeObject(shapes);
    }

    public static List<Attribute> read() throws Exception {
        // Read from disk using FileInputStream
        FileInputStream f_in = new FileInputStream("paint_fx.data");

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = new ObjectInputStream(f_in);

        // Read an object
        return (List<Attribute>) obj_in.readObject();
    }

}
