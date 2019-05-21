/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterparts;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Demo {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imgGrayscale = new Mat();
        Mat image = Imgcodecs.imread(".\\src\\com\\edu\\sliit\\img\\brailee.jpg", 1);
        HashMap<Integer, double[]> findRectangle = new BraileeDetect(image).findRectangle();
        FindXCordinates f =new FindXCordinates();
        HashMap<Integer, ArrayList<Double>> findAverageXCordinates = f.findAverageXCordinates(findRectangle); 
        try {
            BinaryValueCalculation.calculateBanayValuesForEachCordinates(findAverageXCordinates);
            // CountWhireSpots c=new CountWhireSpots();
            // c.drawALine(findAverageXCordinates);    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//        System.out.println("Width : "+image.width());
//       System.out.println("Height  : "+image.height());