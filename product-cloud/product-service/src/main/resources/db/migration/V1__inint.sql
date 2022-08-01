
create table if not exists products (
    id                  bigserial primary key,
    title               varchar(255),
    price               double precision,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp

);

insert into products (title, price) values ('пальто', 200), ('брюки', 50),
                                           ('ботинки', 100), ('куртка', 150),
                                           ('рубашка', 40), ('платье', 75),
                                           ('трусы', 5);







