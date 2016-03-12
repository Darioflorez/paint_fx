package observer;

import java.util.List;

/**
 * Created by dario on 2016-03-12.
 */
public class Subject {

    private List<Observer> observers;

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer obs: observers){
            obs.update();
        }
    }
}
