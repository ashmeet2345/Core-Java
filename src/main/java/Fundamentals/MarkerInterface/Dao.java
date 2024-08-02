package Fundamentals.MarkerInterface;

public class Dao {

    public void delete(Object object){
        if(object instanceof MarkerInterface){
            //do some DB query
        }
    }
}
