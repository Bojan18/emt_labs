import React from "react";
import {useNavigate} from "react-router-dom";

const BookEdit = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: 0,
        author_id: 0,
        availableCopies: 0,
        rentalStatus:0,
        condition:0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim(),
        });
    };

    const onFormSubmit = (e) => {
        e.preventDefault();

        const name = formData.name !== "" ? formData.name : props.books.name;
        const category = formData.category !== "" ? formData.category : props.books.category;
        const author_id = formData.author_id !== 0 ? formData.author_id : props.books.author.id;
        const availableCopies = formData.availableCopies !== "" ? formData.availableCopies : props.books.availableCopies;
        const rentalStatus = formData.rentalStatus !== "" ? formData.rentalStatus : props.books.rentalStatus;
        const condition = formData.condition !== "" ? formData.condition : props.books.condition;

        props.onEditBook(props.books.book_id, name, category, author_id, availableCopies, rentalStatus, condition);
        navigate("/books")
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.books.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => (
                                <option key={term} value={term}>
                                    {term}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author_id" id="author_id" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if (props.books.author !== undefined &&
                                props.books.author.id === term.id)
                                    return <option selected={props.books.author.id === term.id}>{term.name + " " + term.surname}</option>
                                else
                                    return <option value={term.id}>{term.name + " " + term.surname}</option>
                                }
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.books.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>rentalStatus</label>
                        <select name="rentalStatus" className="form-control" onChange={handleChange}>
                            {props.rentalStatus.map((term) => (
                                <option key={term} value={term}>
                                    {term}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>condition</label>
                        <select name="condition" className="form-control" onChange={handleChange}>
                            {props.condition.map((term) => (
                                <option key={term} value={term}>
                                    {term}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default BookEdit