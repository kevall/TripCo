import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';
import Map from  './Home/Map.jsx';
import Result from './Result/Result.jsx';

export default class App extends React.Component {
    constructor(props) {
        super(props);                                   
        this.state = {
            columns: [], //all possible column headers
            selectedColumns: [], //columns chosen to display in the table
            total: 0, //total distance of the trip
            allPairs: [], //represents every Leg that will be displayed in the table
            selectedID: [], //IDs of selected locations
            selectedNames: [], //names of selected locations
            allResults: [] //all locations returned from a search as Results
        }
    };

    render() {
        let rs = [];
        for(let i = 0; i < this.state.allResults.length; i++) {
            let r = <Result 
            			resultName = {this.state.allResults[i].resultName} 
            			resultID = {this.state.allResults[i].resultID} 
            			resultSelect = {this.resultSelect.bind(this)}
            		/>;
            rs.push(r);
           	console.log("HERE");
        }
        return (
            <div className="app-container">
                <Home
                	//This section binds the variables in app.js to the corresponding variables in Home.jsx
                    columns = {this.state.columns}
		    		totaldistance = {this.state.total}
                    pairs = {this.state.allPairs.map((pp) => {return <Pair {...pp}/>;})}
                    selectedID = {this.state.selectedID}
                    selectedNames = {this.state.selectedNames}
                    allResults = {rs}
                    numResultsMessage = {this.state.numResultsMessage}
					//This section binds the methods in app.js to the corresponding methods in Home.jsx
                    showSearchResults = {this.showSearchResults.bind(this)}
                    selectAll = {this.selectAll.bind(this)}
                    removeAll = {this.removeAll.bind(this)}
                    selectColumns = {this.selectColumns.bind(this)}
                    setFields = {this.setFields.bind(this)}
                    displaySelected = {this.displaySelected.bind(this)}
                    browseFile = {this.browseFile.bind(this)}
                />
            </div>
        )
    }

	//method bound to Result.jsx that allows for location selection 
    async resultSelect(Name, value, checked) {
        let selectedID = this.state.selectedID;
        let selectedNames = this.state.selectedNames;
		if(checked && !selectedID.includes(value)) {
			selectedID.push(value);
        	selectedNames.push(Name);
		}
		if(!checked) {
			selectedID.splice(selectedID.indexOf(value),1);
			selectedNames.splice(selectedNames.indexOf(Name),1);
		}
        this.setState({
            selectedID: selectedID,
            selectedNames: selectedNames
        });
        console.log("selected IDs: ", selectedID);
    }
    
	//turns all locations returned from the search into Results and collects them in this.state.allResults
	async showSearchResults(jsonResults) {
        console.log(jsonResults);
        let results = jsonResults.locations;
        let resultsArray = []
        for (let i = 0; i < results.length; i++) {
            //creates a Result for every Leg
            let r = { //this corresponds to the structure of Result
                resultID: results[i].Code,
                resultName: results[i].Name
            };
            resultsArray.push(r);
        }
        let Message = "Search returned " + resultsArray.length + " results";
        this.setState({
        	allResults: resultsArray,
        	numResultsMessage: Message
        });
        console.log("allResults: ", this.state.allResults);
    }   

    async selectAll() {
		let selectedID = this.state.selectedID;
		let selectedNames = this.state.selectedNames;
    	for(let i = 0; i < this.state.allResults.length; i++) {
    		selectedID.push(this.state.allResults[i].resultID);
    		selectedNames.push(this.state.allResults[i].resultName);
    	}
    	this.setState({
            selectedID: selectedID,
            selectedNames: selectedNames
        });
        console.log("selected IDs: ", selectedID);
    }

    async removeAll() {
    	this.setState({
            selectedID: [],
            selectedNames: []
        });
        console.log("selected IDs: ", this.state.selectedID);
    }
    
	//populates this.state.columns with all possible column-header names, which are displayed in a selection box on the webpage
    async selectColumns(locations) { 
        let headers = [];
        let options = [];
        let startInfo = locations[0].startInfo;
        console.log("startInfo: ", startInfo);
		for (let i = 0; i < startInfo.length; i++) {
			headers = startInfo[i].split(":");
        	options.push(headers[0]);
        }
        console.log("columns: ", options);
		this.setState({	columns: options }); //this.state.columns now contains all column headers
     }

	//takes the selected columns received from Home.jsx and adds them to this.state.selectedColumns
	async setFields(array) {
		console.log("selected columns: ", array);
		this.setState({selectedColumns: array[array.length-1].split(",")});
	}

	//builds pairs with the selected locations to be displayed in Home.jsx and adds them into this.state.allPairs
	//uses this.state.selectedColumns to filter information displayed
    async displaySelected(locations) {
        console.log("Got file:", locations);
        let pairs = [];
        let distance = 0; //total trip distance
		//for every Leg...
        for (let i = 0; i < locations.length; i++) {
            //save the following information:
			let startName = locations[i].startName;
			let startLatitude = locations[i].startLatitude;
			let startLongitude = locations[i].startLongitude;
			let startInfo = locations[i].startInfo;
			let endName = locations[i].endName;
			let endLatitude = locations[i].endLatitude;
			let endLongitude = locations[i].endLongitude;
			let endInfo = locations[i].endInfo;
            let dist = locations[i].distance; //the distance for this Leg
            distance += locations[i].distance; //add to the running total distance
			let startinfo = ""; //will contain all "other" data fields of the starting location
			let endinfo = ""; //will contain all "other" data fields of the ending location
			//append selected column information to startinfo to be displayed in the table
			for(let i = 0; i < startInfo.length; i++){
				let headers = startInfo[i].split(":");
				if(this.state.selectedColumns.includes(headers[0])) {
					startinfo += headers[0] + ": " + headers[1];
					if(headers.length === 3) { startinfo += headers[2]; }
					startinfo += " ";
				}
			}
			//append selected column information to endinfo to be displayed in the table
			for(let i = 0; i < endInfo.length; i++){
				let headers = endInfo[i].split(":");
				if(this.state.selectedColumns.includes(headers[0])) {
					endinfo += headers[0] + ": " + headers[1];
					if(headers.length === 3) { endinfo += headers[2]; }
					endinfo += " ";
				}
			}
			//creates a pair object for every leg
            let p = { //this corresponds to the structure of Pair
                startname: startName,
                endname: endName,
                dist: dist,
                total: distance,
                startInfo: startinfo,
                endInfo: endinfo
            };
            pairs.push(p); //add new Pair to pairs[]
        }
        //Update the state of app; any component referencing allPairs will be re-rendered
        this.setState({
        	total: distance,
            allPairs: pairs, //this.state.allPairs now contains all Pair objects corresponding to every Leg
        });
    }

	//method called when loading a saved trip
    async browseFile(file) {
        console.log("Got file:", file);
        let destinationsList = [];
        for (let i = 0; i < file.destinations.length; i++) {
	        destinationsList.push(file.destinations[i]);
        }
		this.setState({ 
			selectedNames: destinationsList,
			selectedID: destinationsList
		});        
    }
} 
