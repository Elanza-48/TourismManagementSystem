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

insert into main.user_privilege(id, title, description, created_at, last_update) values
    ((select uuid_generate_v4()),'USER_CREATE','USER_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'USER_READ','USER_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'USER_UPDATE','USER_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'USER_DELETE','USER_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'USER_SELF_READ','USER_SELF_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'USER_SELF_UPDATE','USER_SELF_UPDATE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'HOTEL_CREATE','HOTEL_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'HOTEL_READ','HOTEL_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'HOTEL_UPDATE','HOTEL_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'HOTEL_DELETE','HOTEL_DELETE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'DESTINATION_CREATE','DESTINATION_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'DESTINATION_READ','DESTINATION_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'DESTINATION_UPDATE','DESTINATION_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'DESTINATION_DELETE','DESTINATION_DELETE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'TRANSPORT_CREATE','TRANSPORT_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TRANSPORT_READ','TRANSPORT_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TRANSPORT_UPDATE','TRANSPORT_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TRANSPORT_DELETE','TRANSPORT_DELETE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'PACKAGE_CREATE','PACKAGE_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PACKAGE_READ','PACKAGE_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PACKAGE_UPDATE','PACKAGE_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PACKAGE_DELETE','PACKAGE_DELETE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'BOOKING_CREATE','BOOKING_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'BOOKING_READ','BOOKING_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'BOOKING_UPDATE','BOOKING_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'BOOKING_DELETE','BOOKING_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'BOOKING_SELF_READ','BOOKING_SELF_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'BOOKING_SELF_UPDATE','BOOKING_SELF_UPDATE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'TICKET_CREATE','TICKET_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TICKET_READ','TICKET_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TICKET_UPDATE','TICKET_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TICKET_DELETE','TICKET_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TICKET_SELF_READ','TICKET_SELF_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'TICKET_SELF_UPDATE','TICKET_SELF_UPDATE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'PAYMENT_CREATE','PAYMENT_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PAYMENT_READ','PAYMENT_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PAYMENT_UPDATE','PAYMENT_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PAYMENT_DELETE','PAYMENT_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'PAYMENT_SELF_READ','PAYMENT_SELF_READ',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'REVIEW_CREATE','REVIEW_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'REVIEW_READ','REVIEW_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'REVIEW_UPDATE','REVIEW_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'REVIEW_DELETE','REVIEW_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'REVIEW_SELF_UPDATE','REVIEW_SELF_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'REVIEW_SELF_DELETE','REVIEW_SELF_DELETE',(select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),'ENQUIRY_CREATE','ENQUIRY_CREATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'ENQUIRY_READ','ENQUIRY_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'ENQUIRY_UPDATE','ENQUIRY_UPDATE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'ENQUIRY_DELETE','ENQUIRY_DELETE',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'ENQUIRY_SELF_READ','ENQUIRY_SELF_READ',(select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),'ENQUIRY_SELF_UPDATE','ENQUIRY_SELF_UPDATE',(select localtimestamp), (select localtimestamp));


insert into main.user_role(id, title, description, created_at, last_update) values
    ((select  uuid_generate_v4()),'ADMIN', 'ROLE_ADMIN', (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()),'MANAGER', 'ROLE_MANAGER', (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()),'USER', 'ROLE_USER', (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()),'ALL', 'ROLE_ALL', (select localtimestamp), (select localtimestamp));


insert into  main.user_role_privilege_map(role_id, privilege_id) values
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='USER_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='USER_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='USER_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='HOTEL_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='HOTEL_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='HOTEL_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='HOTEL_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='DESTINATION_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='DESTINATION_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='DESTINATION_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='DESTINATION_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TRANSPORT_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TRANSPORT_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TRANSPORT_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TRANSPORT_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PACKAGE_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PACKAGE_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PACKAGE_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PACKAGE_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='BOOKING_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='BOOKING_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='BOOKING_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='BOOKING_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TICKET_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TICKET_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TICKET_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='TICKET_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PAYMENT_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PAYMENT_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PAYMENT_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='PAYMENT_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='REVIEW_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='REVIEW_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='REVIEW_DELETE')),

    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='ENQUIRY_CREATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='ENQUIRY_READ')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='ENQUIRY_UPDATE')),
    ((select id from main.user_role where title='ADMIN'),(select id from main.user_privilege where title='ENQUIRY_DELETE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='USER_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='USER_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='HOTEL_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='HOTEL_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='DESTINATION_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='DESTINATION_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='TRANSPORT_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='TRANSPORT_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PACKAGE_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PACKAGE_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PACKAGE_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='BOOKING_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='BOOKING_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='BOOKING_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='TICKET_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='TICKET_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='TICKET_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PAYMENT_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PAYMENT_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='PAYMENT_UPDATE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='REVIEW_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='REVIEW_UPDATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='REVIEW_SELF_DELETE')),

    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='ENQUIRY_CREATE')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='ENQUIRY_READ')),
    ((select id from main.user_role where title='MANAGER'),(select id from main.user_privilege where title='ENQUIRY_UPDATE')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='USER_SELF_READ')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='USER_SELF_UPDATE')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='HOTEL_READ')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='TRANSPORT_READ')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='BOOKING_CREATE')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='BOOKING_SELF_READ')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='BOOKING_SELF_UPDATE')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='TICKET_SELF_READ')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='TICKET_SELF_UPDATE')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='PAYMENT_CREATE')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='PAYMENT_SELF_READ')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='REVIEW_CREATE')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='REVIEW_SELF_UPDATE')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='REVIEW_SELF_DELETE')),

    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='ENQUIRY_CREATE')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='ENQUIRY_SELF_READ')),
    ((select id from main.user_role where title='USER'),(select id from main.user_privilege where title='ENQUIRY_SELF_UPDATE')),

    ((select id from main.user_role where title='ALL'),(select id from main.user_privilege where title='PACKAGE_READ')),
    ((select id from main.user_role where title='ALL'),(select id from main.user_privilege where title='DESTINATION_READ')),
    ((select id from main.user_role where title='ALL'),(select id from main.user_privilege where title='REVIEW_READ'));


insert into main.user_account(id, name, gender, dob, district, state, street, zip, email, mobile_no, password, active, suspended, created_at, last_update, role_id) values
    ((select uuid_generate_v4()),'Rajarshi Kundu',0, to_date('19920316','YYYYMMDD'), 'Paschim Medinipur', 'WB', 'Ward no 22, Konnagar, Ghatal',721214, 'elanza48@outlook.com', 9645520836, '{noop}password_a1', true, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='ADMIN')),
    ((select uuid_generate_v4()),'Rishav lahiri',0 , to_date('19950910','YYYYMMDD'),  'Kolkata', 'WB', 'Balaka Apartment',700075, 'rishavlahiri55@gmail.com', 8333690652, '{noop}password_m1', true, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='MANAGER')),
    ((select uuid_generate_v4()),'Ronit Chakraborty',0 , to_date('19971022','YYYYMMDD'), 'Kolkata', 'WB', '3rd Jadav Lane',700210, 'rjronit99@gmail.com', 7044945943, '{noop}password_m2', false, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='MANAGER')),
    ((select uuid_generate_v4()),'Sohaib Akram' ,0 , to_date('19870808','YYYYMMDD'), 'North Delhi', 'DL', 'Khyber Pass, Civil Lines',110054, 'akrams121@yahoo.com', 7304985675, '{noop}password_u1', false, true, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='USER')),
    ((select uuid_generate_v4()),'Gunjan Amrapalli' ,2 , to_date('19810725','YYYYMMDD'), 'Mangalore', 'KA', 'Mangalore H.O',575001, 'gunjana93@hotmail.com', 7986554746, '{noop}password_u2', true, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='USER')),
    ((select uuid_generate_v4()),'Avinash Yadav' ,0 , to_date('19791231','YYYYMMDD'), 'Aligarh', 'UP', 'Aligarh City',202001, 'yadavji82@outlook.com', 9756954756, '{noop}password_u3', true, true, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='USER')),
    ((select uuid_generate_v4()),'Sheha Patel' ,1 , to_date('19900911','YYYYMMDD'), 'Aurangabad', 'BR', 'Chandel Niwas, Maharajganj Road',824101, 'mesheha46@yahoo.com', 7563554663, '{noop}password_u4', true, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='USER')),
    ((select uuid_generate_v4()),'Kamalpreet Kaur' ,1, to_date('19960703','YYYYMMDD'), 'Ludhiana', 'PB', 'Ferozepur Road',141001, 'kpkaur98@hotmail.com', 8657454743, '{noop}password_u5', false, false, (select localtimestamp), (select localtimestamp),
     (select id from main.user_role where title='USER'));


insert into main.destination_info(id, name, description, province, max_stay_duration, created_at, last_update) values
    ((select uuid_generate_v4()), 'Manali', 'Himachal Pradesh', 'HP', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Ladakh', 'UT of Leh and Ladakh', 'LA', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Jammu','UT of Jammu and Kashmir', 'JK', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Kashmir','UT of Jammu and Kashmir', 'JK', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Hawa Mahal','Located in Jaipur' ,'RJ', 2, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Sri Harmandir Sahib','The Golden Temple, Located In Amritsar' ,'PB', 1, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Rishikesh','Himalayan foothills beside the Ganges River' ,'UK', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Nainital','Set around Nainital Lake' ,'UK', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Jama Masjit','In Delhi' ,'DL', 1, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Kolkata','City of Joy' ,'WB', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Gir National Park','Wild Life Sanctuary' ,'GJ', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Ajanta Caves','The Buddhist Caves in Ajanta' ,'MH', 2, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Bannerghatta National Park','Bannerghatta National Park' ,'KA', 4, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Athirappilly Water Falls','In Thrissur District in Kerala' ,'KL', 4, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Ooty','In Western Ghats mountains' ,'TN', 7, (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Andaman and Nicobar Islands','Andaman and Nicobar Islands' ,'AN', 10, (select localtimestamp), (select localtimestamp));

insert into main.hotel_info(id, name, district, state, street, zip, email, mobile_no, base_price, type, dest_id, created_at, last_update) values
    ((select uuid_generate_v4()), 'Montana Blues Resort','Manali', 'HP','MDR 29, Prini',175103, 'montanabluesresort@gmail.com', 7468952136, 1586, 'PRIME',(select id from main.destination_info where name='Manali'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'The Grand Dragon Ladakh','Leh', 'LA','Old Road Sheynam', 194101, 'granddragonhotel@outlook.com', 6589746812, 11582, 'ROYAL',(select id from main.destination_info where name='Ladakh'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Radisson Blu Jammu','Jammu', 'JK','Radisson Square, Narwal Bala, Bypass Road',180006, 'radissonbluhotel@gmail.com', 8569552136, 3000, 'PRIME',(select id from main.destination_info where name='Jammu'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'H.B Zaindari Palace','Srinagar', 'JK','Dal Lake Shikara Ghat 14 Nehru Park',190001, 'zaindaripalace@hotmail.com', 9685647124, 2000, 'STANDARD',(select  id from main.destination_info where name='Kashmir'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Trident Jaipur','Jaipur', 'RJ','Amber Fort Road, Opposite Jal Maha',302002, 'tridenthoteljaipur@yahoo.com', 4569832168, 4500, 'ROYAL',(select id from main.destination_info where name='Hawa Mahal'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Fairfield by Marriott Amritsar','Amritsar', 'PB','Albert Road',143001, 'fairfieldhotel@outlook.com', 8564756921, 3865, 'STANDARD',(select  id from main.destination_info where name='Sri Harmandir Sahib'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Ganga Kinare','Rishīkesh', 'UK','237 Virbhadra Road',249201, 'gangakinareresort@hotmail.com', 5692314768, 8576, 'ROYAL',(select  id from main.destination_info where name='Rishikesh'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Lakeside Inn','Nainital', 'UK','Mall Road',263002, 'hotellakesideinn@gmail.com', 5874635012, 3586, 'PRIME',(select  id from main.destination_info where name='Nainital'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Mannat international','Paharganj', 'DL','9346 multani dhanda',110055, 'hotelmannatinternational@gmail.com', 6897235014, 1586, 'STANDARD',(select  id from main.destination_info where name='Jama Masjit'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'ITC Royal Bengal','Kolkata', 'WB','1 JBS Haldane Avenue',700046, 'royalbengal@yahoo.com', 7569832005, 5832, 'ROYAL',(select  id from main.destination_info where name='Kolkata'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Anil Farmhouse','Sasan Gir', 'GJ','Village Bhalchel',362135, 'anilfarmhouseresort@outlook.com', 4698332158, 2986, 'STANDARD',(select  id from main.destination_info where name='Gir National Park'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Vivanta','Aurangabad', 'MH','8-N - 12 , CIDCO, Rauza Bagh, Navkhanda',431003, 'Vivantamaharashtra@hotmail.com', 3258965449, 4238, 'PRIME',(select  id from main.destination_info where name='Ajanta Caves'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Radiant Resort','Bangalore', 'KA','C K Palya Road, 17 th km Bannerghatta road',560083, 'radiantresort@gmail.com', 1212587699, 3476, 'STANDARD',(select  id from main.destination_info where name='Bannerghatta National Park'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Casa Rio Resorts','Athirappilly', 'KL','P.O Vettilappara, Chalakkudy Trissur',680724, 'casarioresorts@outlook.com', 6698745321, 7500, 'PRIME',(select  id from main.destination_info where name='Athirappilly Water Falls'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Treebo Trend Sky Dale Inn & Suites','Ooty', 'TN','284, Ooty West Lake Road (Thettukkal Road)',643004, 'skydaleinn@hotmail.com', 5398746588, 1586, 'STANDARD',(select id from main.destination_info where name='Ooty'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Hotel De Pebbles','Port Blair', 'AN','Jawaharlal Nehru Road Ground',744102, 'hoteldepebbles@yahoo.com', 2458762457, 2957, 'STANDARD',(select  id from main.destination_info where name='Andaman and Nicobar Islands'), (select localtimestamp), (select localtimestamp));

insert into main.transport_info(id, name, description, public, mode, created_at, last_update) values
    ((select uuid_generate_v4()), 'Slik route transports', 'Delux bus service', false, 'BUS', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Ozone car rentals', 'Comfort to day car service', false, 'CAR', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'UTC', 'Uttarakhand Transport Corporation', true, 'BUS', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Palace on Wheels', 'Experience royalty at its finest', true, 'RAILWAY', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Vector car rentals', 'Speedy and Comfort', false, 'CAR', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Himsagar Express', 'From Kashmir to Kanyakumari.', true, 'RAILWAY', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Indigo', 'The Lean clean machine', false, 'FLIGHT', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Omega waterways', 'Experts on waterways', false, 'STEAMER', (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()), 'Sea Hawk Luxury', 'Serve the best on water', false, 'CRUISE', (select localtimestamp), (select localtimestamp));

insert into main.tour_package(id, name, activities, description, events, active, created_at, last_update) values
    ((select  uuid_generate_v4()), 'Amazing Himalayas','Hill views, Side seeing, Traking', 'Amazing Views of himalayan range', 'Local Art and culture', true, (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()), 'Couple Goals','Forest Views, Side seeing, Traking', 'Amazing Places for Honeymoon', 'Local Art and culture, Safari', true, (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()), 'Spiritual Mighty','Hill views, Temple view, Traking', 'Enjoy the places almighty', 'Temple/Mosque/Gurdwara Darshan', true, (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()), 'Holiday Enclave','Hill views, Side seeing, Traking, Sea side View', 'Enjoy hills and the sea in true pleasure', 'Surfing, Sky Paragliding, Snorkling, Scuba Diving, Rope Way', true, (select localtimestamp), (select localtimestamp)),
    ((select  uuid_generate_v4()), 'City Lights','City views, Side seeing, City/Town roam', 'Amazing Views of city and village.', 'Local Art and culture, foods, night party ', false, (select localtimestamp), (select localtimestamp));

insert into main.package_destination_map(pkg_id, dest_id) values
    ((select id from main.tour_package where name='Amazing Himalayas'),(select id from main.destination_info where name='Rishikesh')),
    ((select id from main.tour_package where name='Amazing Himalayas'),(select id from main.destination_info where name='Ladakh')),
    ((select id from main.tour_package where name='Amazing Himalayas'),(select id from main.destination_info where name='Manali')),
    ((select id from main.tour_package where name='Couple Goals'),(select id from main.destination_info where name='Manali')),
    ((select id from main.tour_package where name='Couple Goals'),(select id from main.destination_info where name='Kashmir')),
    ((select id from main.tour_package where name='Couple Goals'),(select id from main.destination_info where name='Ooty')),
    ((select id from main.tour_package where name='Spiritual Mighty'),(select id from main.destination_info where name='Rishikesh')),
    ((select id from main.tour_package where name='Spiritual Mighty'),(select id from main.destination_info where name='Jama Masjit')),
    ((select id from main.tour_package where name='Spiritual Mighty'),(select id from main.destination_info where name='Sri Harmandir Sahib')),
    ((select id from main.tour_package where name='City Lights'),(select id from main.destination_info where name='Kolkata')),
    ((select id from main.tour_package where name='City Lights'),(select id from main.destination_info where name='Hawa Mahal')),
    ((select id from main.tour_package where name='City Lights'),(select id from main.destination_info where name='Ajanta Caves')),
    ((select id from main.tour_package where name='Holiday Enclave'),(select id from main.destination_info where name='Gir National Park')),
    ((select id from main.tour_package where name='Holiday Enclave'),(select id from main.destination_info where name='Nainital')),
    ((select id from main.tour_package where name='Holiday Enclave'),(select id from main.destination_info where name='Andaman and Nicobar Islands'));

insert into main.package_transport_map(pkg_id, transport_id) values
    ((select id from main.tour_package where name='Amazing Himalayas'),(select id from main.transport_info where name='Palace on Wheels')),
    ((select id from main.tour_package where name='Amazing Himalayas'),(select id from main.transport_info where name='Ozone car rentals')),
    ((select id from main.tour_package where name='Couple Goals'),(select id from main.transport_info where name='Indigo')),
    ((select id from main.tour_package where name='Couple Goals'),(select id from main.transport_info where name='Vector car rentals')),
    ((select id from main.tour_package where name='Spiritual Mighty'),(select id from main.transport_info where name='Himsagar Express')),
    ((select id from main.tour_package where name='Spiritual Mighty'),(select id from main.transport_info where name='Slik route transports')),
    ((select id from main.tour_package where name='City Lights'),(select id from main.transport_info where name='Slik route transports')),
    ((select id from main.tour_package where name='City Lights'),(select id from main.transport_info where name='Ozone car rentals')),
    ((select id from main.tour_package where name='Holiday Enclave'),(select id from main.transport_info where name='Vector car rentals')),
    ((select id from main.tour_package where name='Holiday Enclave'),(select id from main.transport_info where name='Sea Hawk Luxury'));

insert into main.booking_info(id, date, passenger_count, price, r_type, status, t_date, pkg_id, usr_id, created_at, last_update) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),4,38465,'SUPERIOR','COMPLETE',to_date('20171003','YYYYMMDD'),(select id from main.tour_package where name='Couple Goals'),(select id from main.user_account where email='mesheha46@yahoo.com'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),to_date('20190123','YYYYMMDD'),7,85745,'DELUXE','CANCELLED',to_date('20190611','YYYYMMDD'),(select id from main.tour_package where name='City Lights'),(select id from main.user_account where email='yadavji82@outlook.com'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),to_date('20180329','YYYYMMDD'),3,25283,'SUPERIOR','COMPLETE',to_date('20180907','YYYYMMDD'),(select id from main.tour_package where name='Spiritual Mighty'),(select id from main.user_account where email='kpkaur98@hotmail.com'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),to_date('20191013','YYYYMMDD'),10,158921,'SUPERIOR','COMPLETE',to_date('20200209','YYYYMMDD'),(select id from main.tour_package where name='Holiday Enclave'),(select id from main.user_account where email='gunjana93@hotmail.com'), (select localtimestamp), (select localtimestamp)),
    ((select uuid_generate_v4()),to_date('20211022','YYYYMMDD'),5,65897,'SUPERIOR','ACTIVE',to_date('20220612','YYYYMMDD'),(select id from main.tour_package where name='Holiday Enclave'),(select id from main.user_account where email='yadavji82@outlook.com'), (select localtimestamp), (select localtimestamp));

insert into main.payment_info(id, date, discount, gst, mode, net_charge, status, txn_id, booking_id, created_at , last_update) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),0, 38465*0.18, 'WALLET',38465,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.date=to_date('20170415','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20190123','YYYYMMDD'),0, 85745*0.18, 'UPI',85745,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.date=to_date('20190123','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

     ((select uuid_generate_v4()),to_date('20180329','YYYYMMDD'),0, 25283*0.18, 'INTERNET_BANKING',25283,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.date=to_date('20180329','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20191013','YYYYMMDD'),0, 158921*0.18, 'CARD',158921,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com' and bi.date=to_date('20191013','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20211022','YYYYMMDD'),0, 65897*0.18, 'WALLET',65897,'PENDING',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.date=to_date('20211022','YYYYMMDD')), (select localtimestamp), (select localtimestamp));


insert into main.booking_transport_ticket(id, date, seat_no, booking_id, transport_id, created_at ,last_update) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'85D1',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='FLIGHT' and bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'32',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.t_date=to_date('20220612','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='CRUISE' and bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.t_date=to_date('20220612','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

     ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'96',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='RAILWAY'and bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'85D1',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='CRUISE' and bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com'  and bi.t_date=to_date('20200209','YYYYMMDD')), (select localtimestamp), (select localtimestamp));


insert into main.destination_review(id, date, rating, review, booking_id, dest_id, created_at, last_update) values
    ((select uuid_generate_v4()),to_date('20171020','YYYYMMDD'),8, 'Excellent place for trekking !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='mesheha46@yahoo.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20171003','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Manali' ), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20181003','YYYYMMDD'),8, 'I am in love with the ambience of this place. Wonderful !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='kpkaur98@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20180907','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Rishikesh' ), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20200319','YYYYMMDD'),9, 'A haven for sea lovers !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='gunjana93@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20200209','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Andaman and Nicobar Islands' ), (select localtimestamp), (select localtimestamp));


insert into main.hotel_review(id, date, rating, review, booking_id, hotel_id ,created_at, last_update) values
    ((select uuid_generate_v4()),to_date('20171026','YYYYMMDD'),7,'Excellent Service !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='mesheha46@yahoo.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20171003','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Manali' and di.id=hi.dest_id), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20181009','YYYYMMDD'),6,'Good and Comfort Stay !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='kpkaur98@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20180907','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Rishikesh' and di.id=hi.dest_id), (select localtimestamp), (select localtimestamp)),

    ((select uuid_generate_v4()),to_date('20200326','YYYYMMDD'),8,'Good Quality Rooms !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='gunjana93@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20200209','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Andaman and Nicobar Islands' and di.id=hi.dest_id), (select localtimestamp), (select localtimestamp));


insert into main.enquiry(id, date, subject, body, booking_id, enq_open, created_at, last_update) values
    ((select uuid_generate_v4()), to_date('20190427','YYYYMMDD'),'Cancellation of My Booking.','Sir,\n Due to some unavoidable circumstance I will not be able to continue my journey in the trip. So I want cancel my booking. \nKindly help me. \nThank you',
     (select bi.id from main.user_account as usr, main.booking_info as bi where usr.email='yadavji82@outlook.com' and usr.id=bi.usr_id and bi.t_date=to_date('20190611','YYYYMMDD')),false, (select localtimestamp), (select localtimestamp));


/*
PRIVILEGES: {
    "USER_CREATE",
    "USER_READ",
    "USER_UPDATE",
    "USER_DELETE",
    "USER_SELF_READ",
    "USER_SELF_UPDATE",

    "HOTEL_CREATE",
    "HOTEL_READ",
    "HOTEL_UPDATE",
    "HOTEL_DELETE",

    "DESTINATION_CREATE",
    "DESTINATION_READ",
    "DESTINATION_UPDATE",
    "DESTINATION_DELETE",

    "TRANSPORT_CREATE",
    "TRANSPORT_READ",
    "TRANSPORT_UPDATE",
    "TRANSPORT_DELETE",

    "PACKAGE_CREATE",
    "PACKAGE_READ",
    "PACKAGE_UPDATE",
    "PACKAGE_DELETE",

    "BOOKING_CREATE",
    "BOOKING_READ",
    "BOOKING_UPDATE",
    "BOOKING_DELETE",
    "BOOKING_SELF_READ",
    "BOOKING_SELF_UPDATE",

    "TICKET_CREATE",
    "TICKET_READ",
    "TICKET_UPDATE",
    "TICKET_DELETE",
    "TICKET_SELF_READ",
    "TICKET_SELF_UPDATE",

    "PAYMENT_CREATE",
    "PAYMENT_READ",
    "PAYMENT_UPDATE",
    "PAYMENT_DELETE",
    "PAYMENT_SELF_READ",

    "REVIEW_CREATE",
    "REVIEW_READ",
    "REVIEW_UPDATE",
    "REVIEW_DELETE",
    "REVIEW_SELF_UPDATE",
    "REVIEW_SELF_DELETE",

    "ENQUIRY_CREATE",
    "ENQUIRY_READ",
    "ENQUIRY_UPDATE",
    "ENQUIRY_DELETE",
    "ENQUIRY_SELF_READ",
    "ENQUIRY_SELF_UPDATE"
}
*/