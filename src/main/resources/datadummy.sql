INSERT INTO BOOK(id, author, isbn, status, title) VALUES(1, 'Ronald L. Rivest, dkk', '978-0262033848', 'NOT_SHELVED', 'Introduction to Algorithms, 3rd Edition (The MIT Press)');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(2, 'Kai-Fu Lee, dkk', '978-1328546395', 'NOT_SHELVED', 'AI Superpowers: China, Silicon Valley, and the New World Order');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(3, 'Ian Goodfellow, dkk', '978-0262035613', 'NOT_SHELVED', 'Deep Learning (Adaptive Computation and Machine Learning)');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(4, 'Gareth James, dkk', '978-1461471370', 'NOT_SHELVED', 'An Introduction to Statistical Learning: with Applications in R');
INSERT INTO BOOK(id, author, isbn, status, title) VALUES(5, 'Betsy Beyer, dkk', '978-1491929124', 'SHELVED', 'Site Reliability Engineering: How Google Runs Production Systems');
INSERT INTO SHELF(id, cur_capacity, max_capacity) VALUES(1, 2, 5);
INSERT INTO SHELF_BOOKS(shelf_id, books) VALUES(1, 1);
INSERT INTO SHELF_BOOKS(shelf_id, books) VALUES(1, 2);
INSERT INTO SHELF(id, cur_capacity, max_capacity) VALUES(2, 2, 5);
INSERT INTO SHELF_BOOKS(shelf_id, books) VALUES(2, 3);
INSERT INTO SHELF_BOOKS(shelf_id, books) VALUES(2, 4);

select * from book;
select * from shelf;
select * from shelf_books;