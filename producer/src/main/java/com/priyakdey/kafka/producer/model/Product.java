package com.priyakdey.kafka.producer.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Priyak Dey
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 6925656614885054380L;

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
