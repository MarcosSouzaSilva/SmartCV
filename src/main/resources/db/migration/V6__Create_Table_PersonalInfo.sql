CREATE TABLE personal_info (
    id INT AUTO_INCREMENT PRIMARY KEY, -- ID único para cada registro
    full_name VARCHAR(255) NOT NULL,   -- Nome completo
    gender VARCHAR(50),               -- Sexo (assumindo ENUM no Java)
    age INT CHECK (age >= 0),          -- Idade (em anos, não pode ser negativa)
    marital_status VARCHAR(50),       -- Estado Civil (assumindo ENUM no Java)
    has_children BOOLEAN NOT NULL,    -- Tem Filho(s)?
    address VARCHAR(255),             -- Endereço
    state VARCHAR(50),                -- Estado (assumindo ENUM no Java)
    city VARCHAR(255),                -- Cidade
    phone1 VARCHAR(20),               -- Telefone 1
    phone2 VARCHAR(20),               -- Telefone 2
    email VARCHAR(255) UNIQUE,        -- E-Mail (único)
    user_id CHAR(36)                  -- Chave estrangeira para a tabela Users
);
