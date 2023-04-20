import './App.css';
import {Component} from "react";
import React from "react";
import {Navigate, Route, BrowserRouter as Router, Routes, Link} from "react-router-dom";
import Authors from "../Authors/authors";
import Categories from "../Categories/categories";
import BooksService from "../../repository/booksRepository";
import Countries from "../Countries/countries";
import Books from "../Books/books";
import BookAdd from "../Books/BookAdd/bookAdd";
import Header from "../Header/header";
import RentalStatus from "../RentalStatus/rentalStatus";
import Condition from "../Condition/condition";
import BookEdit from "../Books/BookEdit/BookEdit";
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            categories: [],
            countries: [],
            rentalStatus: [],
            books: [],
            selectedBook: {}
        };
    }

    componentDidMount() {
        this.loadAuthors()
        this.loadCategories();
        this.loadCountries();
        this.loadBooks();
        this.loadRentalStatus();
        this.loadConditions();
    }

    render() {
        return(
            <div>
                {/*<Books books={this.state.books}></Books>*/}
                <Router>
                    <Header></Header>
                    <main>
                        <div className={"container"}>
                            <Routes>
                                <Route path={"/books"} exact element={<Books books={this.state.books}
                                                                             onDelete={this.deleteBook}
                                                                             onEdit={this.editBook}/>}></Route>
                                <Route path={"/books/add"} exact element={<BookAdd books={this.state.books}
                                                                                   authors={this.state.authors}
                                                                                   categories={this.state.categories}
                                                                                   rentalStatus={this.state.rentalStatus}
                                                                                   condition={this.state.condition}
                                                                                   onAddBook={this.addBook}/>}/>
                                <Route path={`/books/edit:id`} exact element={<BookEdit books={this.state.books}
                                                                                        authors={this.state.authors}
                                                                                        categories={this.state.categories}
                                                                                        rentalStatus={this.state.rentalStatus}
                                                                                        condition={this.state.condition}
                                                                                        onEditBook={this.editBook}
                                                                                        book={this.state.selectedBook}/>}/>
                                <Route path={"/authors"} exact element={<Authors authors={this.state.authors}/>}></Route>
                                <Route path={"/countries"} exact element={<Countries countries={this.state.countries}/>}></Route>
                                <Route path={"/categories"} exact element={<Categories categories={this.state.categories}/>}></Route>
                                <Route path={"/rentalStatus"} exact element={<RentalStatus rentalStatus={this.state.rentalStatus}/>}></Route>
                                <Route path={"/condition"} exact element={<Condition condition={this.state.condition}/>}></Route>
                            </Routes>
                        </div>
                    </main>
                </Router>
            </div>
        );
    }

    loadAuthors = () => {
        BooksService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadCategories = () => {
        BooksService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadRentalStatus = () => {
        BooksService.fetchRentalStatus()
            .then((data) => {
                this.setState({
                    rentalStatus: data.data
                })
            });
    }

    loadConditions = () => {
        BooksService.fetchCondition()
            .then((data) => {
                this.setState({
                    condition: data.data
                })
            });
    }

    loadCountries = () => {
        BooksService.fetchCountries()
            .then((data) =>{
                this.setState({
                    countries: data.data
                })
            });
    }

    loadBooks = () => {
        BooksService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    deleteBook = (id) => {
        BooksService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, author, availableCopies, rentalStatus, condition) => {
        BooksService.addBook(name, category, author, availableCopies, rentalStatus, condition)
            .then(() => {
                this.loadBooks();
            });
    }
    getBook = (id) => {
        BooksService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                });
            })
    }

    editBook = (book_id, name, category, author_id, availableCopies, rentalStatus, condition) => {
        BooksService.editBook(book_id, name, category, author_id, availableCopies, rentalStatus, condition)
            .then(() => {
                this.loadBooks();
            })
    }

}

export default App;
