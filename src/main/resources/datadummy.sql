INSERT INTO BOOK(id, author, isbn, status, title) VALUES(1, 'Rinaldi Munir', '9786021514917', 'SHELVED', 'Algoritma dan Pemrograman');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(2, 'Rinaldi Munir', '9786021514924', 'SHELVED', 'Metode Numerik');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(3, 'Budi Raharjo', '9786028759281', 'NOT-SHELVED', 'Pemrograman C');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(4, 'Budi Raharjo', '9786021514993','SHELVED','Visual Basic .Net');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(5, 'Budi Raharjo', '9786022334569','SHELVED','Algoritma dan Pemrograman');
INSERT INTO SHELF(shelf_id, current_capacity, max_capacity) VALUES(1, 2, 5);
INSERT INTO SHELF_BOOKS(shelf_shelf_id, books) VALUES(1, 1);
INSERT INTO SHELF_BOOKS(shelf_shelf_id, books) VALUES(1, 2);
INSERT INTO SHELF(shelf_id, current_capacity, max_capacity) VALUES(2, 2, 5);
INSERT INTO SHELF_BOOKS(shelf_shelf_id, books) VALUES(2, 3);
INSERT INTO SHELF_BOOKS(shelf_shelf_id, books) VALUES(2, 4);

select * from book;
select * from shelf;
select * from shelf_books;