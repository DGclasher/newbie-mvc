DROP TABLE IF EXISTS beer_order_line;
DROP TABLE IF EXISTS beer_order;

CREATE TABLE beer_order (
                            id                 VARCHAR(36) NOT NULL,
                            created_date       TIMESTAMP(6) DEFAULT NULL,
                            customer_ref       VARCHAR(255) DEFAULT NULL,
                            last_modified_date TIMESTAMP(6) DEFAULT NULL,
                            version            BIGINT DEFAULT NULL,
                            customer_id        VARCHAR(36) DEFAULT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE beer_order_line (
                                 id                 VARCHAR(36) NOT NULL,
                                 beer_id            VARCHAR(36) DEFAULT NULL,
                                 created_date       TIMESTAMP(6) DEFAULT NULL,
                                 last_modified_date TIMESTAMP(6) DEFAULT NULL,
                                 order_quantity     INT DEFAULT NULL,
                                 quantity_allocated INT DEFAULT NULL,
                                 version            BIGINT DEFAULT NULL,
                                 beer_order_id      VARCHAR(36) DEFAULT NULL,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (beer_order_id) REFERENCES beer_order(id),
                                 FOREIGN KEY (beer_id) REFERENCES beer(id)
);