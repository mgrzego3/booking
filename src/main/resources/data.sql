
insert into Room (id, title) values (1, 'Red' )
insert into Room (id, title) values (2, 'Green')
insert into Room (id, title) values (3, 'Blue')

insert into Row_Config (id, capacity, row_name) values (1, 5, 'A')
insert into Row_Config (id, capacity, row_name) values (2, 6, 'B')
insert into Row_Config (id, capacity, row_name) values (3, 4, 'C')

insert into Room_Rows (room_id, rows_id) values (1, 1)
insert into Room_Rows (room_id, rows_id) values (1, 2)
insert into Room_Rows (room_id, rows_id) values (1, 3)

insert into Screening values (1, '2020-02-04 15:30:00', 'Batman', 1)
insert into Screening values (2, '2020-02-04 16:30:00', 'Titanic', 2)
insert into Screening values (3, '2020-02-04 18:30:00', 'A Team', 2)
insert into Screening values (4, '2020-02-04 18:30:00', 'Star Wars', 1)
insert into Screening values (5, '2020-02-04 16:00:00', 'Joker', 3)
insert into Screening values (6, '2020-02-04 18:00:00', 'X-Men', 3)
insert into Screening values (7, '2020-02-04 18:30:00', 'X-Men', 1)

insert into seats (id, reserved, row_name, seats_id) values (1, 2, 'A', 1)
insert into seats (id, reserved, row_name, seats_id) values (2, 0, 'B', 1)
insert into seats (id, reserved, row_name, seats_id) values (3, 0, 'C', 1)

insert into price(id, type, price) values (1, 'normal', 25)
insert into price(id, type, price) values (2, 'student', 18)
insert into price(id, type, price) values (3, 'child', 12.5)

insert into discount values ('ABC')