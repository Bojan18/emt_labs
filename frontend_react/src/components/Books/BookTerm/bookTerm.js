import React from "react";
import {Link} from "react-router-dom";
const bookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name + " " + props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                {/*<a title={"Rent"} className={"btn btn-success ml-2"}*/}
                {/*   onClick={() => props.onRent(props.term.id)}>*/}
                {/*    Rent*/}
                {/*</a>*/}

                <Link className={"btn btn-danger ml-2"}
                      onClick={() => props.onDelete(props.book_id)}
                      to={"/books"}>
                    Delete
                </Link>

                <Link className={"btn btn-dark ml-2"}
                      onClick={() => props.onEdit(props.book_id)}
                      to={`/books/edit/${props.book_id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    );
}


export default bookTerm;