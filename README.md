
Restaurants, Queries and Statistical Learning
===

This code is from an Assignment completed in December 2017 done with a partner. It deals with multiple aspects of software construction:
+ managing complex ADTs;
+ multithreading and the client-server pattern;
+ query parsing and execution.

It also touches upon rudimentary methods for statistical inference and learning, namely k-means clustering and least squares regression.

### Background

We worked with data from the [Yelp Academic Dataset](https://www.yelp.com/academic_dataset) in JSON format that contained information on some restaurants, reviews of the restaurants, and users who wrote those reviews.

First, we parsed the JSON data to create an in-memory database (a `YelpDB`) with all restaurants, users and reviews. Then, we wrote methods to statistically analyze the data using k-means clustering and least squares regression. K-means clustering was used to group the restaurants into clusters within a city based on their longitude and latitude. This ideally allows us to group restaurants that are in the same neighbourhood in a city without knowledge of the neighbourhoods in a city. This [visualization](http://tech.nitoyon.com/en/blog/2013/11/07/k-means/) is a good way to understand how the algorithm works. Least squares regression was used to predict what rating out of five a user might give to a new restaurant based on their reviews of other restaurants and the price rating of those other restaurants. 

The next part of the assignment was to implement a multi-threaded server (a server that can handle multiple connections at once) application, `YelpDBServer` that wraps a `YelpDB` instance. This server is able to get the JSON format information of any restaurant, review, or user in the YelpDB, or add a-------------------------------------------------------

### Part IV: Handling Simple Requests

The server should be able to handle some simple requests from a client that connects to it.

Here are five simple requests that you should implement:

+ `GETRESTAURANT <business id>`: To this request, the server should respond with the restaurant details in JSON format for the restaurant that has the provided business identifier. If there is no such restaurant then one should use the error message as above. (Note that the business is is not wrapped in `< >`. The use of `< >` is to indicate that the command should be followed by a required argument. So the request will look like this: `GETRESTAURANT h_we4E3zofRTf4G0JTEF0A` and this example refers to the restaurant Fondue Fred in the provided dataset.)
+ `ADDUSER <user information>`: This request is a string that begins with the keyword (in our protocol), `ADDUSER`, followed by user details in JSON, formatted as suited for the Yelp dataset. Since we are adding a new user the JSON formatted information will contain only the user's name. So the JSON string may look like this `{"name": "Sathish G."}`. The server should interact with the RestaurantDB to create a new user, generate a new userid (it does not have to be in the Yelp userid format, you can use your own format for new users), generate a new URL (although it is a dummy URL) and then acknowledge that the user was created by responding with a more complete JSON string such as:
  `{"url": "http://www.yelp.com/user_details?userid=42", "votes": {}, "review_count": 0, "type": "user", "user_id": "42", "name": "Sathish G.", "average_stars": 0}`. If the argument to the `ADDUSER` command is invalid (not JSON format, missing name, etc.) then the server would respond with the message `ERR: INVALID_USER_STRING`. Note that the server can create a new user if the argument to this command is a valid JSON string and has a field called `name` but also has other information (which can be ignored).
+ `ADDRESTAURANT <restaurant information>`: This command has structure similar to the `ADDUSER` command in that the JSON string representing a restaurant should have all the necessary details to create a new restaurant except for details such as `business_id` and `stars`. If the provided string is incomplete or erroneous , the error message should `ERR: INVALID_RESTAURANT_STRING`.
+ `ADDREVIEW <review information>`: The last simple command to implement is an `ADDREVIEW` command which has the same principle as the other commands. The possible error codes are `ERR: INVALID_REVIEW_STRING`, `ERR: NO_SUCH_USER` and `ERR: NO_SUCH_RESTAURANT`.

Remember that when multiple clients are making such requests to change the database you will need to deal with potential data races and other concurrency-related conflicts.

For any other errors in the requests, you can send an `ERR: ILLEGAL_REQUEST`.

### Part V: Structured Queries

The final part of this machine problem is to support structured queries over the database you have constructed. The request-response pattern will be handled by the `RestaurantDBServer` as was the case with "simple" requests earlier.

We would like to process queries such as "list all restaurants in a neighbourhood that serve Chinese food and have moderate ($$) price."

In our request-response model, the request would begin with the keyword `QUERY` followed by a string that represents the query.

A query string may be: `in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2`. This query string represents a query to obtain a list of Chinese and Italian restaurants in the Telegraph Avenue neighbourhood that have a price range of 1-2.

For the query string above, the server would respond with a list of restaurants in JSON notation. If no restaurants match the query (for any reason) then the server should respond with `ERR: NO_MATCH`. If no query string is sent or if the query is ill-formed then the response should be `ERR: INVALID_QUERY`.

The grammar for the query language looks something like this:

```
<orExpr> ::= <andExpr>(<or><andExpr>)*
<andExpr> ::= <atom>(<and><atom>)*
<atom> ::= <in>|<category>|<rating>|<price>|<name>|<LParen><orExpr><RParen>
<or> ::= "||"
<and> ::= "&&"
ineq ::= <gt>|<gte>|<lt>|<lte>|<eq>
<gt> ::= ">"
<gte> ::= ">="
<lt> ::= "<"
<lte> ::= "<="
<eq> ::= "="
<num> ::= [1-5]
<in> ::= "in" <LParen><string><RParen>
<category> ::= "category" <LParen><string><RParen>
<name> ::= "name" <LParen><string><RParen>
<rating> ::= "rating" <ineq><num>
<price> ::= "price" <ineq><num>
<LParen> ::= "("
<RParen> ::= ")"
```

### Grading Guidelines

We will grade your work using the following _approximate_ breakdown of the aspects of required.

We will use the following approximate rubric to grade your work:		

| Task | Grade Contribution |		
|:----|---:|
| Datatype Design    | 20%  |
| k-means Clustering | 20% |		
| Least Squares Regression | 20% |
| Database Implementation: Simple Requests | 20% |		
| Database Implementation: Structured Queries | 20% |

Functionality apart, we will evaluate your submissions carefully along the following dimensions:
+ code style (e.g., an A on Codacy);
+ clear specifications for methods;
+ implementation of unit tests (high test coverage and integration with Coveralls.io);
+ code-level comments as appropriate;
+ comments the indicate clearly what the representation invariants and abstraction functions are for the datatypes you create.

### Hints

- Use example code we have provided to implement a multi-threaded server.
- You can use a parser generator (such as ANTLR) for parsing queries (or roll your own).
- Consider using streams and parallel streams for some of the tasks.
- There are several easier tasks you can accomplish before focusing on structured queries. **Use your time wisely.**
- It is critical that your `build.gradle` file reflect any dependencies that your work may have (external libraries such as JSON parsers.) (You work will not be graded otherwise.)
- When you complete this assignment, you would have implemented an approximation of a relational database. In the relational database world, a row is analogous to an instance of a datatype while a table definition is analogous to a datatype definition.
- Ensure that you have Codacy and Coveralls set up early.
- Your Github repo should reflect contributions from both team members.

### Submission

1. Commit and push your work to Github.
2. Include a `Team.md` document where you indicate who worked on this assignment and how the work was divided up.
3. Add a comment in the text box on Canvas indicating that you have submitted the work and the names of the students in the pair.
