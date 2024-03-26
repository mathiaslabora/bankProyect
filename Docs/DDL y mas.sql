-- SELECT User, Host FROM mysql.user;

CREATE DATABASE IF NOT EXISTS mathiasbank
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;
use mathiasbank;
CREATE TABLE Usuario (
    documento INT PRIMARY KEY,
    correo VARCHAR(255),
    nombre_completo VARCHAR(255),
    direccion VARCHAR(255),
    tipo_usu VARCHAR(30),
    contrasena VARCHAR(20)
);

CREATE TABLE Cliente (
    documento_cli INT PRIMARY KEY,
    tipo_cli VARCHAR(30),
    activo BOOLEAN,
    FOREIGN KEY (documento_cli) REFERENCES Usuario(documento)
);

CREATE TABLE Empleado (
    documento_em INT PRIMARY KEY,
    rol VARCHAR(10),
    activo BOOLEAN,
    FOREIGN KEY (documento_em) REFERENCES Usuario(documento)
);

CREATE TABLE Cuenta (
    nro_cuenta INT PRIMARY KEY auto_increment,
    documento_cli INT,
    activo BOOLEAN,
    tipo VARCHAR(255),
    FOREIGN KEY (documento_cli) REFERENCES Cliente(documento_cli)
);

CREATE TABLE Tarjeta (
    id_tarjeta INT PRIMARY KEY auto_increment,
    nro_tarjeta VARCHAR(16),
    nro_cuenta INT,
    fecha_alta DATETIME,
    fecha_vencimiento DATETIME,
    tipo VARCHAR(30),
    limite DECIMAL(10, 2),
    disponible DECIMAL(10, 2),
    fecha_cierre DATETIME,
    FOREIGN KEY (nro_cuenta) REFERENCES Cuenta(nro_cuenta)
);

CREATE TABLE Tarea (
    fecha timestamp,
    documento_em INT,
    descripcion VARCHAR(255),
    FOREIGN KEY (documento_em) REFERENCES Empleado(documento_em)
);

CREATE TABLE Transaccion (
    nro_transaccion INT PRIMARY KEY auto_increment,
    nro_cuenta INT,
    documento_cli INT,
    id_tarjeta INT,
    fecha DATETIME,
    monto DECIMAL(10, 2),
    aviso VARCHAR(255),
    cuenta_destino INT,
    descripcion VARCHAR(255),
    moneda INT,
    tipo_transaccion VARCHAR(255),
    ordinal smallint default 1,
    FOREIGN KEY (nro_cuenta) REFERENCES Cuenta(nro_cuenta),
    FOREIGN KEY (documento_cli) REFERENCES Cliente(documento_cli),
    FOREIGN KEY (id_tarjeta) REFERENCES Tarjeta(id_tarjeta)
);

CREATE TABLE Saldo (
    id_saldo INT PRIMARY KEY auto_increment,
    nro_cuenta INT,
    monto_pesos DECIMAL(10, 2),
    monto_dolares DECIMAL(10, 2),
    limite_sobregiro DECIMAL(10, 2),
    FOREIGN KEY (nro_cuenta) REFERENCES Cuenta(nro_cuenta)
);

select * from usuario;

select * from Cliente;
insert into cliente values (51114096, "Premium",true);

select * from Cuenta;