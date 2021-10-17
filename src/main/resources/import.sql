insert into suppliers (name, ruc, active, created_at) values ("Sony","593123123123", 1,"2021-10-15");
insert into suppliers (name, ruc, active,created_at) values ("Lg", "593123123456", 1,"2021-10-15");
insert into suppliers (name, ruc, active,created_at) values ("Samsung", "593123123789", 1,"2021-10-15");
insert into suppliers (name, ruc, active,created_at) values ("Apple", "593123123012", 1,"2021-10-15");

insert into products (name, price, stock,   supplier_id, created_at) values ("Sony Mark III",1350.0, 10,1,"2021-10-15");
insert into products (name, price, stock,  supplier_id, created_at) values ("Tv Smart tv 65", 2500.0, 5,2,"2021-10-15");
insert into products (name, price, stock,  supplier_id, created_at) values ("Samsung Note 10 pro", 899.0, 8,3,"2021-10-15");
insert into products (name, price, stock,  supplier_id, created_at) values ("Iphone 12 pro", 1230.0, 20,4,"2021-10-15");


insert into customers (name, identity, phone ) values ("Carlos","12312312123", "+593123123012");
insert into customers (name, identity, phone) values ("Viviana", "123123121456", "+593123123789");
insert into customers (name, identity, phone) values ("Fabian", "12312312678","+593123123456");
insert into customers (name, identity, phone) values ("Ariel", "12312312789","+593123123123");