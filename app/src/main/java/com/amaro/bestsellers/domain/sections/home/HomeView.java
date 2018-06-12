package com.amaro.bestsellers.domain.sections.home;

import com.amaro.bestsellers.domain.model.ProductResponse;

public interface HomeView {
    void showLoader();
    void hideLoader();

    void onFailure(String error);
    void onSuccess(ProductResponse response);
}
