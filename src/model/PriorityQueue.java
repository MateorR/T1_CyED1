package model;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<T>> implements Prioritable<T>{

    private ArrayList<T> elements;
    private int heapSize;
    public PriorityQueue() {
        elements = new ArrayList<T>();
        heapSize = 0;
    }

    @Override
    public T front() {
        if(!isEmpty())
            return elements.get(0);
        else
            return null;
    }

    @Override
    public T back() {
        if(!isEmpty())
            return elements.get(elements.size()-1);
        else
            return null;
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if (elements.isEmpty()) {
            result = true;
        }
        return result;
    }

    @Override
    public void insert(T elem) {
        elements.add(elem);
        heapSize = elements.size();
        if(!isEmpty()){
            for (int i = (heapSize/2)-1; i>=0; i--) {
                maxHeapify(i);
            }
        }
    }

    @Override
    public T extractMax() {
        int last = elements.size() - 1;
        if (!isEmpty()) {
            T max = elements.get(0);
            elements.set(0, elements.get(last));
            elements.remove(last);
            maxHeapify(0);
            return max;
        } else {
            return null;
        }
    }

    @Override
    public T extractMin() {
        int last = elements.size() - 1;
        if (!isEmpty()) {
            T min = elements.get(0);
            elements.set(0, elements.get(last));
            elements.remove(last);
            minHeapify(0);
            return min;
        } else {
            return null;
        }
    }

    @Override
    public void increaseKey(int i, T key) {
        if (key.compareTo(elements.get(i))>0) {
            elements.set(i, key);
            i++;
            while (i>0 && elements.get((i/2)-1).compareTo(elements.get(i-1))<0) {
                T temp = elements.get(i-1);
                elements.set(i-1, elements.get((i/2)-1));
                elements.set((i/2)-1, temp);
                i=(i/2)-1;
            }
        }
    }

    @Override
    public void decreaseKey(int i, T key) {
        if (key.compareTo(elements.get(i))<0) {
            elements.set(i, key);
            i++;
            while (i>0 && elements.get((i/2)-1).compareTo(elements.get(i-1))>0) {
                T temp = elements.get(i-1);
                elements.set(i-1, elements.get((i/2)-1));
                elements.set((i/2)-1, temp);
                i=(i/2)-1;
            }
        }

    }
    @Override
    public void maxHeapify(int i) {
        this.heapSize = elements.size()-1;
        i++;
        int l = (2*i)-1;
        int r = 2*i;
        i--;
        int largest=i;
        if (l<=heapSize && elements.get(l).compareTo(elements.get(i))>0) {
            largest=l;
        }
        if (r<=heapSize && elements.get(r).compareTo(elements.get(largest))>0) {
            largest=r;
        }
        if (largest!=i) {
            T temp = elements.get(i);
            elements.set(i, elements.get(largest));
            elements.set(largest, temp);
            maxHeapify(largest);
        }
    }

    @Override
    public void minHeapify(int i) {
        int heapSize = elements.size()-1;
        i++;
        int l = (2*i)-1;
        int r = 2*i;
        i--;
        int low=i;
        if (l<=heapSize && elements.get(l).compareTo(elements.get(i))<0) {
            low=l;
        }
        if (r<=heapSize && elements.get(r).compareTo(elements.get(low))<0) {
            low=r;
        }
        if (low!=i) {
            T temp = elements.get(i);
            elements.set(i, elements.get(low));
            elements.set(low, temp);
            maxHeapify(low);
        }
    }
}

