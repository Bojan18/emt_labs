import React from "react";
import {Link} from "react-router-dom";

const books = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-hover table-primary text-center"}>
                        <thead className={"table-dark"}>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Category</th>
                            <th scope={"col"}>Author</th>
                            <th scope={"col"} >Available copies</th>
                            <th scope={"col"}>Delete/Edit Book</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.books.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.category}</td>
                                    <td>{term.author.name}</td>
                                    <td>{term.availableCopies}</td>
                                    {/*<td scope={"col"} className={"text-right"}>*/}
                                    {/*    <a title={"Delete"} className={"btn btn-danger"}*/}
                                    {/*    onClick={() => props.onDelete(term.book_id)}>Delete</a>*/}
                                    {/*</td>*/}
                                    <Link className={"btn btn-danger ml-2"}
                                          onClick={() => props.onDelete(term.book_id)}
                                          to={"/books"}>
                                        Delete
                                    </Link>
                                    <Link className={"btn btn-dark ml-2"}
                                          onClick={() => props.onEdit(term.book_id)}
                                          to={`/books/edit/${term.book_id}`}>
                                        Edit
                                    </Link>

                                    <Link className={"btn btn-info ml-2"}
                                    onClick={()=> props.onRent(term.book_id)}>
                                        Mark as Taken
                                    </Link>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                    <div className={"col mb-3"}>
                        <div className={"row"}>
                            <div className={"col-sm-12 col-md-12"}>
                                <Link className={"btn btn-success"} to={"/books/add"}>
                                    Add new book
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default books;