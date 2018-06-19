import React, {Component} from 'react';
import "../Home/Pair/Pair.scss"

export default class Result extends React.Component {

    constructor(props) {
        super(props);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
    	console.log("event.target.clicked: ", event.target.checked);
        this.props.resultSelect(event.target.name, event.target.value, event.target.checked);
        console.log(event.target.value);
        console.log(event.target.checked);
    }

    render() {
        let resultName = this.props.resultName;
        let resultID = this.props.resultID;
        return (
            <tbody className="pair">
        		<tr>
            		<td>
            		    <input name = {resultName} value = {resultID} type = "checkbox" onChange = {this.handleInputChange} />
            		</td>
            		<td>
                		<h5>{resultName}</h5>
            		</td>
        		</tr>
        	</tbody>
    	);
    }
}
