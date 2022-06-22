CREATE TABLE admin
(
    id            bigserial    NOT NULL,
    active        bool         NULL,
    cellphone     varchar(255) NULL,
    document_no   varchar(255) NULL,
    document_type int4         NULL,
    email         varchar(255) NULL,
    last_name     varchar(255) NULL,
    name          varchar(255) NULL,
    CONSTRAINT admin_pkey PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id        bigserial    NOT NULL,
    cellphone varchar(255) NULL,
    email     varchar(255) NULL,
    last_name varchar(255) NULL,
    name      varchar(255) NULL,
    password  varchar(255) NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          bigserial    NOT NULL,
    active      bool         NULL,
    color       varchar(255) NULL,
    description varchar(255) NULL,
    price       float8       NULL,
    size        varchar(255) NULL,
    stock       int4         NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE purchase_order
(
    id         bigserial NOT NULL,
    created_at timestamp NULL,
    admin_id   int8      NULL,
    CONSTRAINT purchase_order_pkey PRIMARY KEY (id),
    CONSTRAINT fkkldumqs1rf8hejnlqhrb394ou FOREIGN KEY (admin_id) REFERENCES admin (id)
);

CREATE TABLE purchase_order_item
(
    id                bigserial NOT NULL,
    current_stock     int4      NULL,
    status            int4      NULL,
    product_id        int8      NULL,
    purchase_order_id int8      NULL,
    CONSTRAINT purchase_order_item_pkey PRIMARY KEY (id),
    CONSTRAINT fk593lt017d995ds7nuqxgo3mmm FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fkmj122necubadvuquvjoq967y7 FOREIGN KEY (purchase_order_id) REFERENCES purchase_order (id)
);

CREATE TABLE sale
(
    id               bigserial    NOT NULL,
    created_at       timestamp    NULL,
    delivery_address varchar(255) NULL,
    status           int4         NULL,
    customer_id      int8         NULL,
    CONSTRAINT sale_pkey PRIMARY KEY (id),
    CONSTRAINT fkjw88ojfoqquyd9f1obip1ar0g FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE sale_detail
(
    id         bigserial NOT NULL,
    quantity   int4      NULL,
    unit_price float8    NULL,
    product_id int8      NULL,
    sale_id    int8      NULL,
    CONSTRAINT sale_detail_pkey PRIMARY KEY (id),
    CONSTRAINT fk2k26c5k0qkdm1srkiwn7q5009 FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fkgnpg9v1mvi1nyhc18vdcyuh98 FOREIGN KEY (sale_id) REFERENCES sale (id)
);
