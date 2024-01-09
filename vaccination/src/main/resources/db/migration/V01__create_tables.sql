CREATE TABLE Vacinas (
    id SERIAL,
    titulo VARCHAR(60),
    descricao VARCHAR(200),
    doses INTEGER,
    periodicidade INTEGER,
    intervalo INTEGER,

    PRIMARY KEY (id)
);

CREATE TABLE Usuarios(
     id SERIAL,
     nome VARCHAR(60),
     data_nascimento DATE,
     sexo CHAR(1),
     logradouro VARCHAR(60),
     numero NUMERIC,
     setor VARCHAR(40),
     cidade VARCHAR(40),
     uf CHAR(2),

     PRIMARY KEY (id)
);

CREATE TABLE Agendas(
    id SERIAL,
    data DATE,
    hora TIME,
    situacao CHAR(10),
    data_situacao DATE,
    observacoes VARCHAR(200),
    vacina_id SERIAL,
    usuario_id SERIAL,

    PRIMARY KEY (id),
    FOREIGN KEY (vacina_id) REFERENCES Vacinas(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

CREATE TABLE Alergias(
    id SERIAL,
    nome VARCHAR(40),

    PRIMARY KEY (id)
);

CREATE TABLE Usuarios_Alergias(
    id SERIAL,
    usuario_id SERIAL,
    alergia_id SERIAL,

    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (alergia_id) REFERENCES Alergias(id)
);