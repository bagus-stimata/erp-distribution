package org.erp.distribution.modeltest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TestFactory {
    public static Collection generateCollection(){
        List<PersonBean> collection = new LinkedList<PersonBean>();
        collection.add(new PersonBean("Bagus", 21));
        collection.add(new PersonBean("Arin", 20));
        collection.add(new PersonBean("Queen", 5));
        collection.add(new PersonBean("Caesar", 4));
        
        return collection;
    }
    public static PersonBean[] generateBeanArray(){
        PersonBean[] list = new PersonBean[3];
        list[0]= (new PersonBean("Bagus", 0));
        list[1]= (new PersonBean("Arin", 20));
        list[2]= (new PersonBean("Caesar", 4));
        return list;
    }
    

}
