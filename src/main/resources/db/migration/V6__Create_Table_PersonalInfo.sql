CREATE TABLE personal_info (
    id CHAR(36) PRIMARY KEY, -- ID único para cada registro
    full_name VARCHAR(255) NOT NULL,   -- Nome completo
    age INT CHECK (age >= 0),          -- Idade (em anos, não pode ser negativa)
    address VARCHAR(255),             -- Endereço
    eua_states VARCHAR(50),            -- Estado (assumindo ENUM no Java)
    city VARCHAR(255),                -- Cidade
    phone1 VARCHAR(20),               -- Telefone 1
    phone2 VARCHAR(20),               -- Telefone 2
    email VARCHAR(255),        -- E-Mail (único)
    user_id CHAR(36)                  -- Chave estrangeira para a tabela Users
);
