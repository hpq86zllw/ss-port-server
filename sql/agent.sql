CREATE TABLE agent(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ss_host VARCHAR(50) UNIQUE,
    ss_encrypt_method VARCHAR(50),
    base_url VARCHAR(100),
    token VARCHAR(50) UNIQUE
);