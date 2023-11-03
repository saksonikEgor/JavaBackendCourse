package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Task8 {
    private Task8() {
    }

    public static class BackwardIterator<E> implements Iterator<E> {
        private final E[] data;
        private int currentIndex;

        public BackwardIterator(Collection<E> collection) {
            this.data = (E[]) collection.toArray();
            currentIndex = data.length - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[currentIndex--];
        }
    }
}
