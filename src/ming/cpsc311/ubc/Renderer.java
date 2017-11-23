package ming.cpsc311.ubc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Renderer implements RendererInterface {
    private HashMap<String, Structure> visited = new HashMap<>();
    private float l = 60;
    @Override
    public void render(Structure s) {
        //setup canvas
        try{
            BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            Graphics2D ig2 = bi.createGraphics();
            Font font = new Font("TimesRoman", Font.BOLD, 25);
            ig2.setFont(font);
            ig2.setPaint(Color.black);
            renderHelper(s, 250, 250, ig2);

            ImageIO.write(bi, "PNG", new File("poop.PNG"));
        } catch (IOException e){
            System.out.println("didnt work dude");
        }
    }

    private void renderHelper(Structure s, float startx, float starty, Graphics2D ig2){
        this.visited.put(s.getvName(), s);
        ig2.drawString(s.geteName(),startx, starty);
        double ogBond = 360 / s.getBonds().size() * Math.PI / 180;
        double bondAngle = ogBond;
        for(Bond bond : s.getBonds()){
            if(visited.get(bond.getStructure().getvName()) == null){
                int endx = (int) (startx + this.l * Math.sin(bondAngle));
                //System.out.println(bondAngle);
                //System.out.println(endx);
                int endy = (int) (starty + this.l * Math.cos(bondAngle));
                int bondType = bond.getBondType();
                if(bondType == 1){
                    Line2D lin = new Line2D.Float(startx, starty, endx, endy);
                    ig2.draw(lin);
                } else if(bondType == 2){
                    int offset = 3;
                    if(s.getBonds().size() == 3){
                        if(bondAngle > 4 && bondAngle < 5){
                            offset = offset + 5;
                        }
                    }
                    Line2D lin1 = new Line2D.Float(startx, starty, endx, endy);
                    Line2D lin2 = new Line2D.Float(startx+offset, starty+offset, endx+offset, endy+offset);
                    ig2.draw(lin1);
                    ig2.draw(lin2);
                } else if(bondType == 3){
                    int offset = 3;
                    if(s.getBonds().size() == 3){
                        if(bondAngle > 4 && bondAngle < 5){
                            offset = offset + 7;
                        }
                    }
                    Line2D lin1 = new Line2D.Float(startx, starty, endx, endy);
                    Line2D lin2 = new Line2D.Float(startx+offset, starty+offset, endx+offset, endy+offset);
                    Line2D lin3 = new Line2D.Float(startx-offset, starty-offset, endx-offset, endy-offset);
                    ig2.draw(lin1);
                    ig2.draw(lin2);
                    ig2.draw(lin3);
                }
                renderHelper(bond.getStructure(), endx, endy, ig2);
            }
            bondAngle = bondAngle + ogBond;
        }
    }
}
