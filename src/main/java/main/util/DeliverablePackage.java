package main.util;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * to be implemented, ah it's no more needed, i can simply use Queue.
 *
 */
public interface DeliverablePackage {

    Iterator<List<?>> getIterator();

    void attach(List<?> list);
}
