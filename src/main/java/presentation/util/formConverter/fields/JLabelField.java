package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class JLabelField extends FormField{
    public JLabelField(){
        super(new JLabel());
    }
    @Override
    public Object getInfo() {
        return null;
    }

    @Override
    public void clear() {

    }
    @Override
    public String getInfoAsString() {
        return ((JLabel)component).getText();
    }
    public void setInfo(char[] info){
        ((JLabel)component).setText(String.valueOf(info));
    }
    public void setInfo(Date info){
        ((JLabel)component).setText((new SimpleDateFormat("dd-MM-yyyy hh:mm")).format(info));
    }
    public void setInfo(Object info){
        if(info==null){
            ((JLabel)component).setText("");
            return;
        }

        if(info instanceof char[])
            setInfo((char[])info);
        else{
            if(info instanceof String)
                setInfo((String)info);
            else{
                if(info instanceof Date)
                    setInfo((Date)info);
                else
                    ((JLabel)component).setText(info.toString());
            }
        }

    }

    @Override
    public void addFieldAction(Runnable f) {

    }

    public void setInfo(String info){
        ((JLabel)component).setText(info);
    }
}
