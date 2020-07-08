This project is a library system.
It provides services for both the readers and the librarians. The readers can log into their own account,
borrow books, return books and see which books they have borrowed (with the due date of returning the book), or create
a new account if they use this system for the first time. The librarians can log into their own account, help the
readers return their books, see the list of books,add new books to the booklist and see the list of readers.

Phase 2:
In order to test that the Persistence and Parser work properly, I put the tests regarding these two into one test class.
(i.e. store data to json file, and then read the data, and finally test whether the original and parsed data are the same)

Phase 3:
1. The Observer design pattern is used. The readers can subscribe the books that they are interested in or unsubscribe
these books. Every time the book status is changed (i.e. the book is borrowed or returned), the readers who subscribe
this book will get corresponding notifications and they will see these notifications when they log into their account
(Only the latest MAX_Notifications number of notifications will be shown).
2. The Iterator design pattern is used in the BookList class and ReaderList class. Also, the iterator in BookList is designed
to get all the available books at first and then the books which have been borrowed.
