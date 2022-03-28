package repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Work in progress
 */
public class PredicateFactory {
    /**
     * Builds a predicate list based on the fields sent through an object, using the like predicate
     * @param criteriaBuilder
     * @param values the values to be searched. null values are ignored
     * @param table the root table
     * @return
     */
    public static List<Predicate> fieldsToPredicates( CriteriaBuilder criteriaBuilder, Object values, Root table){
        List<Predicate> predicates=new ArrayList<>();
        for(Field f:values.getClass().getDeclaredFields()){
            Column[] columnProperties=f.getAnnotationsByType(Column.class);
            if(columnProperties.length>0)
            try {
                PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),values.getClass());
                Object value=propertyDescriptor.getReadMethod().invoke(values);
                if(value!=null)
                {
                    if(value instanceof String)
                        predicates.add(criteriaBuilder.like(table.get(f.getName()),"%"+value+"%"));
                    else{
                        if(value instanceof char[])
                        {
                            char[] arr=new char[((char[])value).length+2];
                            arr[0]='%';
                            System.arraycopy((char[])value, 0, arr, 1, ((char[])value).length);
                            arr[arr.length-1]='%';
                            predicates.add(criteriaBuilder.like(table.get(f.getName()), String.valueOf(arr)));
                        }
                        else{
                            if(!(value instanceof Number))
                                predicates.add(criteriaBuilder.equal(table.get(f.getName()),value));
                        }
                    }

                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return predicates;
    }

    /**
     * Also adds join columns entities as filters by getting their column filters
     * @param criteriaBuilder
     * @param values
     * @param table
     * @return
     */
    public static List<Predicate> fieldsToPredicatesAddJoins( CriteriaBuilder criteriaBuilder, Object values, Root table){
        List<Predicate> predicates=new ArrayList<>();
        for(Field f:values.getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(Column.class))
                try {
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),values.getClass());
                    Object value=propertyDescriptor.getReadMethod().invoke(values);
                    if(value!=null)
                    {
                            if(value instanceof String)
                                predicates.add(criteriaBuilder.like(table.get(f.getName()),"%"+value+"%"));
                            else{
                                if(value instanceof char[])
                                {
                                    char[] arr=new char[((char[])value).length+2];
                                    arr[0]='%';
                                    System.arraycopy((char[])value, 0, arr, 1, ((char[])value).length);
                                    arr[arr.length-1]='%';
                                    predicates.add(criteriaBuilder.like(table.get(f.getName()), String.valueOf(arr)));
                                }
                                else{
                                    if(!(value instanceof Number))
                                        predicates.add(criteriaBuilder.equal(table.get(f.getName()),value));
                                }
                            }

                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            else{
                if(f.isAnnotationPresent(OneToOne.class)||f.isAnnotationPresent(OneToMany.class)||
                        f.isAnnotationPresent(ManyToOne.class)||f.isAnnotationPresent(ManyToMany.class)){
                    Join join = table.join(f.getName());
                    PropertyDescriptor propertyDescriptor= null;
                    try {
                        propertyDescriptor = new PropertyDescriptor(f.getName(),values.getClass());
                        Object value=propertyDescriptor.getReadMethod().invoke(values);
                        predicates.addAll(PredicateFactory.fieldsToPredicates(criteriaBuilder,value,join));
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
        return predicates;
    }
    public static List<Predicate> fieldsToPredicates( CriteriaBuilder criteriaBuilder, Object values, Join table){
        List<Predicate> predicates=new ArrayList<>();
        for(Field f:values.getClass().getDeclaredFields()){
            Column[] columnProperties=f.getAnnotationsByType(Column.class);
            if(columnProperties.length>0)
                try {
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),values.getClass());
                    Object value=propertyDescriptor.getReadMethod().invoke(values);
                    if(value!=null&&(!(value instanceof Number))&&(!(value instanceof Boolean)))
                    {
                        if(value instanceof String)
                            predicates.add(criteriaBuilder.like(table.get(f.getName()),"%"+value+"%"));
                        else{
                            if(value instanceof char[])
                            {
                                char[] arr=new char[((char[])value).length+2];
                                arr[0]='%';
                                System.arraycopy((char[])value, 0, arr, 1, ((char[])value).length);
                                arr[arr.length-1]='%';
                                predicates.add(criteriaBuilder.like(table.get(f.getName()), String.valueOf(arr)));
                            }
                            else{
                                predicates.add(criteriaBuilder.equal(table.get(f.getName()),value));
                            }
                        }

                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return predicates;
    }
    public static List<Predicate> fieldsToPredicatesExact( CriteriaBuilder criteriaBuilder, Object values, Join table){
        List<Predicate> predicates=new ArrayList<>();
        for(Field f:values.getClass().getDeclaredFields()){
            Column[] columnProperties=f.getAnnotationsByType(Column.class);
            if(columnProperties.length>0)
                try {
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),values.getClass());
                    Object value=propertyDescriptor.getReadMethod().invoke(values);
                    if(value!=null)
                    {
                        predicates.add(criteriaBuilder.equal(table.get(f.getName()),value));

                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return predicates;
    }
}
