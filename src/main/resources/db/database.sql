SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS news(
    id int PRIMARY KEY auto_increment,
    data varchar,
    type varchar,
    userId int,
    departmentId int
 );

CREATE TABLE IF NOT EXISTS users (
        id int PRIMARY KEY auto_increment,
        name varchar,
        position varchar,
        role varchar,
        departmentId int
     );
CREATE TABLE IF NOT EXISTS departments(
        id int PRIMARY KEY auto_increment,
        name varchar,
        description varchar
 );
