SELECT E.first_name,E.hire_date,E.salary,D.department_name,J.job_title FROM Employees E,Departments D,Jobs J WHERE E.department_id=D.department_id=J.department_id AND E.salary>=4500 AND E.salary<1500;

SELECT E.employee_id,E.first_name,E.last_name,J.job_title,C.country_name FROM Employee E,Departments D,Jobs J,Location L,Countries C WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id AND J.job_id=E.job_id AND job_title='Accountant';

SELECT E.first_name,E.hire_date,E.salary,D.department_name,L.city FROM Employee E,Location L,Departments D WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND L.city='Mexico City' OR L.city='United States';

SELECT E.first_name,E.hire_date,E.salary,D.department_name,L.city FROM Empleado E,Departments D,Location L WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND D.department_name='SALES' AND L.city='United Kingdom';

SELECT E.first_name,E.last_name,J.job_title,R.region_id FROM Empleado E,Department D,Location L,Countries C,Region R WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id AND C.region_id=R.region_id AND J.job_id=E.job_id AND R.region_name='Americas' AND E.salary<(SELECT(AVG(E.salary) FROM Empleado WHERE E.department_id=D.department_id);

SELECT R.region_name,C.country_name,(SELECT COUNT(*) FROM Empleado E,Country C WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id) AS cantidad_emp_pais FROM Employee E,Location L,Countries C,Region R WHERE E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id AND C.region_id=R.region_id AND cantidad_emp_pais>3;

SELECT JH.start_date,JH.end_date,(SELECT EXTRACT('YEAR',TO_DATE(AGE(JH.end_date,JH.start_date))) AS ANIO*365+EXTRACT('MONTH',TO_DATE(AGE(JH.end_date,JH.start_date))) AS MES*30+EXTRACT('DAY',TO_DATE(AGE(JH.end_date,JH.start_date)))) FROM Employee E,Job_History JH,Departments D WHERE E.employee_id=JH.employee_id AND D.department_id=E.department_id AND D.department_name!='Executives';

SELECT E.first_name,E.employee_id,E.last_name,(SELECT COUNT(*) FROM Employees E WHERE E.manager_id=E.employee_id) as cant_sub FROM Empleado E WHERE cant_sub<8;



BEGIN 

DBMS_OUTPUT.PUT_LINE('El empleado',SELECT E.employee_name,E.lastname From Employees E,Job_History JH,Location L,Region R,Countries C,Departments D WHERE E.employee_id=JH.employee_id E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id AND C.region_id=R.region_id AND E.hire_date<1997 AND R.region_name='Europe');

DBMS_OUTPUT.PUT_LINE('ha trabajado por',(SELECT EXTRACT('YEAR',TO_DATE(AGE(JH.end_date,JH.start_date))) AS ANIO),(SELECT EXTRACT('MONTH',TO_DATE(AGE(JH.end_date,JH.start_date))) AS MES),(SELECT EXTRACT('YEAR',TO_DATE(AGE(JH.end_date,JH.start_date))) AS DIA) FROM Employees E,Job_History JH,Location L,Region R,Countries C,Departments D WHERE E.employee_id=JH.employee_id E.department_id=D.department_id AND L.location_id=D.location_id AND L.country_id=C.country_id AND C.region_id=R.region_id AND E.hire_date<1997 AND R.region_name='Europe'); 

END

SELECT J.job_title,JH.start_date,JH.end_date FROM Employees E,Job_History JH,Jobs J WHERE E.employee_id=JH.employee_id AND E.job_id=J.job_id;

SELECT avg(salary) AS mayor FROM Employees;