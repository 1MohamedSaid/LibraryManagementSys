# Spring boot Library Management System Api.

## Models and Database:
*Books and Patrons with borrowing records.
*Crud(add, update, delete).
*MySQL database.

## How to use:
-This API is JWT authentication protected, you need to manually disable JWT authentication by commenting out
the security filter part in SecurityConfig class and add "/api/patrons" in the requestMatchers.permitAll() line.

-Now you can add a patron that can act as an admin after JWT authentication is enabled
by uncommenting the security filter part in SecurityConfig class, and remove the "/api/patrons" part
from the requestMatchers.permitAll() line for properly utilizing JWT authentication.

## How to login to the api:
-POST: "/api/login" --> logs in to the database using username and password,
                        returning a JWT token to be used in further api calls.

## CRUD Operations:
###### Book API endpoints:
* GET: "/api/books" --> returns the list of books in library.
* GET: "/api/books/{id}" --> returns the book with the specified id.
* POST: "/api/books" --> adds a book to the library database.
* PUT: "/api/books/{id}" --> updates a book's information with the specified id.
* DELETE: "/api/books/{id}" --> deletes a book with the specified id.

###### Patron API endpoints:
-GET: "/api/patrons" --> returns the list of patrons in the database.
-GET: "/api/patrons/{id}" --> returns the patron with the specified id.
-POST: "/api/patrons" --> adds a patron to the database.
-PUT: "/api/patrons/{id}" --> updates a patrons's information with the specified id.
-DELETE: "/api/patrons/{id}" --> deletes a patron with the specified id.

###### Borrowing record API endpoints:
-POST: "/api/borrow/{bookId}/patron/{patronId}" --> specified patron borrows the specified book from library.
-PUT: "/api/return/{bookId}/patron/{patronId}" -->returns the borrowed book by the specified patron to the library.





