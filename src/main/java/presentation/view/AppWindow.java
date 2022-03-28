package presentation.view;

import presentation.controller.Controller;
import presentation.util.Navbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AppWindow extends JFrame {
    public static final Color HEADER_COLOR=new Color(17, 27, 37);
    public static final Color TEXT_COLOR=new Color(17, 27, 37);
    public static final Color OBJECT_WINDOW_COLOR=new Color(255, 255, 255);
    public static final Color OBJECT_WINDOW_TEXT_COLOR=new Color(23, 22, 22);
    public static final Color BACKGROUND_COLOR=new Color(210, 210, 210);
    public static final Color BUTTON_BACKGROUND=new Color(0, 173, 236);
    public static final Color BUTTON_FOREGROUND=new Color(213, 213, 213);
    public static final Font BUTTON_FONT=new Font("Arial",Font.BOLD,14);
    public static final Font HEADER_FONT=new Font("Segoe UI",Font.BOLD,21);
    public static final Font TITLE_FONT=new Font("Bahnschrift",Font.PLAIN,30);
    public static final Font FORM_FONT=new Font("Segoe UI",Font.PLAIN,21);
    public static final Font WINDOW_FONT=new Font("Cambria",Font.BOLD,20);
    AppWindow previous;
    Navbar mainNavbar;
    JButton refresh,back;
    JPanel content=new JPanel();
    Controller controller;
    public AppWindow(String title,AppWindow previous){
        super(title);
        this.setIconImage(getIcon("images/sigla.png").getImage());
        JScrollPane scrollPane=new JScrollPane(content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setBackground(AppWindow.BACKGROUND_COLOR);
        this.previous=previous;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(previous.getSize());
        this.setMinimumSize(previous.getSize());
        this.setLocationRelativeTo(previous);
        this.setLayout(new BorderLayout());
        this.refresh=new JButton("refresh");
        refresh.setBackground(HEADER_COLOR);
        refresh.setForeground(TEXT_COLOR);
        refresh.addActionListener(e->{this.refresh();});
        refresh.setPreferredSize(new Dimension(50,50));
        addIconToButton(refresh,"images/refresh_icon.png");

        this.back=new JButton();
        back.setBackground(HEADER_COLOR);
        back.setForeground(TEXT_COLOR);
        back.setPreferredSize(new Dimension(65,65));
        addIconToButton(back,"images/back_button.png");
        mainNavbar=new Navbar(HEADER_COLOR);
        mainNavbar.addLeftOption(back);
        mainNavbar.addLeftOption(refresh);
        back.addActionListener(e->{
            previous.setVisible(true);
        this.dispose();});
        this.add(mainNavbar,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        this.previous.setVisible(false);
    }
    public AppWindow(int width,int height,String title){
        super(title);
        this.setIconImage(getIcon("images/sigla.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }
    public void formatButton(JButton button){
        button.setBackground(AppWindow.BUTTON_BACKGROUND);
        button.setForeground(AppWindow.BUTTON_FOREGROUND);
        button.setFont(AppWindow.BUTTON_FONT);
        button.setPreferredSize(new Dimension(75,35));
        button.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
    }
    public void addIconToButton(JButton button,String imageName){
        button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        button.setIcon(getIcon(imageName));
    }
    public ImageIcon getIcon(String imageName){
        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(imageName);
            //System.out.println(classLoader.getResource(imageName).getPath());
            BufferedImage image = ImageIO.read(input);
            return new ImageIcon(image);
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void refresh(){
        this.validate();
        this.repaint();
    }
    public Controller getController(){
        return this.controller;
    }

}
