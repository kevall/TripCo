import React, { Component } from 'react';
import {GoogleMap, Polyline, withGoogleMap, withScriptjs} from 'react-google-maps';
import { compose, withProps } from "recompose"

class Map extends Component {
    constructor(props){
    super(props);
    }
    render() {
    
    let data = this.props.locations;
    let points = [];
    
 	console.log("data", data);

   for (var i = 0 ; i < data.length ; i++){
   console.log("data", data[i].startLatitude);
        points.push(new google.maps.LatLng(data[i].startLatitude, data[i].startLongitude))
    }

    points.push(new google.maps.LatLng(data[0].startLatitude , data[0].startLongitude ))
    
 
    
        return (
            { /*GoogleMap is an imported component from react-google-maps*/ },
            <GoogleMap
                defaultCenter={{lat: 0, lng: 0} /*Sets the default center for the map to start at */}
                defaultZoom={1 /* Sets the default zoom ie how much of the world is on the screen*/}
            >
            {/* Everything that is in between <GoogleMap> and </GoogleMap> get rendered onto the
                map. Polyline is an easy google library that draws lines from coordiates.*/ }
                <Polyline
                    visible={true /*Make sure the map is visable on screen*/}

                    path={points /* Set polyline path to the coordiates array*/}

                    options={{
                        /* This is a list of optional things line line color and line weight this does not
                        need to be included. See documentation for more options*/
                        strokeColor: '#750011',
                        strokeWeight: 4,
                    }}
                />
            { /*Close our GoogleMap*/}
            
            </GoogleMap>
        )
    }
}

const MyMapComponent = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyDrERG9T9t2GJVDcAghsVHNumm7PkWatQY&v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `400px` }} />,
    mapElement: <div style={{ height: `100%` }} />
  }),
  withScriptjs,
  withGoogleMap
  )(Map);



class WrappedMap extends Component{
    constructor(props){
    super(props);
    }
    render(){
	
        return(
            <div>
                <MyMapComponent locations = {this.props.legs}/>  
            </div>
        )
    }
}
export default WrappedMap
