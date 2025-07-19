insert into producto(id, nombre, descripcion, precio, categoria_id, imagen_url, stock) values (645789348, 'Coca Cola', 'Bebida sabor cola', 3500,1,'http://someUrl', 500);
insert into producto(id, nombre, descripcion, precio, categoria_id, imagen_url, stock) values (29458733, 'Pepsi', 'Bebida pasable sabor cola', 3000,1,'http://someUrl', 500);
insert into producto(id, nombre, descripcion, precio, categoria_id, imagen_url, stock) values (393457823, 'Manaos', 'Bebida horrible sabor cola', 1500,1,'http://someUrl', 470);
insert into producto(id, nombre, descripcion, precio, categoria_id, imagen_url, stock) values (49342578, 'Crush', 'Bebida mala sabor cola', 2000,1,'http://someUrl', 500);
insert into pedido (id, usuario_id) values (121346, 5);

insert into item(id, producto_id, cantidad, pedido_id) values (19957836, 29458733,30, 121346);