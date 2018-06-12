package com.amaro.bestsellers.domain.sections.home;

import com.amaro.bestsellers.domain.model.ProductResponse;
import com.amaro.bestsellers.domain.networking.NetworkError;
import com.amaro.bestsellers.domain.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class Presenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public Presenter(Service s, HomeView v) {
        this.service = s;
        this.view = v;

        this.subscriptions = new CompositeSubscription();
    }

    public void getProductList() {
        view.showLoader();

        Subscription subscription = service.getProductList(new Service.GetProductListCallback() {

            @Override
            public void onSuccess(ProductResponse response) {
                view.hideLoader();
                view.onSuccess(response);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.hideLoader();
                view.onFailure(networkError.getErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}
