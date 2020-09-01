-- TODO Create a table for Employee containing an ID, firstname, lastname and date of birth
CREATE TABLE EMPLOYEE
(
    ID              NUMBER(19) GENERATED BY DEFAULT AS IDENTITY,
    FIRST_NAME      VARCHAR2(100 CHAR),
    SECOND_NAME     VARCHAR2(100 CHAR),
    DATE_OF_BIRTH   DATE,
    COMPANY_ID      NUMBER(19),
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID)
);