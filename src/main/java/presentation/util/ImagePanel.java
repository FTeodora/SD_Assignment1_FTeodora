package presentation.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class ImagePanel extends JPanel {
    Image image;
    public ImagePanel(String imageUrl){
        try{
            File f=new File(System.getProperty("user.dir")+"/src/main/resources/images");
            File imf=new File(f,"/"+imageUrl);
            //System.out.println(imf.getAbsolutePath());
            image = ImageIO.read(imf);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
