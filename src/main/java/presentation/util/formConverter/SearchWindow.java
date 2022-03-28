package presentation.util.formConverter;

import presentation.util.formConverter.Form;
import presentation.util.objectWindow.WindowPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.function.Function;

public class SearchWindow<T> {
    JPanel everything=new JPanel(new BorderLayout());
    JPanel switchMode=new JPanel(new CardLayout());
    JPanel filterMode=new JPanel();
    JComponent parent=null;
    SearchForm<T> form;
    private WindowPanel<T> resultsPanel;
    private JButton makeVisible;
    private JButton extraFilters;
    private JComboBox objectFieldsForSorting;
    private ButtonGroup order;
    private JRadioButton ascending;
    private JRadioButton descending;
    private boolean isVisible=false;
    Function<T, Collection<T>> resultSupplier;
    public SearchWindow(Class objectClass, WindowPanel<T> resultsPanel, Function<T, Collection<T>> resultSupplier){
        makeVisible=new JButton("Search");
        this.resultSupplier=resultSupplier;
        this.resultsPanel=resultsPanel;
        form=new SearchForm<T>(objectClass);
        form.getFormZone().setVisible(isVisible);
        form.additionalFieldAction(()->{
            T obj=form.completeAndGetObject();
            if(obj!=null)
                resultsPanel.refresh(resultSupplier.apply(obj));
        });
        switchMode.add(form.getFormZone());
        switchMode.add(filterMode);
        everything.add(switchMode);
    }
    public JPanel getPanel(){
        return this.everything;
    }
    public void setParent(JComponent parent){
        this.parent=parent;
    }
}
