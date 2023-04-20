import axios from "../custom-axios/axios";

const BooksService = {
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    fetchCountries: () => {
        return axios.get("/countries")
    },
    fetchBooks: () => {
        return axios.get("/books")
    },
    fetchRentalStatus: () => {
        return axios.get("/rentalStatus")
    },
    fetchCondition: () => {
        return axios.get("/condition")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`)
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    addBook: (name, category, author_id, availableCopies, rentalStatus, condition) => {
        return axios.post("books/add", {
            "name" : name,
            "author_id" : author_id,
            "availableCopies" : availableCopies,
            "rentalStatus" : rentalStatus,
            "condition" : condition
        })
    },
    editBook: (id, name, category, author_id, availableCopies, rentalStatus, condition) => {
        return axios.put(`books/edit/${id}`, {
            "name" : name,
            "category": category,
            "author_id" : author_id,
            "availableCopies" : availableCopies,
            "rentalStatus" : rentalStatus,
            "condition" : condition
        })
    }
}

export default BooksService