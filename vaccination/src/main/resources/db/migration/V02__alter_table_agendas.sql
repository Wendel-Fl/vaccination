ALTER TABLE
    agendas
DROP COLUMN
    data,
DROP COLUMN
    hora;

ALTER TABLE
    agendas
ADD COLUMN
    data_hora TIMESTAMP;