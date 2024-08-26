import java.lang.reflect.Array;

public class DynamicArray<T> {
    private Class<T> type;
    private T[] arrayList;
    private String errorMsg = "Array Index out of Bounds!";

    public DynamicArray(Class<T> type){
        this.type = type;
        this.arrayList = (T[]) Array.newInstance(type, 0);
    }

    public int size(){
        return this.arrayList.length;
    }
    public boolean isEmpty(){
        return this.arrayList.length==0;
    }
    public T get(int idx){
        if (idx<0 || idx>=this.arrayList.length) {
            return null;
        }
        return this.arrayList[idx];

    }

    public boolean contains(T element){
        boolean containsElement = false;
        for (T object: this.arrayList){
            if (object.equals(element)){
                containsElement = true;
                break;
            }
        }
        return containsElement;
    }

    public void add(T element){
        T[] newArrayList = (T[]) Array.newInstance(this.type, this.arrayList.length+1);
        for (int i=0; i<this.arrayList.length; i++){
            newArrayList[i] = this.arrayList[i];
        }
        newArrayList[newArrayList.length-1] = element;
        this.arrayList = newArrayList;
    }

    public void add(int idx, T element){
        if (idx> this.arrayList.length || idx <0){
            throw new IllegalArgumentException(errorMsg);
        }

        T[] newArrayList = (T[]) Array.newInstance(this.type, this.arrayList.length+1);
        for (int i=0; i<this.arrayList.length+1; i++){
            if (i<idx){
                newArrayList[i] = this.arrayList[i];
            }
            else if (i>idx){
                newArrayList[i] = this.arrayList[i-1];
            }
            else{
                newArrayList[i] = element;
            }
        }
        this.arrayList = newArrayList;

    }

    public void set(int idx, T element){
        if (idx<0 || idx>=this.arrayList.length){
            throw new IllegalArgumentException(errorMsg);
        }
        this.arrayList[idx] = element;
    }

    public T remove(int idx){
        if (idx<0 || idx>=this.arrayList.length){
            throw new IllegalArgumentException(errorMsg);
        }

        T popped = this.arrayList[idx];
        T[] newArrayList = (T[]) Array.newInstance(this.type, this.arrayList.length-1);
        for (int i=0; i<this.arrayList.length-1; i++){
            if (i<idx){
                newArrayList[i] = this.arrayList[i];
            }
            if (i>idx){
                newArrayList[i] = this.arrayList[i+1];
            }
        }
        this.arrayList = newArrayList;
        return popped;
    }

    public T remove(T element){
        T popped = null;
        T[] newArrayList = (T[]) Array.newInstance(this.type, this.arrayList.length-1);
        int firstIndex = -1;

        for (int i=0; i<this.arrayList.length; i++) {
            if (this.arrayList[i].equals(element)) {
                firstIndex = i;
                popped = this.arrayList[i];
                break;
            }
        }

        for (int i=0; i<this.arrayList.length; i++){
            if (i<firstIndex){
                newArrayList[i] = this.arrayList[i];
            }
            if (i>firstIndex){
                newArrayList[i-1] = this.arrayList[i];
            }
        }

        this.arrayList = newArrayList;
        return popped;

    }

    public T removeAll(T element){
        T popped = null;
        int count = 0;
        for (T obj: this.arrayList){
            if (obj.equals(element)){
                count++;
                popped = element;
            }
        }
        T[] newArrayList = (T[]) Array.newInstance(this.type, this.arrayList.length-count);

        int counter = 0;
        for (int i=0; i<arrayList.length; i++){
            if (arrayList[i].equals(element)){
                counter++;
            }
            if (!arrayList[i].equals(element)) newArrayList[i-counter] = arrayList[i];
        }

        this.arrayList = newArrayList;

        return popped;
    }

    public void clear(){
        this.arrayList = (T[]) Array.newInstance(this.type, 0);
    }



















}
