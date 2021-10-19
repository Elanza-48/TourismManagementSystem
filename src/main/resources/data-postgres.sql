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
insert into main.user_account(id, name, district, state, street, zip, email, mobile_no, password, role, status)
values
    ((select uuid_generate_v4()),'Rajarshi Kundu', 'Ghatal', 'West Bengal', 'Ward no 22, Konnagar',721214, 'elanza48@outlook.com', 9645520836, 'password_a1', 'ADMIN', 'ACTIVE'),
    ((select uuid_generate_v4()),'Rishav lahiri', 'Kolkata', 'West Bengal', 'Balaka Apartment',700075, 'rishavlahiri55@gmail.com', 8333690652, 'password_m1', 'MANAGER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Ronit Chakraborty', 'Kolkata', 'West Bengal', '3rd Jadav Lane',700210, 'rjronit99@gmail.com', 7044945943, 'password_m2', 'MANAGER', 'INACTIVE'),
    ((select uuid_generate_v4()),'Sohaib Akram', 'North Delhi', 'Delhi', 'Khyber Pass, Civil Lines',110054, 'akrams121@yahoo.com', 7304985675, 'password_u1', 'USER', 'CLOSED'),
    ((select uuid_generate_v4()),'Gunjan Amrapalli', 'Mangalore', 'Karnataka', 'Mangalore H.O',575001, 'gunjana93@hotmail.com', 7986554746, 'password_u2', 'USER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Avinash Yadav', 'Aligarh', 'Uttar Pradesh', 'Aligarh City',202001, 'yadavji82@outlook.com', 9756954756, 'password_u3', 'USER', 'SUSPENDED'),
    ((select uuid_generate_v4()),'Sheha Patel', 'Aurangabad', 'Bihar', 'Chandel Niwas, Maharajganj Road',824101, 'mesheha46@yahoo.com', 7563554663, 'password_u4', 'USER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Kamalpreet Kaur', 'Ludhiana', 'Punjab', 'Ferozepur Road',141001, 'kpkaur98@hotmail.com', 8657454743, 'password_u5', 'USER', 'INACTIVE');

insert into main.destination_info(id, name, description, province, max_stay_duration)
values
    ((select uuid_generate_v4()), 'Manali', 'Himachal Pradesh', 'Manali', 7),
    ((select uuid_generate_v4()), 'Ladakh', 'UT of Leh and Ladakh', 'Ladakh', 7),
    ((select uuid_generate_v4()), 'Jammu','UT of Jammu and Kashmir', 'Jammu', 7),
    ((select uuid_generate_v4()), 'Kashmir','UT of Jammu and Kashmir', 'Kashmir', 7),
    ((select uuid_generate_v4()), 'Hawa Mahal','Located in Jaipur' ,'Rajasthan', 2),
    ((select uuid_generate_v4()), 'Sri Harmandir Sahib','The Golden Temple, Located In Amritsar' ,'Rajasthan', 1),
    ((select uuid_generate_v4()), 'Rishikesh','Himalayan foothills beside the Ganges River' ,'Uttarakhand', 7),
    ((select uuid_generate_v4()), 'Nainital','Set around Nainital Lake' ,'Uttarakhand', 7),
    ((select uuid_generate_v4()), 'Jama Masjit','In Delhi' ,'New Delhi', 1),
    ((select uuid_generate_v4()), 'Kolkata','City of Joy' ,'West Bengal', 7),
    ((select uuid_generate_v4()), 'Gir National Park','Wild Life Sanctuary' ,'Gujarat', 7),
    ((select uuid_generate_v4()), 'Ajanta Caves','The Buddhist Caves in Ajanta' ,'Maharashtra', 2),
    ((select uuid_generate_v4()), 'Bannerghatta National Park','Bannerghatta National Park' ,'Karnataka', 4),
    ((select uuid_generate_v4()), 'Athirappilly Water Falls','In Thrissur District in Kerala' ,'Kerala', 4),
    ((select uuid_generate_v4()), 'Ooty','In Western Ghats mountains' ,'Tamil Nadu', 7),
    ((select uuid_generate_v4()), 'Andaman and Nicobar Islands','Andaman and Nicobar Islands' ,'Andaman and Nicobar', 10);

insert into main.hotel_info(id, name, district, state, street, zip, email, mobile_no, base_price, type, dest_id)
values
    ((select uuid_generate_v4()), 'Montana Blues Resort','Manali', 'Himachal Pradesh','MDR 29, Prini',175103, 'montanabluesresort@gmail.com', 7468952136, 1586, 'PRIME',(select id from main.destination_info where name='Manali')),
    ((select uuid_generate_v4()), 'The Grand Dragon Ladakh','Leh', 'Ladakh','Old Road Sheynam', 194101, 'granddragonhotel@outlook.com', 6589746812, 11582, 'ROYAL',(select id from main.destination_info where name='Ladakh')),
    ((select uuid_generate_v4()), 'Radisson Blu Jammu','Jammu', 'Jammu','Radisson Square, Narwal Bala, Bypass Road',180006, 'radissonbluhotel@gmail.com', 8569552136, 3000, 'PRIME',(select id from main.destination_info where name='Jammu')),
    ((select uuid_generate_v4()), 'H.B Zaindari Palace','Srinagar', 'Kashmir','Dal Lake Shikara Ghat 14 Nehru Park',190001, 'zaindaripalace@hotmail.com', 9685647124, 2000, 'STANDARD',(select  id from main.destination_info where name='Kashmir')),
    ((select uuid_generate_v4()), 'Trident Jaipur','Jaipur', 'Rajasthan','Amber Fort Road, Opposite Jal Maha',302002, 'tridenthoteljaipur@yahoo.com', 4569832168, 4500, 'ROYAL',(select id from main.destination_info where name='Hawa Mahal')),
    ((select uuid_generate_v4()), 'Fairfield by Marriott Amritsar','Amritsar', 'Punjab','Albert Road',143001, 'fairfieldhotel@outlook.com', 8564756921, 3865, 'STANDARD',(select  id from main.destination_info where name='Sri Harmandir Sahib')),
    ((select uuid_generate_v4()), 'Ganga Kinare','Rishīkesh', 'Uttarakhand','237 Virbhadra Road',249201, 'gangakinareresort@hotmail.com', 5692314768, 8576, 'ROYAL',(select  id from main.destination_info where name='Rishikesh')),
    ((select uuid_generate_v4()), 'Lakeside Inn','Nainital', 'Uttarakhand','Mall Road',263002, 'hotellakesideinn@gmail.com', 5874635012, 3586, 'PRIME',(select  id from main.destination_info where name='Nainital')),
    ((select uuid_generate_v4()), 'Mannat international','Paharganj', 'New Delhi','9346 multani dhanda',110055, 'hotelmannatinternational@gmail.com', 6897235014, 1586, 'STANDARD',(select  id from main.destination_info where name='Jama Masjit')),
    ((select uuid_generate_v4()), 'ITC Royal Bengal','Kolkata', 'West Bengal','1 JBS Haldane Avenue',700046, 'royalbengal@yahoo.com', 7569832005, 5832, 'ROYAL',(select  id from main.destination_info where name='Kolkata')),
    ((select uuid_generate_v4()), 'Anil Farmhouse','Sasan Gir', 'Gujarat','Village Bhalchel',362135, 'anilfarmhouseresort@outlook.com', 4698332158, 2986, 'STANDARD',(select  id from main.destination_info where name='Gir National Park')),
    ((select uuid_generate_v4()), 'Vivanta','Aurangabad', 'Maharashtra','8-N - 12 , CIDCO, Rauza Bagh, Navkhanda',431003, 'Vivantamaharashtra@hotmail.com', 3258965449, 4238, 'PRIME',(select  id from main.destination_info where name='Ajanta Caves')),
    ((select uuid_generate_v4()), 'Radiant Resort','Bangalore', 'Karnataka','C K Palya Road, 17 th km Bannerghatta road',560083, 'radiantresort@gmail.com', 1212587699, 3476, 'STANDARD',(select  id from main.destination_info where name='Bannerghatta National Park')),
    ((select uuid_generate_v4()), 'Casa Rio Resorts','Athirappilly', 'Kerala','P.O Vettilappara, Chalakkudy Trissur',680724, 'casarioresorts@outlook.com', 6698745321, 7500, 'PRIME',(select  id from main.destination_info where name='Athirappilly Water Falls')),
    ((select uuid_generate_v4()), 'Treebo Trend Sky Dale Inn & Suites','Ooty', 'Tamil Nadu','284, Ooty West Lake Road (Thettukkal Road)',643004, 'skydaleinn@hotmail.com', 5398746588, 1586, 'STANDARD',(select id from main.destination_info where name='Ooty')),
    ((select uuid_generate_v4()), 'Hotel De Pebbles','Port Blair', 'Andaman and Nicobar','Jawaharlal Nehru Road Ground',744102, 'hoteldepebbles@yahoo.com', 2458762457, 2957, 'STANDARD',(select  id from main.destination_info where name='Andaman and Nicobar Islands'));