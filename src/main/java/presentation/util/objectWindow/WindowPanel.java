package presentation.util.objectWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Another cursed thing that's been developed with every Java assignment since the databases project.
 * This is a scrollable area containing ObjectWindows of the same type.
 * You may also add common actions to said windows by specifying
 * <ul>
 *     <li>a name for the button(String)</li>
 *     <li>something to happen when the button is pressed (ActionListener)</li>
 *     <li>a condition that has to be met in order for the button to be added to the window (Predicate)</li>
 * </ul>
 * <br>
 * If I've used a list of JButtons that were added to every Window, probably it would've been the same button
 * for every single Window (for example, a delete button would've deleted every window in the panel).
 * <br>
 * ngl, this is refactored code from one of my PT assignments (the fourth one to be exact). ofc I wrote this code, it's not just some satanic invocation from stack overflow
 */
public class WindowPanel<T> extends JScrollPane {
    protected JPanel content;
    private JLabel errMessage;
    private JLabel errProcedure;
    private Color windowsColor=Color.gray;
    private Color windowsFontColor=Color.BLACK;
    private Font windowsFont=new Font("Arial",Font.PLAIN,16);
    Consumer<JButton> buttonFormatFunction=(b)-> {};
    Collection<ObjectWindow<T>> windows;
    Iterator<ObjectWindow<T>> currentlyDisplayed;
    List<WindowPanelButton<T>> actions;
    ActionListener editModeListener=null;
    protected Predicate<T> editingCondition=(win)-> false;
    JLabel loadMore;
    int remaining;
    private static final int MAX_CASSETTES=50;

    public WindowPanel(String errMessage,String errProcedure){

        actions=new ArrayList<>();
        content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        loadMore=new JLabel();

        loadMore.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadMore();
                getParent().validate();
                getParent().repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setViewportView(content);
        this.errMessage=new JLabel(errMessage);
        this.errProcedure=new JLabel(errProcedure);
        setBorder(new EmptyBorder(0,0,0,0));
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setMinimumSize(new Dimension(850,400));
        this.windows=new ArrayList<>();
    }
    public void refresh(Collection<T> newWindows){
        content.removeAll();
        content.revalidate();
        content.repaint();
        List<ObjectWindow<T>> windowsFromObj=new ArrayList<>();
        newWindows.stream().forEach(x->{ObjectWindow obj=new ObjectWindow<>(x.getClass(),x);
        obj.setBackground(this.windowsColor);
        obj.setForeground(this.windowsFontColor);
        obj.setFont(this.windowsFont);
        windowsFromObj.add(obj);});
        windows=windowsFromObj;
        currentlyDisplayed= windows.iterator();
        remaining=windows.size();
        if(windows!=null&&(!windows.isEmpty()))
            loadMore();
        else
        {
            content.add(errMessage);
            content.add(errProcedure);
        }
        content.validate();
        content.repaint();
    }
    public void loadMore(){
        remove(loadMore);
        int i=0;
        while(currentlyDisplayed.hasNext()&&i<MAX_CASSETTES)
        {
            ObjectWindow<T> c=currentlyDisplayed.next();
            c.makeEditable(editModeListener,this.editingCondition);
            c.addButtons(actions);
            content.add(c);
            c.formatButton(this.buttonFormatFunction);
            content.add(Box.createRigidArea(new Dimension(0, 10)));
            remaining--;
            i++;
        }
        if(currentlyDisplayed.hasNext())
        {
            loadMore.setText(remaining+" remaining ... Click to load more");
            content.add(loadMore);
        }
    }

    public void addAction(String name,ActionListener l){
        actions.add(new WindowPanelButton(name,l,(x)-> true));
    }
    public void addAction(String name,ActionListener l,Predicate<T> addingConditions){
        actions.add(new WindowPanelButton<>(name,l,addingConditions));
    }
    public void enableEditingMode(ActionListener l,Predicate<T> editableCondition){ editModeListener=l; this.editingCondition=editableCondition;}
    public JPanel getContent(){return this.content;}
    public void setBackground(Color bg){
        if(this.content!=null)
            this.content.setBackground(bg);
    }
    public void setWindowsBackground(Color bg){
        this.windowsColor=bg;
    }
    public void setWindowsFont(Font font){
        this.windowsFont=font;
    }
    public void setWindowsFontColor(Color color){
        this.windowsFontColor=color;
    }
    public void setErrFont(Font font){
        this.errMessage.setFont(font);
        this.errProcedure.setFont(font);
        this.errProcedure.setFont(this.errProcedure.getFont().deriveFont(0.6f));
    }
    public void setButtonFormatFunction(Consumer<JButton> formatFunction){
        this.buttonFormatFunction=formatFunction;
    }
}
