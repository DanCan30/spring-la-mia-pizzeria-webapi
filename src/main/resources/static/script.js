console.log("API test")

console.log("Books")
fetch("http://localhost:8080/api/1/book/all")
.then(response => response.json())
.then(data => console.log(data));

console.log("Pizzas")
fetch("http://localhost:8080/api/1/pizza/all")
.then(response => response.json())
.then(data => console.log(data));