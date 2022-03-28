package presentation.view;

import dataTransferObjects.UserRequestDTO;
import entity.LegalDocumentType;
import entity.enums.RequestStatus;
import presentation.controller.ManageRequestsUserController;
import presentation.controller.UserController;
import presentation.util.formConverter.SearchForm;
import presentation.util.objectWindow.WindowPanel;
import presentation.util.objectWindow.WindowPanelButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ManageRequestsUser extends ManagementWindow<UserRequestDTO> {
    SearchForm<LegalDocumentType> typeSelector;
    WindowPanel<LegalDocumentType> typeOptions;
    JFrame selectType;
    LegalDocumentType selectedType;
    public ManageRequestsUser(AfterLogInWindow previous) {
        super("My requests", previous, "You haven't submitted any requests yet","You can submit up to 3 a year");
        this.controller=new ManageRequestsUserController((UserController) previous.controller);
        this.newTypeButton=new JButton("Make a request");
        formatButton(newTypeButton);
        newTypeButton.setPreferredSize(new Dimension(200,30));
        typeOptions=new WindowPanel<>("There are no results","");
        typeOptions.setButtonFormatFunction(b->formatButton(b));
        typeOptions.setWindowsBackground(AppWindow.OBJECT_WINDOW_COLOR);
        typeOptions.setWindowsFontColor(AppWindow.OBJECT_WINDOW_TEXT_COLOR);
        typeOptions.setWindowsFont(AppWindow.WINDOW_FONT);
        typeOptions.addAction("Select",
                event -> {
                    selectedType=((WindowPanelButton<LegalDocumentType>)event.getSource()).getObject();

                    selectType.dispose();
                    addInsertNewEntityOption(UserRequestDTO.class,
                            r->{
                                r.setType(this.selectedType);
                                return ((ManageRequestsUserController)controller).submitRequest(r);
                            },"request",false);
                    input.setVisible(true);
                });

        this.managedEntities.addAction("cancel",e->{
            ((ManageRequestsUserController)controller).cancelRequest(((WindowPanelButton<UserRequestDTO>)e.getSource()).getObject());
            refresh();
        },u->u.getStatus().equals(RequestStatus.WAITING));

        selectType=new JFrame("Choose a type for the document");

        selectType.setSize(new Dimension(900,700));
        selectType.setLocationRelativeTo(this);
        selectType.setResizable(false);
        this.newTypeButton.addActionListener(e->{
            List<LegalDocumentType> availableTypes=((ManageRequestsUserController)controller).fetchDocumentTypes();
            if(!availableTypes.isEmpty()){
                typeOptions.refresh(availableTypes);
                selectType.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(this, "You can't make anymore requests this year");
            }
            selectType.add(typeOptions);
        });
        this.footer.addRightOption(newTypeButton);
        refresh();
    }
    @Override
    public void refresh(){
        this.managedEntities.refresh(((ManageRequestsUserController)controller).fetchMyRequests());
        super.refresh();
    }
}
