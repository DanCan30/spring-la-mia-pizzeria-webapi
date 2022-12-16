console.log("API test")

fetch("http://localhost:8080/api/1/all")
.then(response => response.json())
.then(data => console.log(data));