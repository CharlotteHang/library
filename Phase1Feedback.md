### Phase 1 Feedback

#### General Comments
- looks like you are off to a very good start!

#### Checkstyle
- passes :-)

#### Code coverage
- 100% - great!

#### Tests
- all tests pass :-)


#### Documentation
- very good, just one thing to note: you state in a number of places
that a method will modify the file containing JSON data.  While it is 
certainly possible to update the file every time an object in the system is
mutated, we're imagining something a little less involved.  You will meet
the requirements for Phase 2 if the user can save the state of the application
to file just before quitting the program, and can then reload it when they
restart the application.


#### Implementation
- generally very good - just a couple of things to think about:
       
- now that we've learned how to override hashCode and equals, be sure
to replace Book.equals with an overridden implementation of Object.equals

- it looks like you are planning to mix the code that loads/saves the
JSON data in with the code in your model package.  Consider separating the
code that addresses these two different concerns in the system - this will
help to keep your model code more readable.  

          