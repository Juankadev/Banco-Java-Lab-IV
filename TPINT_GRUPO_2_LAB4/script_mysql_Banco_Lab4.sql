Create database Banco_Lab4;
use Banco_Lab4;

Create Table Paises(
	IDPais int not null primary key auto_increment,
    Descripcion varchar(100) not null
);

Create Table Localidades(
	CP varchar(10) not null primary key,
    IDPais int not null,
    Descripcion varchar(100) not null,
    FOREIGN KEY(IDPais) references Paises(IDPais)
);

Create Table Direcciones(
	IDDireccion bigint not null auto_increment,
    CP varchar(10) not null,
    Descripcion varchar(100) not null,
    Primary key(IDDireccion, CP),
    Foreign key(CP) references Localidades(CP)
);

Create Table Personas(
	DNI bigint not null,
    IDPais int not null,
    Sexo varchar(2) not null,
    IDDireccion bigint not null,
	CP varchar(10) not null,
    Cuil varchar(15) not null,
    Nombre varchar(50) not null,
    Apellido varchar(50) not null,
    FechaNac date not null,
    Correo varchar(50) not null unique,
    Estado tinyint not null default 1,
	Primary key(DNI),
	Foreign key(IDDireccion, CP) references Direcciones(IDDireccion, CP),
    Foreign key(IDPais) references Paises(IDPais)
);

Create Table Telefonos(
	DNI bigint not null,
    Telefono varchar(50) not null,
    Estado tinyint not null default 1,
	Foreign key(DNI) references Personas(DNI),
	Primary key(DNI)
);

Create Table Roles(
	IDRol int not null primary key auto_increment,
    Descripcion varchar(100) not null
);

Create Table Usuarios(
	DNI bigint not null,
    Usuario varchar(100) not null unique,
    Contrasena varchar(100) not null,
    IDRol int not null,
    Estado tinyint not null default 1,
	Primary key(DNI),
	Foreign key(DNI) references Personas(DNI),
    Foreign key(IDRol) references Roles(IDRol)
);

Create Table TipoCuentas(
	TipoCuenta int not null primary key auto_increment,
    Descripcion varchar(100) not null unique
);

Create Table Cuentas(
	NumeroCuenta bigint not null primary key,
	DNI bigint not null,
    CBU varchar(50) not null unique,
    TipoCuenta int not null,
    FechaCreacion datetime not null default NOW(),
    Saldo decimal not null default 10000,
    Estado tinyint not null default 1,
	Foreign key(DNI) references Usuarios(DNI),
    Foreign key(TipoCuenta) references TipoCuentas(TipoCuenta)
);

Create Table TipoMovimiento(
	IDTipoMovimiento int not null primary key auto_increment,
    Descripcion varchar(100) not null
);

Create Table Movimientos(
	IDMovimiento bigint not null primary key auto_increment,
    IDTipoMovimiento int not null,
    NumeroCuenta bigint not null,
    Fecha datetime not null default NOW(),
    Importe decimal not null ,
    Foreign key(IDTipoMovimiento) references TipoMovimiento(IDTipoMovimiento),
    Foreign key(NumeroCuenta) references Cuentas(NumeroCuenta)
);

Create Table tasanominalanual (
  tna DECIMAL(7,6) NOT NULL,
  PRIMARY KEY (tna));

Create Table Prestamos(
	IDPrestamo bigint not null primary key auto_increment,
    NumeroCuenta bigint not null,
    tna decimal(7,6) not null,
    Fecha datetime not null default NOW(),
    ImporteSolicitado decimal not null,
    PlazoPagoMeses int not null,
    CuotasRestantes int not null,
    Aprobado tinyint not null default 0,
    Estado tinyint not null default 1,
	Foreign key(NumeroCuenta) references Cuentas(NumeroCuenta)
);