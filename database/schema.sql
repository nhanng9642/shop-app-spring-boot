use bookstore;

create table user (
    id          serial                   not null,
    username    varchar(255)             not null unique,
    email       varchar(255)             not null unique,
    first_name  varchar(255)             not null,
    last_name   varchar(255)             not null,
    password    varchar(255)             not null,
    role        varchar(255)             not null,
    inserted_at timestamp default now() not null,
    updated_at  timestamp default now() not null,
    constraint pk_user primary key (id)
);

create table token (
    id          serial                  not null primary key,
    token       varchar(255)            not null,
    user_id     bigint unsigned         not null,
    revoked     boolean default false   not null,
    inserted_at timestamp default now() not null,
    updated_at  timestamp default now() not null,
    constraint foreign key fk_token_user (user_id) references user(id)
);


CREATE TABLE category (
    category_id     integer auto_increment PRIMARY KEY,
    category_name   VARCHAR(100),
    inserted_at timestamp default now() not null,
    updated_at  timestamp default now() not null,
    description     TEXT
);

CREATE TABLE book (
       book_id              SERIAL PRIMARY KEY,
       title                VARCHAR(255),
       category_id          INT,
       author               VARCHAR(100),
       publisher            VARCHAR(100),
       published_year       int,
       price                float,
       quantity_available   INT,
       description          TEXT,
       book_image           VARCHAR(255),
       inserted_at timestamp default now() not null,
       updated_at  timestamp default now() not null,
       FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE customer (
       customer_id          serial PRIMARY KEY,
       name                 NVARCHAR(100),
       email                VARCHAR(100),
       address              NVARCHAR(255),
       inserted_at timestamp default now() not null,
       updated_at  timestamp default now() not null,
       phone                VARCHAR(20)
);

CREATE TABLE sale_order (
        order_id            serial,
        customer_id         BIGINT UNSIGNED not null,
        order_date          DATE,
        total               float,
        shipping_address    VARCHAR(255),
        status              VARCHAR(50),
        inserted_at timestamp default now() not null,
        updated_at  timestamp default now() not null,
        primary key pk_sale_order (order_id),
        FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE order_detail (
          order_id          BIGINT UNSIGNED not null,
          book_id           BIGINT UNSIGNED not null,
          quantity          INT,
          price             float,
          total             float,
          inserted_at timestamp default now() not null,
          updated_at  timestamp default now() not null,
          PRIMARY KEY (order_id, book_id),
          FOREIGN KEY (order_id) REFERENCES sale_order(order_id),
          FOREIGN KEY (book_id) REFERENCES book(book_id)
);