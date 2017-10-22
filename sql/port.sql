CREATE TABLE port(
    id INT PRIMARY KEY AUTO_INCREMENT,
    agent_id INT,
    user_id INT UNIQUE,
    used_flow_bytes BIGINT DEFAULT 0,
    total_flow_bytes BIGINT,
    port_no INT,
    password VARCHAR(50),
);