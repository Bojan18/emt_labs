import React from "react";
import {Link} from "react-router-dom";

const categories = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped table-primary text-center"}>
                        <thead className={"table-dark"}>
                            <tr>
                                <th scope={"col"}>Category name</th>
                            </tr>
                        </thead>
                        <tbody>
                        {Array.isArray(props.categories) && props.categories.map((term) =>{
                            return (
                                <tr>
                                    <td>{term}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default categories;