# MyAwsomeDictionary
My Project for final test on semester 4

In this test, we need to use this API endpoint https://myawesomedictionary.herokuapp.com/words to retieve the dictionary data. The endpoint requires an additional query parameter q=[prefix] to specify the prefix of the words to search. For example, by adding value “ app” to the query parameter, the URL becomes: https://myawesomedictionary.herokuapp.com/words?q=app which will result in the following words: app and apple.
In the explore tab, we need to show the search result retrieved from the API. Each word may have multiple definitions and an image.
And then we need to implement a favorite feature in which you can save a word definition into a local database.
From the favorite tab, we can see our saved words with all the details and we can delete the data from the database.

For this project, i used 2 third party libraries, Volley to help me retrieving the result from the API and Piccaso to help with the image.
In the explore and favorite tab, i used a recycler view to list all the words and for the database, i used SQLite.
