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
insert into main.user_account(id, name, gender,dob, district, state, street, zip, email, mobile_no, password, role, status) values
    ((select uuid_generate_v4()),'Rajarshi Kundu','MALE', to_date('19920316','YYYYMMDD'), 'Ghatal', 'West Bengal', 'Ward no 22, Konnagar',721214, 'elanza48@outlook.com', 9645520836, 'password_a1', 'ADMIN', 'ACTIVE'),
    ((select uuid_generate_v4()),'Rishav lahiri','MALE' , to_date('19950910','YYYYMMDD'),  'Kolkata', 'West Bengal', 'Balaka Apartment',700075, 'rishavlahiri55@gmail.com', 8333690652, 'password_m1', 'MANAGER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Ronit Chakraborty','MALE' , to_date('19971022','YYYYMMDD'), 'Kolkata', 'West Bengal', '3rd Jadav Lane',700210, 'rjronit99@gmail.com', 7044945943, 'password_m2', 'MANAGER', 'INACTIVE'),
    ((select uuid_generate_v4()),'Sohaib Akram' ,'MALE' , to_date('19870808','YYYYMMDD'), 'North Delhi', 'Delhi', 'Khyber Pass, Civil Lines',110054, 'akrams121@yahoo.com', 7304985675, 'password_u1', 'USER', 'CLOSED'),
    ((select uuid_generate_v4()),'Gunjan Amrapalli' ,'MALE' , to_date('19810725','YYYYMMDD'), 'Mangalore', 'Karnataka', 'Mangalore H.O',575001, 'gunjana93@hotmail.com', 7986554746, 'password_u2', 'USER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Avinash Yadav' ,'MALE' , to_date('19791231','YYYYMMDD'), 'Aligarh', 'Uttar Pradesh', 'Aligarh City',202001, 'yadavji82@outlook.com', 9756954756, 'password_u3', 'USER', 'SUSPENDED'),
    ((select uuid_generate_v4()),'Sheha Patel' ,'FEMALE' , to_date('19900911','YYYYMMDD'), 'Aurangabad', 'Bihar', 'Chandel Niwas, Maharajganj Road',824101, 'mesheha46@yahoo.com', 7563554663, 'password_u4', 'USER', 'ACTIVE'),
    ((select uuid_generate_v4()),'Kamalpreet Kaur' ,'FEMALE', to_date('19960703','YYYYMMDD'), 'Ludhiana', 'Punjab', 'Ferozepur Road',141001, 'kpkaur98@hotmail.com', 8657454743, 'password_u5', 'USER', 'INACTIVE');

insert into main.destination_info(id, name, description, province, max_stay_duration) values
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

insert into main.hotel_info(id, name, district, state, street, zip, email, mobile_no, base_price, type, dest_id) values
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

insert into main.transport_info(id, name, description, entity, mode) values
    ((select uuid_generate_v4()), 'Slik route transports', 'Delux bus service', 'PRIVATE', 'BUS'),
    ((select uuid_generate_v4()), 'Ozone car rentals', 'Comfort to day car service', 'PRIVATE', 'CAR'),
    ((select uuid_generate_v4()), 'UTC', 'Uttarakhand Transport Corporation', 'PUBLIC', 'BUS'),
    ((select uuid_generate_v4()), 'Palace on Wheels', 'Experience royalty at its finest', 'PUBLIC', 'RAILWAY'),
    ((select uuid_generate_v4()), 'Vector car rentals', 'Speedy and Comfort', 'PRIVATE', 'CAR'),
    ((select uuid_generate_v4()), 'Himsagar Express', 'From Kashmir to Kanyakumari.', 'PUBLIC', 'RAILWAY'),
    ((select uuid_generate_v4()), 'Indigo', 'The Lean clean machine', 'PRIVATE', 'FLIGHT'),
    ((select uuid_generate_v4()), 'Omega waterways', 'Experts on waterways', 'PRIVATE', 'STEAMER'),
    ((select uuid_generate_v4()), 'Sea Hawk Luxury', 'Serve the best on water', 'PRIVATE', 'CRUISE');

insert into main.tour_package(id, name, activities, description, events, is_active) values
    ((select  uuid_generate_v4()), 'Amazing Himalayas','Hill views, Side seeing, Traking', 'Amazing Views of himalayan range', 'Local Art and culture', true),
    ((select  uuid_generate_v4()), 'Couple Goals','Forest Views, Side seeing, Traking', 'Amazing Places for Honeymoon', 'Local Art and culture, Safari', true),
    ((select  uuid_generate_v4()), 'Spiritual Mighty','Hill views, Temple view, Traking', 'Enjoy the places almighty', 'Temple/Mosque/Gurdwara Darshan', true),
    ((select  uuid_generate_v4()), 'Holiday Enclave','Hill views, Side seeing, Traking, Sea side View', 'Enjoy hills and the sea in true pleasure', 'Surfing, Sky Paragliding, Snorkling, Scuba Diving, Rope Way', true),
    ((select  uuid_generate_v4()), 'City Lights','City views, Side seeing, City/Town roam', 'Amazing Views of city and village.', 'Local Art and culture, foods, night party ', false);

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

insert into main.booking_info(id, date, passenger_count, price, r_type, status, t_date, pkg_id, usr_id) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),4,38465,'SUPERIOR','COMPLETE',to_date('20171003','YYYYMMDD'),(select id from main.tour_package where name='Couple Goals'),(select id from main.user_account where email='mesheha46@yahoo.com')),
    ((select uuid_generate_v4()),to_date('20190123','YYYYMMDD'),7,85745,'DELUXE','CANCELLED',to_date('20190611','YYYYMMDD'),(select id from main.tour_package where name='City Lights'),(select id from main.user_account where email='yadavji82@outlook.com')),
    ((select uuid_generate_v4()),to_date('20180329','YYYYMMDD'),3,25283,'SUPERIOR','COMPLETE',to_date('20180907','YYYYMMDD'),(select id from main.tour_package where name='Spiritual Mighty'),(select id from main.user_account where email='kpkaur98@hotmail.com')),
    ((select uuid_generate_v4()),to_date('20191013','YYYYMMDD'),10,158921,'SUPERIOR','COMPLETE',to_date('20200209','YYYYMMDD'),(select id from main.tour_package where name='Holiday Enclave'),(select id from main.user_account where email='gunjana93@hotmail.com')),
    ((select uuid_generate_v4()),to_date('20211022','YYYYMMDD'),5,65897,'SUPERIOR','ACTIVE',to_date('20220612','YYYYMMDD'),(select id from main.tour_package where name='Holiday Enclave'),(select id from main.user_account where email='yadavji82@outlook.com'));

insert into main.payment_info(id, date, discount, gst, mode, net_charge, status, txn_id, booking_id) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),0, 38465*0.18, 'WALLET',38465,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.date=to_date('20170415','YYYYMMDD'))),

    ((select uuid_generate_v4()),to_date('20190123','YYYYMMDD'),0, 85745*0.18, 'UPI',85745,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.date=to_date('20190123','YYYYMMDD'))),

     ((select uuid_generate_v4()),to_date('20180329','YYYYMMDD'),0, 25283*0.18, 'INTERNET_BANKING',25283,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.date=to_date('20180329','YYYYMMDD'))),

    ((select uuid_generate_v4()),to_date('20191013','YYYYMMDD'),0, 158921*0.18, 'CARD',158921,'SUCCESS',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com' and bi.date=to_date('20191013','YYYYMMDD'))),

    ((select uuid_generate_v4()),to_date('20211022','YYYYMMDD'),0, 65897*0.18, 'WALLET',65897,'PENDING',(select uuid_generate_v4()),
     (select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.date=to_date('20211022','YYYYMMDD')));


insert into main.booking_transport_ticket(id, date, seat_no, booking_id, transport_id) values
    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'85D1',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='FLIGHT' and bi.usr_id=usr.id and usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD'))),

    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'32',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.t_date=to_date('20220612','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='CRUISE' and bi.usr_id=usr.id and usr.email='yadavji82@outlook.com' and bi.t_date=to_date('20220612','YYYYMMDD'))),

     ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'96',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='RAILWAY'and bi.usr_id=usr.id and usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD'))),

    ((select uuid_generate_v4()),to_date('20170415','YYYYMMDD'),'85D1',(select bi.id from main.booking_info as bi, main.user_account as usr where bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select ptm.transport_id from main.package_transport_map as ptm, main.booking_info as bi, main.user_account as usr, main.transport_info as ti where ptm.pkg_id=bi.pkg_id and ptm.transport_id=ti.id and ti.mode='CRUISE' and bi.usr_id=usr.id and usr.email='gunjana93@hotmail.com'  and bi.t_date=to_date('20200209','YYYYMMDD')));


insert into main.destination_review(id, date, rating, review, booking_id, dest_id) values
    ((select uuid_generate_v4()),to_date('20171020','YYYYMMDD'),8, 'Excellent place for trekking !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='mesheha46@yahoo.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20171003','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Manali' )),

    ((select uuid_generate_v4()),to_date('20181003','YYYYMMDD'),8, 'I am in love with the ambience of this place. Wonderful !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='kpkaur98@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20180907','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Rishikesh' )),

    ((select uuid_generate_v4()),to_date('20200319','YYYYMMDD'),9, 'A haven for sea lovers !',(select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select di.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di where usr.email='gunjana93@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20200209','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Andaman and Nicobar Islands' ));


insert into main.hotel_review(id, date, rating, review, booking_id, hotel_id) values
    ((select uuid_generate_v4()),to_date('20171026','YYYYMMDD'),7,'Excellent Service !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='mesheha46@yahoo.com' and bi.t_date=to_date('20171003','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='mesheha46@yahoo.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20171003','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Manali' and di.id=hi.dest_id)),

    ((select uuid_generate_v4()),to_date('20181009','YYYYMMDD'),6,'Good and Comfort Stay !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='kpkaur98@hotmail.com' and bi.t_date=to_date('20180907','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='kpkaur98@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20180907','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Rishikesh' and di.id=hi.dest_id)),

    ((select uuid_generate_v4()),to_date('20200326','YYYYMMDD'),8,'Good Quality Rooms !', (select bi.id from main.booking_info as bi, main.user_account as usr where  usr.email='gunjana93@hotmail.com' and bi.t_date=to_date('20200209','YYYYMMDD')),
     (select hi.id from main.user_account as usr, main.booking_info as bi, main.tour_package as tp, main.package_destination_map as pdm, main.destination_info as di, main.hotel_info as hi where usr.email='gunjana93@hotmail.com' and usr.id=bi.usr_id and bi.pkg_id=tp.id and bi.t_date=to_date('20200209','YYYYMMDD') and tp.id=pdm.pkg_id and pdm.dest_id=di.id and di.name='Andaman and Nicobar Islands' and di.id=hi.dest_id));


insert into main.enquiry(id, date, subject, body, booking_id, enq_open) values
    ((select uuid_generate_v4()), to_date('20190427','YYYYMMDD'),'Cancellation of My Booking.','Sir,\n Due to some unavoidable circumstance I will not be able to continue my journey in the trip. So I want cancel my booking. \nKindly help me. \nThank you',
     (select bi.id from main.user_account as usr, main.booking_info as bi where usr.email='yadavji82@outlook.com' and usr.id=bi.usr_id and bi.t_date=to_date('20190611','YYYYMMDD')),false);

