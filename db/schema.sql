CREATE TABLE Employees (
  id SERIAL PRIMARY KEY,
  name text,
  salary REAL
);

INSERT INTO Employees(name, salary) VALUES ('John Mayer', 2000.0);