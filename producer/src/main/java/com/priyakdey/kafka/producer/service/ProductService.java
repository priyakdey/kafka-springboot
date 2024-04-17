package com.priyakdey.kafka.producer.service;

import com.priyakdey.kafka.producer.model.Product;

/**
 * @author Priyak Dey
 */
public interface ProductService {

    Integer createNewProduct(Product product);

}
