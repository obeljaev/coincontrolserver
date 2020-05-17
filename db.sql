CREATE TABLE users(
        user_id SERIAL PRIMARY KEY,
        user_name VARCHAR,
        user_password VARCHAR
);

CREATE TABLE categories(
        category_id SERIAL PRIMARY KEY,
        user_id SERIAL,
        category_name VARCHAR,
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE sub_categories(
        sub_category_id SERIAL PRIMARY KEY,
        category_id SERIAL,
        sub_category_name VARCHAR,
        FOREIGN KEY (category_id) REFERENCES categories(category_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE wallets(
        id SERIAL PRIMARY KEY,
        user_id SERIAL,
        name VARCHAR,
        money INT,
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE operations(
        id SERIAL PRIMARY KEY,
        user_id SERIAL,
        sub_category_id SERIAL,
        creation_time TIMESTAMP,
        operation_time TIMESTAMP,
        type INT,
        from_wallet_id SERIAL,
        to_wallet_id SERIAL,
        money INT,
        is_processed bool,
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (sub_category_id) REFERENCES sub_categories(sub_category_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (from_wallet_id) REFERENCES wallets(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        FOREIGN KEY (to_wallet_id) REFERENCES wallets(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
