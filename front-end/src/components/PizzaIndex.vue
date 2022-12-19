<template>
  
    <main>
        <h1>
            Pizzeria
        </h1>
        <button v-if="!createFormVisible" @click="createFormVisible = !createFormVisible">CREATE NEW PIZZA</button>
        <div v-else>
            <form @submit="createPizza">
                <label for="name">Name</label>
                <input type="text" name="name" v-model="newPizza.name">
                <label for="description">Description</label>
                <input type="text" name="description" v-model="newPizza.description">
                <label for="price">Price</label>
                <input type="number" name="price" v-model="newPizza.price">
                <input type="submit" value="CREATE">
                <button @click="createFormVisible = !createFormVisible, newPizza = {}">CANCEL</button>
            </form>
        </div>
        <label for="search">Search</label>
        <input type="text" name="search" @keyup.enter="searchPizza" v-model="searchQuery">
        <ul>
            <li v-for="pizza in pizzas" :key="pizza.id">
                <div v-if="editIndex != pizza.id">

                    {{ pizza.name }}
                    <button v-if="!pizza.ingredients" @click="getIngredients(pizza.id)">CHECK INGREDIENTS</button>
                    <button v-if="pizza.ingredients" @click="pizza.ingredients = null">HIDE INGREDIENTS</button>
                    <button @click="editIndex = pizza.id">EDIT</button>
                    <button @click="deletePizza(pizza.id)">DELETE</button>
                </div>
                <div v-else>
                    <form @submit="editPizza">
                        <label for="name">Name</label>
                        <input type="text" name="name" v-model="pizza.name">
                        <label for="description">Description</label>
                        <input type="text" name="description" v-model="pizza.description">
                        <label for="price">Price</label>
                        <input type="number" name="price" v-model="pizza.price">
                        <input type="submit" value="EDIT">
                        <button @click="editIndex = EDIT_DEFAULT">CANCEL</button>
                    </form>
                </div>
                <div v-if="pizza.ingredients">
                    <ul>
                        <li v-for="ingredient in pizza.ingredients" :key="ingredient.id">
                            {{ ingredient.name }}
                        </li>
                        <li v-if="pizza.ingredients != null && pizza.ingredients.length == 0">No ingredients found for this pizza</li>
                    </ul>
                    
                </div>

            </li>
        </ul>
    </main>

</template>

<script>

const API_URL = "http://localhost:8080/api/1/";
const EDIT_DEFAULT = -1;

import axios from "axios";

export default {
    name: "PizzaIndex",

    data: function() {
        return {
            pizzas: [],
            newPizza: {},
            editIndex: EDIT_DEFAULT,
            createFormVisible: false,
            showIngredients: false,
            searchQuery: "",
        }
    },

    methods: {

        findPizzaIndexById(id) {

            for(let i = 0; i < this.pizzas.length; i++) {
                
                const pizza = this.pizzas[i];

                if(pizza.id == id) return i;
            }
            return -1;
        },
        findPizzaById(id) {
            return this.pizzas[this.findPizzaIndexById(id)]
        },

        getAllPizzas() {
            axios.get(API_URL + "pizza/all")
            .then(result => {
                const pizzas = result.data;
                this.pizzas = pizzas;
            });
        },

        editPizza(e) {
            e.preventDefault();
            const pizzaIndex = this.findPizzaIndexById(this.editIndex);
            console.log(pizzaIndex);
            const pizza = this.pizzas[pizzaIndex];
            axios.post(API_URL + "pizza/edit/" + pizza.id, pizza)
            .then(result => {
                console.log(result.data)
            })
            this.editIndex = EDIT_DEFAULT;
        },

        createPizza(e) {
            e.preventDefault();

            const pizza = this.newPizza;
            axios.post(API_URL + "pizza/create", pizza)
            .then(result => {
                const newPizza = result.data;
                this.pizzas.push(newPizza);
            })

            this.createFormVisible = !this.createFormVisible;
            this.newPizza = {};
        },

        deletePizza(id) {
            const selectedPizza = this.findPizzaById(id);
            axios.post(API_URL + "pizza/delete/" + id)
            const selectedPizzaIndex = this.pizzas.indexOf(selectedPizza);
            this.pizzas.splice(selectedPizzaIndex, 1);
        },
        getIngredients(id) {
            axios.get(API_URL + "ingredient/pizza/" + id)
            .then(result => {
                const ingredients = result.data;
                this.findPizzaById(id).ingredients = ingredients;
            })
        },
        searchPizza() {


            if (this.searchQuery.trim() == "")
                this.getAllPizzas()
            else
                axios.get(API_URL + "pizza/search/" + this.searchQuery)
                .then(result => {
                        this.pizzas = result.data;
                }) 
        }

    },

    mounted() {
        this.getAllPizzas();
    }
}
</script>

<style>

</style>