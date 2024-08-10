package DesignPatterns.ObserverDesign.Observer;

import DesignPatterns.ObserverDesign.Observable.IphoneObservable;
import DesignPatterns.ObserverDesign.Observable.StocksObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver{

    StocksObservable observableObj;
    String phoneNo;

    public MobileAlertObserverImpl(StocksObservable observableObj, String phoneNo) {
        this.observableObj = observableObj;
        this.phoneNo = phoneNo;
    }

    @Override
    public void update() {
        sendMessage("Iphones stock count: "+observableObj.getStockCount());
    }

    public void sendMessage(String message) {
        System.out.println(message+" sent to: "+phoneNo);
    }
}
