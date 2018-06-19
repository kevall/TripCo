import React, {Component} from 'react';

let Pair = ({startname, endname, dist, total, startInfo, endInfo}) => <tbody
    className="pair">
    <tr>
        <td>
            <h5>{startname}</h5>
            <p>{startInfo}</p>
        </td>
        <td>
            <h5>{endname}</h5>
            <p>{endInfo}</p>
        </td>
        <td>
            <h5>{dist}</h5>
        </td>
        <td>
            <h5>{total}</h5>
        </td>
    </tr>
</tbody>;

export default Pair;
