package presentation.view;

import dataTransferObjects.ViewPersonalDataDTO;
import presentation.controller.AfterLogInController;
import presentation.controller.PersonalDataController;
import presentation.util.Navbar;
import presentation.util.formConverter.Form;

import javax.swing.*;
import java.awt.*;


public class PersonalDataWindow extends AfterLogInWindow {
    Form<ViewPersonalDataDTO> personalData;
    Navbar footer;
    JButton myProperties;
    public PersonalDataWindow(AfterLogInWindow previous) {
        super(previous);
        this.controller=new PersonalDataController((AfterLogInController) previous.getController());
        personalData=new Form<ViewPersonalDataDTO>(ViewPersonalDataDTO.class,((PersonalDataController)controller).getPersonalData());
        this.content.add(personalData.getFormZone());
        personalData.setFont(AppWindow.FORM_FONT);
        personalData.setForeground(AppWindow.TEXT_COLOR);
        personalData.setBackground(AppWindow.OBJECT_WINDOW_COLOR);


        myProperties=new JButton("Manage personal properties");
        myProperties.addActionListener(e->{new ManageMyPropertiesWindow(this);});
        formatButton(myProperties);
        myProperties.setPreferredSize(new Dimension(300,30));
        footer=new Navbar(AppWindow.HEADER_COLOR);
        footer.addRightOption(myProperties);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(true);

    }
}
