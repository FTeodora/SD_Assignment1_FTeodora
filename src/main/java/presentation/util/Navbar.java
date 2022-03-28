package presentation.util;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class Navbar extends JPanel{
    List<JButton> options;
    JPanel rightButtonContent;
    JPanel leftButtonContent;
    JPanel centerContent;
    public Navbar(Color color){
        super();
        setBackground(color);
        setLayout(new BorderLayout());
        rightButtonContent=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        rightButtonContent.setBackground(color);

        centerContent=new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerContent.setBackground(color);

        leftButtonContent=new JPanel(new FlowLayout(FlowLayout.LEADING));
        leftButtonContent.setBackground(color);

        this.options=new ArrayList<>();
        JPanel dummy=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        dummy.setBackground(color);
        this.add(dummy,BorderLayout.WEST);
        this.add(rightButtonContent,BorderLayout.EAST);
        this.add(centerContent,BorderLayout.CENTER);
        this.add(leftButtonContent,BorderLayout.WEST);
    }
    public void addRightOption(JButton option){
        options.add(option);
        rightButtonContent.add(option);
    }
    public void addLeftOption(JButton option){
        options.add(option);
        leftButtonContent.add(option);
    }
    public void addCenterContent(JComponent content){
        centerContent.add(content);
    }
    public void addExtraButtonContent(Object position){

    }

}
