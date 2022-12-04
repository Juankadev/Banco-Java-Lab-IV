use banco_lab4;
#15 clientes

#15 cuentas
#15 prestamos
#15 movimientos

insert into paises (Descripcion) values ("Argentina");
insert into paises (Descripcion) values ("Chile");
insert into paises (Descripcion) values ("Brasil");

insert into localidades (CP,IDPais,Descripcion) values (1619,1,"Garin");
insert into localidades (CP,IDPais,Descripcion) values (6200000,2,"Punta Arenas");
insert into localidades (CP,IDPais,Descripcion) values (70856,3,"Brasilia");
insert into localidades (CP,IDPais,Descripcion) values (1625,1,"Escobar");



insert into direcciones (CP,Descripcion) values (1619,"Libertad 55");
insert into direcciones (CP,Descripcion) values (6200000,"Francisco Sampaio 678");
insert into direcciones (CP,Descripcion) values (70856,"Asa Norte 408");
insert into direcciones (CP,Descripcion) values (1619,"Arias Norte 1522");
insert into direcciones (CP,Descripcion) values (70856,"Concordia 97");
insert into direcciones (CP,Descripcion) values (70856,"Rafael Lima 122");
insert into direcciones (CP,Descripcion) values (1619,"Ayacucho 260");
insert into direcciones (CP,Descripcion) values (1619,"Pueyrredon 2980");
insert into direcciones (CP,Descripcion) values (1619,"Av. Falcon 884");
insert into direcciones (CP,Descripcion) values (6200000,"Cabrales 1224");
insert into direcciones (CP,Descripcion) values (6200000,"Maipu 502");
insert into direcciones (CP,Descripcion) values (6200000,"Chiloe 698");
insert into direcciones (CP,Descripcion) values (1625,"Dr Travi 2855");
insert into direcciones (CP,Descripcion) values (1625,"Tapia de Cruz 1277");
insert into direcciones (CP,Descripcion) values (1625,"Peirano 669");

insert into roles (Descripcion) values ("Banco");
insert into roles (Descripcion) values ("Cliente");

insert into tipocuentas (Descripcion) values ("Caja de ahorro");
insert into tipocuentas (Descripcion) values ("Cuenta corriente");

insert into tipomovimiento (Descripcion) values ("Alta de cuenta");
insert into tipomovimiento (Descripcion) values ("Alta de prestamo");
insert into tipomovimiento (Descripcion) values ("Pago de prestamo");
insert into tipomovimiento (Descripcion) values ("Transferencia");

insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (15855428,1,"m",1,1619,11158554285,"Gustavo","Moreno","1950-04-14","gustavo@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (25841244,1,"m",1,1619,14258412414,"Anibal","Rodriguez","1986-09-06","anibal@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (45854114,1,"m",1,1619,20458541145,"Marcelo","Monterrey","1981-01-12","marcelo@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (27845123,1,"m",8,1619,15278451231,"Mario","Riquelme","1975-10-09","mario@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (32152654,1,"f",14,1625,73215265422,"Mirtha","Perez","1979-07-12","mirtha@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (15845622,1,"f",15,1625,12158456225,"Lorena","Martinez","1956-04-22","lorena@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (40154222,1,"m",15,1625,20401542221,"Geronimo","Gonzalez","1998-10-05","gero@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (35205488,2,"f",2,6200000,7352054889,"Florencia","Torres","1995-02-11","flor@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (208755481,2,"f",11,6200000,208755481-8,"Luciana","Gutierrez","1989-06-20","luci@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (209566556,2,"f",11,6200000,209566556-3,"Martina","Gutierrez","1992-04-15","martina@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (25521258,2,"f",12,6200000,25521258-9,"Antonela","Salvador","2002-06-28","anto@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (19251233,2,"m",12,6200000,15192512338,"Laura","Villareal","1962-11-11","laura@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (22188177,3,"m",5,70856,15221881773,"Rafael","Silva","1984-10-05","rafa@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (55148117,3,"m",6,70856,5514811778,"Alex","Olmos","1947-05-30","alex@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (85175845,3,"f",6,70856,8517584544,"Sandra","Wilson","1963-08-27","sandra@gmail.com",1);
insert into personas (DNI,IDPais,Sexo,IDDireccion,CP,Cuil,Nombre,Apellido,FechaNac,Correo,Estado) values (82175789,3,"f",6,70856,8217578982,"Martin","Wilson","1960-04-16","martin@gmail.com",1);


insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (25841244,"admin",123,1,1); #ADMIN
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (15855428,"gustavo",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (45854114,"marcelo",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (27845123,"mario",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (32152654,"mirtha",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (15845622,"lorena",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (40154222,"geronimo",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (208755481,"luciana",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (209566556,"martina",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (25521258,"antonela",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (19251233,"laura",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (22188177,"rafael",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (55148117,"alex",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (85175845,"sandra",123,2,1);
insert into usuarios (DNI,Usuario,Contrasena,IDROL,Estado) values (82175789,"martin",123,2,1);

insert into telefonos (DNI,Telefono,Estado) values (25841244,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (15855428,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (45854114,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (27845123,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (32152654,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (15845622,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (40154222,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (208755481,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (209566556,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (25521258,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (19251233,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (22188177,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (55148117,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (85175845,1122841556,1);
insert into telefonos (DNI,Telefono,Estado) values (82175789,1122841556,1);

#cuentas
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000155,25841244,1,50590940090418135201,"2022-01-12",12550.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000156,15855428,1,50590940090418854221,"2021-05-18",7521.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000157,45854114,1,50590940090411254247,"2019-07-22",70418.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000158,27845123,1,50590940090125486324,"2022-02-17",51000.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000159,32152654,1,50590940090417851245,"2018-11-10",4905.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000160,15845622,1,50590940090411124557,"2015-06-16",22945.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000161,40154222,1,50590940090419987544,"2022-10-10",17878.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000162,82175789,1,50590940090411245322,"2022-04-12",42150.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado)  
values (0000000000163,208755481,1,5059094009120300214,"2022-05-22",2509.08,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000164,209566556,1,50590940090418125455,"2018-03-27",51000,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000165,25521258,2,50590940090418124556,"2017-11-22",8542.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000166,19251233,2,50590940090418112451,"2016-05-06",14000.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000167,22188177,2,50590940090414778459,"2017-01-04",19500.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000168,55148117,2,50590940090411410001,"2019-02-02",3215.00,1);
insert into cuentas (NumeroCuenta,DNI,TipoCuenta,CBU,FechaCreacion,Saldo,Estado) 
values (0000000000170,85175845,2,50590940090418104509,"2020-10-29",120800.00,1);

############## prestamos
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000156,0.9168,"2022-10-05",500000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",50000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",10000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",100000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000160,0.9168,"2022-10-05",300000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000160,0.9168,"2022-10-05",100000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000162,0.9168,"2022-10-05",275000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000162,0.9168,"2022-10-05",150000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000165,0.9168,"2022-10-05",1000000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000165,0.9168,"2022-10-05",1000000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000170,0.9168,"2022-10-05",50000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado)
values (0000000000170,0.9168,"2022-10-05",250000,12,12,1);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000170,0.9168,"2022-10-05",40000,12,12,1);

insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000156,0.9168,"2022-10-05",500000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",50000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",10000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000157,0.9168,"2022-10-05",100000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000160,0.9168,"2022-10-05",300000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000160,0.9168,"2022-10-05",100000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000162,0.9168,"2022-10-05",275000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000162,0.9168,"2022-10-05",150000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000165,0.9168,"2022-10-05",1000000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000165,0.9168,"2022-10-05",1000000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000170,0.9168,"2022-10-05",50000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado)
values (0000000000170,0.9168,"2022-10-05",250000,12,12,0);
insert into prestamos (NumeroCuenta,tna,Fecha,ImporteSolicitado,PlazoPagoMeses,CuotasRestantes,Aprobado) 
values (0000000000170,0.9168,"2022-10-05",40000,12,12,0);

################################ movimientosinsert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (1,0000000000155,"2022-10-05 07:22:29",0);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (2,0000000000156,"2021-08-20 15:32:10",85000);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (1,0000000000157,"2022-10-10 23:55:41",0);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (1,0000000000157,"2020-05-15 17:11:15",0);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (1,0000000000157,"2020-05-15 18:11:14",0);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (2,0000000000160,"2022-05-15 14:02:56",20000);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (2,0000000000160,"2020-10-08 18:12:11",92000);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (4,0000000000162,"2022-11-12 11:05:05",77000);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (4,0000000000162,"2022-11-12 04:22:11",12900);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (4,0000000000165,"2022-11-12 19:19:19",15850);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (4,0000000000165,"2022-11-12 12:11:15",150200);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (3,0000000000170,"2022-11-12 15:43:00",45120);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (2,0000000000170,"2022-11-12 13:52:00",65000);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (3,0000000000170,"2022-11-12 01:05:02",240300);
insert into movimientos (IDTipoMovimiento,NumeroCuenta,Fecha,Importe) 
values (4,0000000000155,"2022-11-12 16:18:02",124000);


#tasa
insert into tasanominalanual (tna) values (0.9168);  