package com.amaro.bestsellers.domain.sections.details;

import com.amaro.bestsellers.domain.model.Product;

public interface DetailView {
    void populateData(Product item);
}
