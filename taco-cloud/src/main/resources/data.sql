delete from Ingredient;
insert into Ingredient (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type) values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) values ('SRCR', 'Sour Cream', 'SAUCE');

delete from Taco_Users;
insert into Taco_Users (username, password, enabled)
    values ("user1", "$2y$10$fvoodC49WSnpaBT8U7/NmO08sjuraDjmNVUwfibhj15PdGIorvXX.", 1);
insert into Taco_Users (username, password, enabled)
    values ("user2", "$2y$10$66IRPhBvgnMSTXZcVzLVZOsra39u0L4XER//.akmmb26x8Vv9btNW", 1);

delete from Taco_User_Authorities;
insert into Taco_User_Authorities (username, authority) values ("user1", "ROLE_USER");
insert into Taco_User_Authorities (username, authority) values ("user2", "ROLE_USER");
