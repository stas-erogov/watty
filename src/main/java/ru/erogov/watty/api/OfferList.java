package ru.erogov.watty.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferList<T> {
    public List<T> getOffers() {
        return offers;
    }

    private final List<T> offers = Collections.synchronizedList(new ArrayList<>());

    public void add(T t) {
        offers.add(t);
    }

    public void addOffer(OfferList<T> offerList) {
        offers.addAll(offerList.getOffers());
    }

    @Override
    public String toString() {
        return "OfferList{" +
                "offers=" + offers.toString() +
                '}';
    }
}
