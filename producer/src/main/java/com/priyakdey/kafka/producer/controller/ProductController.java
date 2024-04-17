package com.priyakdey.kafka.producer.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.priyakdey.kafka.producer.model.Product;
import com.priyakdey.kafka.producer.service.ProductService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/v1/products", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ProductController {

    private static final String PRODUCT_URI_TMPL = "/v1/products/%d";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody Product product) {
        int productId = productService.createNewProduct(product);

        return ResponseEntity
            .created(URI.create(String.format(PRODUCT_URI_TMPL, productId)))
            .body(productId);
    }

}
