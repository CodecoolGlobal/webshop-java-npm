ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category
    DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cart
    DROP CONSTRAINT IF EXISTS pk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;



DROP TABLE IF EXISTS supplier;

DROP SEQUENCE IF EXISTS public.suplier_id_seq;
CREATE TABLE supplier
(
    id          SERIAL NOT NULL,
    name        VARCHAR(50),
    description VARCHAR(150)
);
DROP TABLE IF EXISTS product_category;

DROP SEQUENCE IF EXISTS public.product_category_id_seq;
CREATE TABLE product_category
(
    id          SERIAL NOT NULL,
    name        VARCHAR(50),
    department  VARCHAR(50),
    description VARCHAR(300)
);
DROP TABLE IF EXISTS cart;

DROP SEQUENCE IF EXISTS public.cart_id_seq;
CREATE TABLE cart
(
    id        SERIAL NOT NULL,
    quantity  INTEGER,
    sum_price FLOAT,
    product_id INTEGER
);

DROP TABLE IF EXISTS "order";

DROP SEQUENCE IF EXISTS public.order_id_seq;
CREATE TABLE "order"
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(50),
    email            VARCHAR(50),
    phone_number     INTEGER,
    billing_address  VARCHAR(150),
    shipping_address VARCHAR(150)
);

DROP TABLE IF EXISTS product;
DROP SEQUENCE IF EXISTS public.product_id_seq CASCADE;

CREATE TABLE product
(
    id          SERIAL NOT NULL,
    name        text,
    price       float,
    currency    VARCHAR(5),
    description varchar(500),
    category_id INTEGER,
    supplier_id INTEGER,
    cart_id     INTEGER
);


ALTER TABLE ONLY cart
    ADD CONSTRAINT pk_cart_id PRIMARY KEY (id);


ALTER TABLE ONLY product_category
    ADD CONSTRAINT pk_product_category_id PRIMARY KEY (id);


ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);


ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES product_category (id);

ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);


INSERT INTO product_category
VALUES (1, 'Home Consoles', 'Hardware',
        'A video game device that is primarily used for home gamers, as opposed to in arcades or some other commercial establishment');
INSERT INTO product_category
VALUES (2, 'Handheld Consoles', 'Hardware',
        'They are smaller and portable, allowing people to carry them and play them at any time or place, along with microconsoles and dedicated consoles.');
INSERT INTO product_category
VALUES (3,'Hybrid Consoles', 'Hardware', 'Can be used as both a stationary and portable device.');

INSERT INTO supplier
VALUES (1,'Sony', 'Consumer and professional electronics, gaming, entertainment and financial services');
INSERT INTO supplier
VALUES (2,'Nintendo', 'Consumer electronics and video game company');
INSERT INTO supplier
VALUES (3,'Microsoft',
        'It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services');


INSERT INTO product VALUES (1,'PlayStation 4 Pro', 399.99, 'USD', 'The technology in the PlayStation 4 is similar to the hardware found in modern personal computers. This familiarity is designed to make it easier and less expensive for game studios to develop games for the PS4.',1,1);
INSERT INTO product VALUES (2, 'PS Vita', 249, 'USD', 'The PlayStation Vita (officially abbreviated PS Vita or Vita) is a handheld video game console developed and released by Sony Computer Entertainment. It is the successor to the PlayStation Portable as part of the PlayStation brand of gaming devices.', 2, 1);
INSERT INTO product VALUES (3, 'Switch', 299.99, 'USD', 'The Nintendo Switch is a hybrid video game console, consisting of a console unit, a dock, and two Joy-Con controllers.', 3, 2);
INSERT INTO product VALUES (4, 'Xbox One', 499, 'USD', 'The Xbox One is an eighth-generation home video game console that was developed by Microsoft.', 1, 3);
INSERT INTO product VALUES (5, 'New Nintendo 3DS', 150, 'USD', 'The New Nintendo 3DS is a handheld game console developed by Nintendo. It is the fourth system in the Nintendo 3DS family of handheld consoles, following the original Nintendo 3DS, the Nintendo 3DS XL, and the Nintendo 2DS.', 2, 2);