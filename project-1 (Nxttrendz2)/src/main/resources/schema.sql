CREATE TABLE IF NOT EXISTS PRODUCT(
    productId INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(250),
    price DOUBLE
);

CREATE TABLE IF NOT EXISTS REVIEW(
    reviewId INT PRIMARY KEY AUTO_INCREMENT,
    reviewContent VARCHAR(250),
    rating INT,
    productId INT,
    FOREIGN KEY(productId) REFERENCES PRODUCT(productId) ON DELETE CASCADE
);

INSERT INTO PRODUCT(productName, price) VALUES
('Smartphone', 599.99),
('Laptop', 1299.99),
('Gaming Console', 399.99);

INSERT INTO REVIEW(reviewContent, rating, productId) VALUES
('Great battery life!', 5, 1),
('Lags sometimes.', 3, 1),
('Perfect for my daily tasks!', 4, 2),
('Bit pricey, but worth it.', 4, 2),
('Awesome gaming experience!', 5, 3),
('Needs more exclusive games.', 4, 3);