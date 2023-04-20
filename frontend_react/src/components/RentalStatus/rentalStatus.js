import React from "react";

const rentalStatus = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped table-primary text-center"}>
                        <thead className={"table-dark"}>
                        <tr>
                            <th scope={"col"}>Rental Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Array.isArray(props.rentalStatus) && props.rentalStatus.map((term) =>{
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

export default rentalStatus;