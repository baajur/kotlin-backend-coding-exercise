INSERT INTO company (city, country, company_name, company_id) VALUES ('Dubai', 'UAE', 'Bayzat', 1)
INSERT INTO company (city, country, company_name, company_id) VALUES ('London', 'UK', 'Gemalto', 2)

INSERT INTO employee (employee_id, employee_name, phone_number, gender, date_of_birth, salary, company_id ) VALUES (1, 	'John', '+971555960195', 'MALE', '1990-06-06', 1500.0, 2)
INSERT INTO employee (employee_id, employee_name, phone_number, gender, date_of_birth, salary, company_id ) VALUES (2, 	'Kamal', '+971555960195', 'MALE', '1983-06-06', 1600.0, 2)

INSERT INTO dependant (dependant_id, dependant_name, phone_number, gender, date_of_birth, relation, employee_id ) VALUES (1, 	'Dependant1', '+971555960195', 'MALE', '1990-06-06', 'FATHER' , 2)
INSERT INTO dependant (dependant_id, dependant_name, phone_number, gender, date_of_birth, relation, employee_id ) VALUES (2, 	'Dependant2', '+971555960195', 'MALE', '1990-06-06', 'FATHER' , 2)
