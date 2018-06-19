# Sprint 4 - *T16* - *Off the (ASCII) Chart*

## Goal

### Worldwide trips!

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release `v4.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>4.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Coverage at least 50% overall and for each class.
* Clean continuous integration build/test on master branch.

## Policies

* Tests and Javadoc are written before/with code.  
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* Continuous integration always builds and tests successfully.
* All commits with more than 1 line of change include a task/issue number.
* All Java dependencies in pom.xml.

## Plan 

Epics in the Sprint Backlog: #167, #168, #169, #170 

Total planned tasks in the Sprint Backlog: 54

Total story points in the Sprint Backlog: 82

## Daily Scrums

Date | Tasks done this time | Tasks done next time | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
*10/25* | *No issues* | *#167, #168, #169, #170, #171, #178, #191* | *Add new issues for each epic* | *High*
*10/27* | *#171, #178, #191* | *#172, #203* | *Need to read about scaling SVG images and wait for instructions on the website transition.* | *High*
*10/30* | *#203* | *#173, #174, #175, #200, #202, #204, #215* | *Need to account for -180 to 180 and -90 to 90 .* | *High*
*11/1* | *#173, #174, #175, #200, #202, #204* | *#176, #177, #215, #179, #212* | *Fix bug in view class.* | *low*
*11/3* | *#176, #177, #179, #212* | *#172, #179, #180, #184, #203, #212, #215, #226* | *Speak with TA for help with View bug and website bug.* | *Medium*
*11/6* | *#172, #179, #180, #184, #203, #212, #215, #226* | *#213, #215, #217, #221, #225, #228, #236, #237, #214, #226* | *Not sure what the path is to provide the map and where, and the website still won't work.* | *High*
*11/8* | *#213, #215, #217, #221, #225, #228, #236, #237* | *Remaining Issues* | *Need to wait for feedback on how to fix website bug. How to incorporate destination selection and trip saving.* | *Medium*
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *#177*: *Our SQL database has been upgraded to include more airports, additional columns, and additional tables with related information. Search the entire database and make all relevant information from the database available for each destination.*
* *#214*: *A new background map is available.*
* *#175*: *The user is able to select kilometers or miles for distances in their itinerary.*
* *#168*: *Load the web application from a web site rather than the local file system so it can be accessed anywhere on the network.

Completed *number of issues completed* issues associated with these user stories.

#### Incomplete user stories / epics in Sprint Backlog 
* *#186*: *3-opt became a very complex issue that was a small addition to the functionality of the updated program, and so other epics had to be prioritized.*
* *#243*: *Make adjustments to accommodate multiple search words*
* *#183*: *Figure out how to reorder destinations*
* *#188*: *Create select box for desired optimization algorithm*
* *#187*: *Update Itinerary constructor to account for algorithm selection*

#### What went well
* *Great utilization of TA and online help to solve problems.*
* *Consistant use of zenhub an issues.*
* *Good communication and teammwork*

#### Problems encountered and resolutions
* *A Reference Error that prevented the website from loading it's content. Several files needed to be edited but the solution was found with the help of serveral TA's.*

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time |*Making sure we stay on the same page and communicate with everyone for every major change.* | *Create more tests and make sure our code is well tested prior to each merge.* | *Improve our use of Zenhub and slack, and incorporate IntelliJ more in our testing.*
What we did well | *All members were present and participated at all meetings* | *Each member made a lot more issues and pull requests. Daily scrum meetings were more productive*  | *We consistently used ZenHub and IntelliJ*
What we need to work on | *Dividing work more evenly*  | *Creating tests prior to writing methods*  | *Following code inspection checklists when reviewing pull requests, and understanding code climate*
What we will change next time | *Making sure each member is completing the same amount of issues/story points* | *Not accepting pull requests that don't include tests*  | *Updating daily scrum log after each meeting*
