CREATE TABLE IF NOT EXISTS permissao
(
    id        SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario
(
    id      SERIAL PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    senha   VARCHAR(255) NOT NULL,
    ativo   BOOLEAN      NOT NULL,
    tipo    VARCHAR(255) NOT NULL,
    UNIQUE (usuario)
);

CREATE TABLE IF NOT EXISTS usuario_permissao
(
    usuario_id   BIGINT REFERENCES usuario (id) ON DELETE CASCADE,
    permissao_id BIGINT REFERENCES permissao (id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, permissao_id)
);

CREATE TABLE IF NOT EXISTS categoria
(
    id        SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto
(
    id                 SERIAL PRIMARY KEY,
    nome               VARCHAR(255)                     NOT NULL,
    valor              NUMERIC(10, 2)                   NOT NULL,
    quantidade_estoque INTEGER                          NOT NULL,
    categoria_id       BIGINT REFERENCES categoria (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS endereco_cliente
(
    id               SERIAL PRIMARY KEY,
    logradouro       VARCHAR(255) NOT NULL,
    numero           VARCHAR(10),
    bairro           VARCHAR(255) NOT NULL,
    complemento      VARCHAR(255),
    ponto_referencia VARCHAR(255),
    cep              CHAR(8)      NOT NULL,
    id_municipio     BIGINT       NOT NULL,
    id_estado        BIGINT       NOT NULL,
    latitude         VARCHAR(20),
    longitude        VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS cliente
(
    id                  SERIAL PRIMARY KEY,
    nome                VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    telefone            VARCHAR(20),
    data_nascimento     TIMESTAMP,
    documento           VARCHAR(20),
    endereco_cliente_id BIGINT REFERENCES endereco_cliente (id)
);

CREATE TABLE IF NOT EXISTS venda
(
    id          SERIAL PRIMARY KEY,
    data        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total DECIMAL(10, 2) NOT NULL,
    cliente_id  INTEGER        REFERENCES cliente (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS item_venda
(
    id             SERIAL PRIMARY KEY,
    quantidade     INTEGER        NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    subtotal       DECIMAL(10, 2) NOT NULL,
    venda_id       INTEGER REFERENCES venda (id) ON DELETE CASCADE,
    produto_id     INTEGER        REFERENCES produto (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS movimentacao
(
    id         SERIAL PRIMARY KEY,
    produto_id BIGINT      NOT NULL,
    usuario_id BIGINT      NOT NULL,
    quantidade INTEGER     NOT NULL,
    data       TIMESTAMP   NOT NULL,
    tipo       VARCHAR(10) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);