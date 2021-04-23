
CREATE TABLE CLIENT(
    id_client integer NOT NULL,
    name varchar2(30) NOT NULL,
    cellphone varchar2(11) NOT NULL,
    PRIMARY KEY (id_client)
);

CREATE TABLE USERS(
    id_user integer NOT NULL,
    name varchar2(30) NOT NULL,
    username varchar2(30) NOT NULL,
    password varchar2(20) NOT NULL,
    PRIMARY KEY (id_user)
);


CREATE TABLE ORDERSHEET(
    id_ordersheet integer NOT NULL,
    date_create date NOT NULL,
    date_payment date,
    id_client integer NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (id_client) REFERENCES CLIENT(id_client),
    PRIMARY KEY (id_ordersheet)
);

CREATE TABLE PRODUCT(
    id_product integer NOT NULL, 
    description varchar2(40) NOT NULL,
    value number NOT NULL, 
    PRIMARY KEY (id_product)
);

CREATE TABLE ORDERSHEET_PRODUCT(
    id_ordersheet_product interger NOT NULL,
    id_ordersheet integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT fk_ordersheet FOREIGN KEY (id_ordersheet) REFERENCES ORDERSHEET(id_ordersheet),
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES PRODUCT(id_product), 
   
    PRIMARY KEY (id_ordersheet_product)
);
	