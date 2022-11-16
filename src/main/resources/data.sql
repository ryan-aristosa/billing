INSERT INTO BILLING
    (ID, ACCOUNT_ID, ACCOUNT_NAME, AMOUNT, TYPE)
VALUES
    (10001, 1001, 'Ryan', 100.0, 'PAPER'),
    (10002, 1002, 'Baqui', 150.0, 'ELECTRONIC'),
    (10003, 1003, 'Justin', 450.0, 'ELECTRONIC'),
    (10004, 1004, 'Zoro', 250.0, 'ELECTRONIC'),
    (10005, 1001, 'Ryan', 150.0, 'PAPER'),
    (10006, 1005, 'Robin', 600.0, 'PAPER'),
    (10007, 1005, 'Robin', 170.0, 'PAPER'),
    (10008, 1006, 'Chopper', 430.0, 'ELECTRONIC'),
    (10009, 1003, 'Justin', 2500.0, 'ELECTRONIC'),
    (10010, 1007, 'Mugiwara', 5000000.0, 'PAPER'),
    (10011, 1004, 'Zoro', 80000.0, 'PAPER'),
    (10012, 1002, 'Baqui', 9000.0, 'ELECTRONIC'),
    (10013, 1008, 'Trafalgar', 20000.0, 'ELECTRONIC'),
    (10014, 1001, 'Ryan', 3000.0, 'PAPER'),
    (10015, 1009, 'Boa', 1500000.0, 'PAPER');

INSERT INTO USER_ENTITY
    (id, username, password)
VALUES
    (1, 'Ryan123', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu'),
    (2, 'Baqui123', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu'),
    (3, 'Justin123', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');