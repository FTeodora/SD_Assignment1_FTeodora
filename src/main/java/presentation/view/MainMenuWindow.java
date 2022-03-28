package presentation.view;

import dataTransferObjects.SessionDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainMenuWindow extends AfterLogInWindow{
    ArrayList<JButton> options;
    JPanel optionsPane;
    public MainMenuWindow(LogInWindow previous, SessionDTO session) {
        super(previous, session);
        createOptions();
    }

    public MainMenuWindow(AfterLogInWindow previous) {
        super(previous);
        createOptions();
    }
    public void createOptions(){
        this.options=new ArrayList<>();
        optionsPane=new JPanel();
        optionsPane.setBackground(AppWindow.BACKGROUND_COLOR);
        optionsPane.setLayout(new GridLayout(0,1));
        content.setLayout(new BorderLayout());
        content.add(optionsPane, BorderLayout.CENTER);
    }
    public void addOptions(){
        this.options.forEach(x->{
            x.setBackground(AppWindow.HEADER_COLOR);
            x.setForeground(AppWindow.BACKGROUND_COLOR);
            x.setFont(AppWindow.HEADER_FONT);
            x.setBorder(BorderFactory.createEmptyBorder(5,70,5,70));
            x.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            optionsPane.add(x);
        optionsPane.add(Box.createRigidArea(new Dimension(0, 10)));});
    }
}
