package presentation.util.formConverter;

import presentation.util.formConverter.fields.FormField;
import service.validators.InputException;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SearchForm<T> extends  Form<T>{
    public SearchForm(Class<T> objectClass){
        super(objectClass);
    }

    @Override
    public void objectToForm(){
        for(Field field  : this.type.getDeclaredFields())
        {
            if (field.isAnnotationPresent(SearchSwingField.class))
            {
                SearchSwingField fieldClass=field.getAnnotation(SearchSwingField.class);
                try{
                    FormField f=(FormField) fieldClass.componentType().getDeclaredConstructor().newInstance();
                    f.setRealName(field.getName());
                    if(fieldClass.labelName().equals(""))
                        f.setLabelName(field.getName());
                    else
                        f.setLabelName(fieldClass.labelName());
                    this.fieldList.add(f);
                }
                catch(Exception exc){exc.printStackTrace();}
            }

        }
    }
    public void objectToForm(Class objectClass){
        for(Field field  : objectClass.getDeclaredFields())
        {
            if (field.isAnnotationPresent(SearchSwingField.class))
            {
                SearchSwingField fieldClass=field.getAnnotation(SearchSwingField.class);
                try{
                    FormField f=(FormField) fieldClass.componentType().getDeclaredConstructor().newInstance();
                    f.setRealName(field.getName());
                    if(fieldClass.labelName().equals(""))
                        f.setLabelName(field.getName());
                    else
                        f.setLabelName(fieldClass.labelName());
                    this.fieldList.add(f);
                }
                catch(Exception exc){exc.printStackTrace();}
            }
        }
    }
    @Override
    public T completeAndGetObject() {
        for(FormField field:this.fieldList){
                try{
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getRealName(),this.type);
                    if(!field.getInfoAsString().equals(""))
                        propertyDescriptor.getWriteMethod().invoke(generatedObject,field.getInfo());
                    else
                        propertyDescriptor.getWriteMethod().invoke(generatedObject,(Object) null);
                }catch (IntrospectionException | InvocationTargetException | IllegalAccessException e)
                { e.printStackTrace();
                    return null;}

        }
        return this.generatedObject;
    }
}
