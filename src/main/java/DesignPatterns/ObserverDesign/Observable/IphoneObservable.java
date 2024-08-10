package DesignPatterns.ObserverDesign.Observable;

import DesignPatterns.ObserverDesign.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservable implements StocksObservable{

    public List<NotificationAlertObserver> observerList=new ArrayList<>();
    public int stockCount=0;

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscriber() {
        for(NotificationAlertObserver observer:observerList){
            observer.update();
        }
    }

    @Override
    public void setStockCount(int newStockAdded) {
        stockCount+=newStockAdded;
        notifySubscriber();
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
