import React from "react";

const condition = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped table-primary text-center"}>
                        <thead className={"table-dark"}>
                        <tr>
                            <th scope={"col"}>Condition</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Array.isArray(props.condition) && props.condition.map((term) =>{
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

export default condition;