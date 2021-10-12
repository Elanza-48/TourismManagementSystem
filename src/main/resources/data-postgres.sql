create  or replace function main.truncate_tables(usr_name in varchar, schema_name in varchar) returns void 
language plpgsql as '
declare
    statements cursor for
        select tablename from pg_tables where schemaname= schema_name and tableowner=usr_name;
begin
    for stmt in statements loop
        execute ''truncate table '' || schema_name || ''.'' || quote_ident(stmt.tablename) || '' cascade'';
    end loop;
end;
';
select main.truncate_tables('elanza48', 'main');

---- Insert data ----
insert into main.user_account(id, name, district, state, street, zip, email, mobile_no, password, role, status) values ((select uuid_generate_v4()),'Rajarshi Kundu', 'Ghatal', 'West Bengal', 'Ward no 16, Konnagar',721212, 'elanza48@outlook.com', 9674652083, 'password_a1', 'ADMIN', 'ACTIVE');
insert into main.user_account(id, name, district, state, street, zip, email, mobile_no, password, role, status) values ((select uuid_generate_v4()),'Rishav lahiri', 'Kolkata', 'West Bengal', 'Balaka Apartment',700075, 'rishavlahiri55@gmail.com', 8335090842, 'password_m1', 'MANAGER', 'ACTIVE');
insert into main.user_account(id, name, district, state, street, zip, email, mobile_no, password, role, status) values ((select uuid_generate_v4()),'Ronit Chakraborty', 'Kolkata', 'West Bengal', '3rd Jadav Lane',700215, 'rjronit@gmail.com', 7044954743, 'password_u1', 'USER', 'ACTIVE');
