package ming.cpsc311.ubc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.geom.Line2D;


import javax.imageio.ImageIO;

class WriteImageType {
  static public void main(String args[]) throws IOException {
    try {
      int width = 200, height = 200;

      //setting the background size
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

      Graphics2D ig2 = bi.createGraphics();

      //setting font stuff
      Font font = new Font("TimesRoman", Font.BOLD, 20);
      ig2.setFont(font);
      //in our case this would be ename of structure
      String message = "www.java2s.com!";
      // render elements as just strings of ename of structure
      ig2.setPaint(Color.black);
      // 2 ints for offset// drawing the string
      ig2.drawString(message,50, 100);
      //1= x start point
      //2= y start point
      //3= x end point
      //4= y end point 
      Line2D lin = new Line2D.Float(0, 0, 100, 100);
      //draw the line
      ig2.draw(lin);

      //write to png file, can also write to pdf or any format really
      //ImageIO.write(bi, "PNG", new File("c:\\yourImageName.PNG"));
      //ImageIO.write(bi, "JPEG", new File("c:\\yourImageName.JPG"));
      //ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
      //ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));

      
      //writes poop.png to where this java file is
      ImageIO.write(bi, "PNG", new File("poop.PNG"));
      
    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }
}