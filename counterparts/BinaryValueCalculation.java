package counterparts;

import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryValueCalculation {

    public static void calculateBanayValuesForEachCordinates(HashMap<Integer, ArrayList<Double>> findAverageXCordinates) 
            throws FileNotFoundException, UnsupportedEncodingException {
        
        ArrayList<Double> yCordinates = findAverageXCordinates.get(0);
        ArrayList<Double> xCordinates = findAverageXCordinates.get(1);
        URL url;
        BufferedImage image = null;
        boolean whitePixel = false;
        PrintWriter writer = new PrintWriter(".\\src\\com\\edu\\sliit\\img\\the-file-name.txt", "UTF-8");
        double binaryValue=0;
        try {
            image = ImageIO.read(new FileInputStream(".\\src\\com\\edu\\sliit\\img\\dilate.jpg"));
        } catch (Exception ex) {
            Logger.getLogger(BinaryValueCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }//Math.round(x)
        
        for (int i = 0; i < yCordinates.size(); i++) {
            for (int j = 0; j < xCordinates.size(); j++) {
                int clr = image.getRGB(Integer.parseInt(Math.round(yCordinates.get(i)) + ""), Integer.parseInt(Math.round(xCordinates.get(j)) + ""));
                if (isWhitePixel(clr)) {
                    whitePixel = true;
                    //System.out.println("");
                } else {
                    //System.out.println("whitePixel Before : "+whitePixel);
                    whitePixel = isWhitePixelFurther(Integer.parseInt(Math.round(yCordinates.get(i)) + ""), Integer.parseInt(Math.round(xCordinates.get(j)) + ""), image);
//                    if(whitePixel){
//                        System.out.println("i : "+i+" j : "+j);
//                        System.out.println("whitePixel After : "+whitePixel);
//                    }
                    
                }
                //Calculate binary Values
                //Write to Exel
                if(i%2==0){
                    if(j%3==0 && whitePixel ){
                       binaryValue=binaryValue+1;
                    }
                    else if(j%3==1 && whitePixel ){
                        binaryValue=binaryValue+2;
                    }
                    else if(j%3==2){
                        if(whitePixel){
                            binaryValue=binaryValue+4;
                        }                       
                        //PoI Write to Exel
                        writer.println("First Column of Brailee : "+binaryValue);
                        writer.println("");
                        binaryValue=0;
                    }
                }
                else if(i%2==1){
                    if(j%3==0 && whitePixel){
                        binaryValue=binaryValue+8;
                    }
                    else if(j%3==1 && whitePixel){
                        binaryValue=binaryValue+16;
                    }
                    else if(j%3==2){
                        if(whitePixel){
                             binaryValue=binaryValue+32;
                        }
                        //PoI Write to Exel
                        writer.println("Second Column of Brailee : "+binaryValue);
                        writer.println("");
                        binaryValue=0;
                    }
                }                   
                whitePixel = false;
            }
        }
        writer.close();
    }

    public static boolean isWhitePixel(int clr) {
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        //System.out.println("red: " + red + " green: " + green + " blue: " + blue);
        if (red > 235 && green > 235 && blue > 235) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWhitePixelFurther(int x, int y, BufferedImage image) {
        boolean whitePixel = false;
        outer:
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                whitePixel = isWhitePixel(image.getRGB(i, j));
                //System.out.println("####### x : " + j + " y : " + i);
                if (whitePixel) {
                    break outer;
                }

            }
        }
        return whitePixel;
    }
}
