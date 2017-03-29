
import java.lang.reflect.Array;
import java.util.Arrays;
@SuppressWarnings("unchecked")
public class Set<T> implements SetInterface<T> {

    private T[] arr;//array to be used for social client
    private int size= 0;
    private int capacity = 100;
    
    public Set(int capacity){
      this.capacity = capacity;
      arr = (T[])(new Object [capacity]);    
    }//constructor with given capacity
    
    public Set(){
        arr = (T[])(new Object [capacity]);
    }// end default constructor of array size capacity [100] 100
    
    @Override
    public int getCurrentSize() {
        return size;
    }//returns size of the array

    @Override
    public boolean isEmpty() {
        /*
        for(T item:arr){
            if(item != null){
                return false;
            }
        }
        return true;
        */
        if (size == 0){
            return true;
        }
        else{
            return false;
        }//returns true if the array size is set to 0 
    }

    @Override
    public boolean add(T newEntry) throws SetFullException, IllegalArgumentException {
        if (newEntry==null){
            throw new IllegalArgumentException();
        }
        if(size == capacity){
            arr = Arrays.copyOf(arr, capacity*2);
            capacity *=2;//resizes array and changes capacity
        }
        if(size>=capacity){
            throw new SetFullException();
        }//if not room than throws setfull exception
        if(size < capacity && !contains(newEntry)){//if room, not duplicate, and not null
            arr[size] = newEntry;//adds new entry to spot size
            size++;//increases size by one2                                
            return true;
        }
        else{
            return false;//returns false if cant add new entry 
        }    
    }
    

    @Override
    public boolean remove(T entry) throws IllegalArgumentException {
        if(entry==null){//if entry is not valid
            throw new IllegalArgumentException();
        }
        for(int i =0; i<arr.length; i++){//tests if entry is in set
            if(arr[i].equals(entry)){
                arr[i]= null;//sets that spot to null
                for(int j =i; j<arr.length-1; j++){
                    arr[j] = arr[j+1];
                }//moves everything back one step
                arr[(arr.length)-1] = null;//sets last thing to null
                size--;
                return true;
            }
        }
        return false;//if not found than returns false
    }

    @Override
    public T remove() {
        if(size == 0){
            return null;
        }
        else{
            T raturn = arr[size-1];
            arr[size-1] = null;
            size--;
            return raturn;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        //runs through all entries and makes them null
        for(int i=0; i<arr.length; i++)
            arr[i]= null;
    }

    @Override
    public boolean contains(Object entry) throws IllegalArgumentException {
        boolean testEntry = false;
        if(entry ==null){//error if not valid entry
            throw new IllegalArgumentException();
        }
        else{
            for (int i = 0; i < size; i++)
            {
                if(arr[i].equals(entry))
                    testEntry = true;                    
            }
            return testEntry;        //if not found
    }
}

    @Override
    //returns new copy of arr with no null values
    public T[] toArray() {
        return Arrays.copyOf(arr, size);
    }
    
    
}
