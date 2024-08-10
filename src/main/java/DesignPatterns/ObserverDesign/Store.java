package DesignPatterns.ObserverDesign;

import DesignPatterns.ObserverDesign.Observable.IphoneObservable;
import DesignPatterns.ObserverDesign.Observer.EmailAlertObserverImpl;
import DesignPatterns.ObserverDesign.Observer.MobileAlertObserverImpl;
import DesignPatterns.ObserverDesign.Observer.NotificationAlertObserver;

public class Store {

    public static void main(String[] args) {
        IphoneObservable observable=new IphoneObservable();
        NotificationAlertObserver observer1=new MobileAlertObserverImpl(observable,"987654321");
        NotificationAlertObserver observer2=new EmailAlertObserverImpl(observable,"abc@xyz.com");
        observable.add(observer1);
        observable.add(observer2);

        observable.setStockCount(30);


    }
}
