<html>
<body>
<h2>Books</h2>
 <form action="register" method="post">
        
        <label for="title">Book Title:</label>
        <input type="text" id="title" name="title" placeholder="Enter title" required>

        
        <label for="author">Task Author:</label>
        <input type="text" id="author" name="author" placeholder="Enter the name of the author" required>
        
       
        <label for="price">Price</label>
        <input type="number" id="price" name="price" required>

        
        <button type="submit">Register Book</button>
    </form>
    <a href="display-books">View All Books</a>
    
    
<form action="deleteBook" method="post">
    
    <label for="book_id">Book ID:</label>
    <input type="number" id="book_id" name="book_id" placeholder="Enter book ID" required>

   
    <button type="submit">Delete Book</button>
</form>
<form action="searchBooks" method="get">
   
    <label for="title">Search:</label>
    <input type="text" id="title" name="title" placeholder="Enter title" required>

  
    <button type="submit">Search</button>
</form>
</body>
</html>
