package com.amaro.bestsellers.domain.networking;

import com.amaro.bestsellers.domain.model.ProductResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.Subscription;
import rx.functions.Func1;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService service) {
        this.networkService = service;
    }

    public Subscription getProductList(final GetProductListCallback callback) {
        return networkService.getBestSellers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ProductResponse>>() {
                    @Override
                    public Observable<? extends ProductResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(ProductResponse response) {
                        callback.onSuccess(response);
                    }
                });
    }

    public interface GetProductListCallback{
        void onSuccess(ProductResponse response);
        void onError(NetworkError networkError);
    }
}
