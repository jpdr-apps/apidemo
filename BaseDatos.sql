-- Table: clientes
DROP TABLE IF EXISTS clientes;

CREATE TABLE IF NOT EXISTS clientes (
    cliente_id     INTEGER,
    direccion      VARCHAR (255) NOT NULL,
    edad           INTEGER       NOT NULL,
    genero         VARCHAR (255) NOT NULL,
    identificacion VARCHAR (255) NOT NULL,
    nombre         VARCHAR (255) NOT NULL,
    telefono       VARCHAR (255),
    contraseña     VARCHAR (255) NOT NULL,
    estado         BOOLEAN       NOT NULL,
    PRIMARY KEY (
        cliente_id
    )
);


-- Table: cuentas
DROP TABLE IF EXISTS cuentas;

CREATE TABLE IF NOT EXISTS cuentas (
    cuenta_id     INTEGER,
    estado        BOOLEAN       NOT NULL,
    numero_cuenta INTEGER       NOT NULL,
    saldo_inicial REAL (16, 2)  NOT NULL,
    tipo_cuenta   VARCHAR (255) NOT NULL,
    cliente_id    INTEGER       NOT NULL,
    PRIMARY KEY (
        cuenta_id
    )
);


-- Table: movimientos
DROP TABLE IF EXISTS movimientos;

CREATE TABLE IF NOT EXISTS movimientos (
    movimiento_id    INTEGER,
    fecha            VARCHAR (255) NOT NULL,
    saldo_disponible REAL (16, 2)  NOT NULL,
    saldo_inicial    REAL (16, 2)  NOT NULL,
    tipo_movimiento  VARCHAR (255) NOT NULL,
    valor            REAL (16, 2)  NOT NULL,
    cuenta_id        INTEGER       NOT NULL,
    PRIMARY KEY (
        movimiento_id
    )
);


-- Table: parametros
DROP TABLE IF EXISTS parametros;

CREATE TABLE IF NOT EXISTS parametros (
    clave VARCHAR (255) NOT NULL,
    valor VARCHAR (255) NOT NULL,
    PRIMARY KEY (
        clave
    )
);
