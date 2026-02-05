create extension if not exists "uuid-ossp";

create table if not exists "country"
(
    id       UUID unique   not null default uuid_generate_v1() primary key,
    name     varchar(50)   unique not null,
    code     varchar(50)   not null
    );

alter table "country"
    owner to postgres;

delete
from "country";

insert into "country"(name, code)
values ('Tanzania', 'TZ');
insert into "country"(name, code)
values ('Western Sahara', 'EH');
insert into "country"(name, code)
values ('Canada', 'CA');
insert into "country"(name, code)
values ('United States', 'US');