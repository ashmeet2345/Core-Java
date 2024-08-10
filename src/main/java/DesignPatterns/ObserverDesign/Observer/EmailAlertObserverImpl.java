package DesignPatterns.ObserverDesign.Observer;

import DesignPatterns.ObserverDesign.Observable.IphoneObservable;
import DesignPatterns.ObserverDesign.Observable.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{

    StocksObservable observableObj;
    String emailId;

    public EmailAlertObserverImpl(StocksObservable observableObj, String emailId) {
        this.observableObj = observableObj;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        sendMessage("Iphones stock count: "+observableObj.getStockCount());
    }

    private void sendMessage(String message) {
        System.out.println(message+"\nsent to: "+emailId);
    }
}
