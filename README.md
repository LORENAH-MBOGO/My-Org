# NewsPortalAPI
This is a rest REST API for querying and retrieving scoped news and information

# Author
**  Lorenah M. **

# Running
Clone the repository or download it.

In PSQL:

SET MODE PostgreSQL;

CREATE DATABASE myorg_api;

CREATE TABLE IF NOT EXISTS news(
   id int PRIMARY KEY ,
   data varchar,
   type varchar,
   userId int,
   departmentId int
 );

CREATE TABLE IF NOT EXISTS users (
        userid int PRIMARY KEY ,
        username varchar,
        userposition varchar,
        userrole varchar,
        userdepartmentId int
     );

# Built With
Java
Spark 
Sql
Handlebars
Postgress DB


# Behaviour Driven Development
Allow user to add news.

The user is able to enter a new organization user.

The user is also able to enter a new department.

The user is able  to register when he viewed an animal and view all sightings afterwards.

# Development
Want to contribute? Great!

To fix a bug or enhance an existing module, follow these steps:

# Fork the repo

Create a new branch
Make the appropriate changes in the files
Add changes to reflect the changes made
Commit your changes
Push to the branch
Create a Pull Request.

# Known Bugs
No known bugs however if you find any bug kindly reach out.

# License
*MIT License

# Copyright (c) [2020] [Lorenah M.]

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
