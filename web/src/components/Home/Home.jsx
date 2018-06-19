import React, {Component, PropTypes} from 'react';
import Dropzone from 'react-dropzone';
import Select from 'react-select';
import InlineSVG from 'svg-inline-react';
import WrappedMap from  './Map.jsx';


class Home extends React.Component {
	constructor(props) {
		super(props);
		this.state={
			units: [
				{ value: 1, label: 'miles (default)' },
				{ value: 0, label: 'kilometers' }
			],
			ops: [
				{ value: 0, label: 'None' },
				{ value: 1, label: 'Nearest Neighbor' },
				{ value: 2, label: '2-Opt' },
				{ value: 3, label: '3-Opt (default)' }
			],
			showMap: false,
			input : "",
			selectedCols: [], //contains all columns that have been selected at any given time
            serverReturned: null,
            miles: true,
            op: 3,
            message: "",
            legs: null
		}
	}

	//collects the selected columns for app.jsx
	logChange(val) {
		this.state.selectedCols.push(val);
		this.props.setFields(this.state.selectedCols);
		this.setState({val});
	}

	//sets the miles field based on selected units
	selectUnits(val) {
		console.log("units: ", val);
		if(val === 0) { this.setState({ miles: false }); }
		else if(val === 1) { this.setState({ miles: true }); }
		this.setState({val});
	}

	//sets the op field based on the selected optimization
	selectOp(value) {
		console.log("optimization: ", value);
		if(value === 0) { this.setState({ op: 0 }); }
		else if(value === 1) { this.setState({ op: 1 }); }
		else if(value === 2) { this.setState({ op: 2 }); }
		else if(value === 3) { this.setState({ op: 3 }); }
		this.setState({value});
	}

    render() {
    	var options = []; 
    	//console.log(this.props.columns);
    	for(var i=0; i<this.props.columns.length; i++) {
			var ob = new Object();
			ob.value = this.props.columns[i];
			ob.label = this.props.columns[i];
			options.push(ob);
    	}
        let total = this.props.totaldistance; //update the total here
        let serverLocations;
        let renderedSvg;

        // Once the server sends back an SVG, set the local variable "renderedSvg" to be the image
        if (this.state.showMap) {
            renderedSvg = <WrappedMap
				legs = {this.state.legs}/>;
        }

		let HTML = [];
        for(let i = 0; i < this.props.selectedNames.length; i++) {
        	let ele = <li>{this.props.selectedNames[i]}</li>;
        	HTML.push(ele);
        }
		console.log("HTML: ", HTML);
        return (
			<div className="home-container">
        		<div className="inner">
            		<h2>T16 - Off the (ASCII) Chart</h2>

					<div className="boxed">

		        		<form onSubmit={this.handleSubmit.bind(this)}>
		            		<input size="35" className="search-button" type="text"
		                    		onKeyUp={this.keyUp.bind(this)} placeholder="Enter a search term like denver" autoFocus/>
		            		<input type="submit" className="button button1"  value="Submit" />
		        		</form>

						<p> Example for exact match: ben schoeman airport </p>
						<p> Example for related: auckland, london </p>

					</div>

					<h3>OR</h3>
					
            		<Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    	<button className="button button1">Load saved trip</button>
                	</Dropzone>

                	<br />

					<h4> {this.props.numResultsMessage} </h4>

					<button className="button button1" onClick={this.selectAll.bind(this)}>Select All</button>

					<button className="button button1" onClick={this.removeAll.bind(this)}>Clear Selected</button>
					
					<h4> {this.state.message} </h4> 

                	{/* This is the checkbox list of locations to choose from */}
					<table className="pair-table">
                        {this.props.allResults}
					</table>

					{/* This is the selected locations */}
					<ul>{HTML}</ul>
					
					<div className="react-select-box">
						<Select
							options={this.state.units}
							onChange={this.selectUnits.bind(this)}
							placeholder="Choose distance units"
							multi={false}
							simpleValue = {true}
							searchable={false}
							backspaceToRemoveMessage=""
							value={this.state.val}
						/>		
					</div>

					<br />
					
					<div className="react-select-box">
						<Select
							options={this.state.ops}
							onChange={this.selectOp.bind(this)}
							placeholder="Choose trip optimization"
							multi={false}
							simpleValue = {true}
							searchable={false}
							backspaceToRemoveMessage=""
							value={this.state.value}
						/>		
					</div>
					
					<br />	
					<br />
					
					<button className="button button1" onClick={this.planTrip.bind(this)}>Plan</button>
					
					{this.props.options}

					<br />
					<br />

            		<button className="button button1" onClick={this.displayMap.bind(this)}>Click here for a map</button>
                        
            		<br />
            		<br />


					{/* Display the local variable renderedSvg. It is either null or an <svg> tag containing the image*/}
					<h1> {renderedSvg} </h1> 

        			<br />


            		<div className="react-select-box">
						<Select
							name="options"
							options={options}
							onChange={this.logChange.bind(this)}
							placeholder="Select additional information to display"
							multi={true}
							simpleValue f= {true}
							searchable={false}
							backspaceToRemoveMessage=""
							value={this.state.val}
						/>		
					</div>

					<button className="button button1" onClick={this.planTrip.bind(this)}>Show</button>
					
					<br />

					<button className="button button1" onClick={this.saveButtonClicked.bind(this)}>Save this trip</button>

            		<br />
            		
            		<table className="pair-table">
                		{this.props.pairs}
                		<tbody>
        	            	<tr>
        	            		<th colSpan="2">Total:</th>
        	            		<td>{total}</td>
        	           	 	</tr>    
						</tbody>
		 			</table>
				</div>
    		</div>
		)
    } 
    
    //calls fetch if enter is pushed for a search
    keyUp(event) {
        if (event.which === 13) { // Waiting for enter to be pressed. Enter is key 13: https://www.cambiaresearch.com/articles/15/javascript-char-codes-key-codes
            this.fetch("search", this.state.input); // Call fetch and pass whatever text is in the input box
        } else {
            this.setState({
                input: event.target.value
            });
        }
    }

	//calls fetch if the submit button is pushed for a search
    handleSubmit(event) {
        this.fetch("search", this.state.input);
        event.preventDefault();
    }

	//calls fetch if the "Click here for an SVG" button is clicked
    buttonClicked(event) {
        this.fetch("svg", event.target.value);
    }

	//calls fetch if the "Plan" or "Show" buttons are clicked
	planTrip(event) {
        this.fetch("select", this.props.selectedID);
        console.log("selected: ", this.props.selectedID);
    }

	//calls the saveFile method if the "Save this Trip" button is clicked
    saveButtonClicked(event) {
        this.saveFile();
    }

	selectAll(event) {
		this.setState({ message: "All locations have been selected" });
		this.props.selectAll();
	}

	removeAll(event) {
		this.setState({ message: "All selected locations have been removed" });
		this.props.removeAll();
	}
	    
    
    displayMap() {
        this.setState({showMap: true});
    }
    
    
    
    
    //This function sends 'input' to the server and updates the state with whatever is returned
    async fetch(type, input) {
        // Create object to send to server

        /*  IMPORTANT: This object must match the structure of whatever
            object the server is reading into (in this case ServerRequest).
            Notice how we give both requests the same format */
        let clientRequest;
        // if "enter" is pressed in the input box
        if (type === "search") {
            clientRequest = {
                request: "search",
                description: input,
                miles: this.state.miles,
                op: this.state.op
            };

            // if the button is clicked:
        } else if (type === "select") {
			clientRequest = {
                request: "select",
                description: input.toString(),
                miles: this.state.miles,
                op: this.state.op
            };
            console.log("input: ", input.toString());
        }

        else {
            clientRequest = {
                request: "svg",
                description: "",
                miles: this.state.miles,
                op: this.state.op
            }
        }
        try {
            // Attempt to send `clientRequest` via a POST request
            // Notice how the end of the url below matches what the server is listening on (found in java code)
            // By default, Spark uses port 4567
	    let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":6713/testing";
            let jsonReturned = await fetch(serverUrl,
                {
                    method: "POST",
                    body: JSON.stringify(clientRequest)
                });
            // Wait for server to return and convert it to json.
            let ret = await jsonReturned.json();
            console.log(ret);
            let returnedJson = JSON.parse(ret);
            // Log the received JSON to the browser console
            console.log("returnedJson: ", returnedJson);

            // if the response field of the returned json is "query", that means the server responded to the SQL query request
            if (returnedJson.response === "search") {
            	//console.log(returnedJson.locations);
            	this.props.showSearchResults(returnedJson);
            } 
            else if (returnedJson.response === "select") {

            	this.setState({ legs: returnedJson.legs });
          		this.props.selectColumns(returnedJson.legs);
            	this.props.displaySelected(returnedJson.legs);
            }
           

            // Print on console what was returned
            // Update the state so we can see it on the web
        } catch (e) {
            console.error("Error talking to server");
            console.error(e);
        }
    }

    drop(acceptedFiles) {
        console.log("Accepting drop");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj);
                   this.props.browseFile(JsonObj);
                };
            })(file).bind(this);
            fr.readAsText(file);
        });
    }

	//downloads a JSON file of the current trip (IDs only)
	async saveFile() {
        // create an object in the format of the download file:
        let locationFile = {
            title : "The cOoLeSt TrIp",
            destinations: this.props.selectedID
        };

		console.log("IDs to be written: ", this.props.selectedID);
		
        // stringify the object
        let asJSONString = JSON.stringify(locationFile);
        
        // Javascript code to create an <a> element with a link to the file
        
        let pom = document.createElement('a');
        pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(asJSONString));
        // Download the file instead of opening it:
        pom.setAttribute('download', "download.json");
        
        // Javascript to click the hidden link we created, causing the file to download
        if (document.createEvent) {
            let event = document.createEvent('MouseEvents');
            event.initEvent('click', true, true);
            pom.dispatchEvent(event);
        } else {
            pom.click();
        }
        
        // remove hidden link from page
        pom.parentNode.removeChild(pom);

    } 
}
export default Home
