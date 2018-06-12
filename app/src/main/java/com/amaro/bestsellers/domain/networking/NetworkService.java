package com.amaro.bestsellers.domain.networking;

import com.amaro.bestsellers.domain.model.ProductResponse;

import rx.Observable;
import retrofit2.http.GET;

public interface NetworkService {
    @GET("59b6a65a0f0000e90471257d")
    Observable<ProductResponse> getBestSellers();
}
