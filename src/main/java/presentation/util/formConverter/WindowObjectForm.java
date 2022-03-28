package presentation.util.formConverter;

import presentation.util.formConverter.fields.FormField;
import presentation.util.objectWindow.SwingWindow;

import javax.swing.*;
import java.awt.*;

public class WindowObjectForm<T> extends Form<T>{
    JPanel head,center,left,right;
    public WindowObjectForm(Class<T> parameterClass, T object) {
        super();
        this.generatedObject=object;
        this.fieldZone.setLayout(new BorderLayout());
        this.type=parameterClass;

        head=new JPanel();
        head.setLayout(new GridLayout(1,0));
        fieldZone.add(head,BorderLayout.NORTH);

        JPanel displayWrapper=new JPanel(new GridLayout(1,3));

        left=new JPanel(new GridLayout(0,1));
        displayWrapper.add(left);

        center=new JPanel(new GridLayout(0,1));
        displayWrapper.add(center);

        right=new JPanel(new GridLayout(0,1));
        displayWrapper.add(right);

        this.fieldZone.add(displayWrapper,BorderLayout.CENTER);
        this.objectToForm();
        this.fieldList.stream().filter(x->{
            try {
                return this.type.getDeclaredField(x.getRealName()).isAnnotationPresent(SwingWindow.class);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        }).forEach(x->{
            try {
                SwingWindow annotation=this.type.getDeclaredField(x.getRealName()).getAnnotationsByType(SwingWindow.class)[0];
                switch (annotation.positionInWindow()){
                    case CENTER-> {center.add(x.getFieldView());
                    }
                    case RIGHT ->{ right.add(x.getFieldView());
                    }
                    case LEFT -> {left.add(x.getFieldView());
                    }
                    case UP -> head.add(x.getFieldView());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        completeFields(object);
    }
    @Override
    public void setBackground(Color bg){
        super.setBackground(bg);
        this.head.setBackground(bg);
        this.center.setBackground(bg);
        this.left.setBackground(bg);
        this.right.setBackground(bg);
    }
}
