create table user(
    id int primary key AUTO_INCREMENT,
    name varchar(45) not null,
    password varchar(254) not null,
    email varchar(45) not null,
    role enum('ADMIN','CLIENT') not null,
    discount double
);

create table country(
    id int primary key AUTO_INCREMENT,
    country varchar(45) not null,
    city varchar(45) not null
);

create table tour(
    id int primary key AUTO_INCREMENT,
    country_id int,
    days int,
    price decimal(10.0) not null,
    status enum('HOT','ACTIVE','SOLD') not null,
    type_of_tour enum('Vacation','Excursion','Shopping','Therapy')

    constraint fk_country_id foreign key (country_id) references country(id)
);

create table cart(
    id int primary key AUTO_INCREMENT,
    user_id int not null,

    constraint fk_user_id foreign key (user_id) references user(id)
);

create table cart_tour(
    cart_id int not null,
    tour_id int not null,

    constraint fk_cart_id foreign key (cart_id) references cart(id),
    constraint fk_tour_id foreign key (tour_id) references tour(id)
);

create table order(
    id int primary key AUTO_INCREMENT,
    sum NUMERIC not null,
    user_id int not null,

    foreign key (user_id) references user(id)
);

create table order_details(
    id int primary key AUTO_INCREMENT,
    order_id int not null,
    tour_id int not null,
    amount NUMERIC not null,
    price NUMERIC not null,
    constraint fk_order_id foreign key (order_id) references order(id),
    foreign key (tour_id) references tour(id)
);

