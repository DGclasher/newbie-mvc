drop table if exists beer_order_shipment;

CREATE TABLE beer_order_shipment (
    id VARCHAR(36) NOT NULL PRIMARY KEY ,
    tracking_number VARCHAR(50),
    created_date TIMESTAMP(6),
    last_modified_date TIMESTAMP(6) DEFAULT NULL,
    version BIGINT DEFAULT NULL,
    beer_order_id VARCHAR(36) UNIQUE ,
    FOREIGN KEY (beer_order_id) REFERENCES beer_order(id)
);

ALTER TABLE beer_order
 ADD COLUMN beer_order_shipment_id VARCHAR(36) DEFAULT NULL;

ALTER TABLE beer_order
    ADD FOREIGN KEY (beer_order_shipment_id) REFERENCES beer_order_shipment(id);