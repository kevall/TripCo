# Sprint 2 - *T16* - *Off the (ASCII) Chart*

## Goal

### A shorter trip with a better itinerary.

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release created on GitHub with appropriate version number and name, a description based on the template, and a JAR file containing the executables to be used for the demo. 
* Unit tests for all new features and public methods at a minimum.
* Clean continuous integration build/test on master branch.
* No outstanding branches, commits, pull requests.

## Policies

* Master is never broken.  If broken, it is fixed immediately.
* Continuous integration always builds and tests successfully.
* Tests are written before/with code.  
* All changes are built and tested before they are committed.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the affected code.

## Plan 

User stories (epics) in the Sprint Backlog: *#8, #9, #10, #12, #11, #14*.  
* #45 select what information is displayed
* #44 map of our trip
* #43 shortest path
* #46 where should I get gas?
* #47 optional - fast outputs
* #48 where do I start?


Total planned tasks / issues in the Sprint Backlog: *11* 

## Daily Scrums

Date | Tasks done this time | Tasks done next time | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
*Sep 28* |  Maggie - Finished shortest distance; Hayley - Finished server!!! Spent 2 hours with TA working on it; Kevin - created method in apps to show map, added object to constructor, added line to render so home can get image from app; Jewett - worked on shortest distance |Hayley - put my code together with everyone elses; Kevin - display image on webpage; Maggie - test shortest distance; Jewett - ??? |Hayley - still not too confident in Javascript; Maggie - being pressed for time; Kevin - still not comfortable with Javascript; Jewett - ???|Hayley - low; Maggie - High; Kevin - High; Jewett - ???  
*Sep 27* |Maggie - wrote pseudocode for shortest distance; Hayley - Fixed model so last line of csv file makes it into info arraylist; Kevin - added writefile method and helper method for svg; Jewett - ??? |Maggie - write shortest distance; Kevin - meet with TA about method in home.jsx; Hayley - meet with TA for help with server; Jewett - ??? |Maggie - class all day today; Hayley - class and work; Kevin - javascript; Jewett - work | Maggie - medium; Kevin - High; Hayley - low; Jewett - ???
*Sep 25* |Maggie - made plan for shortest trip; Hayley - read/watched tutorials on react; Kevin - worked on map; Jewett - ??? |Maggie - start writing shortest trip; Hayley - I'm meeting with the TA. I have a list of questions; Kevin - I need to make a button in the render method in Home.jsx and create a new method in app that will perform calculations. I'm going to meet with the TA for clarification; Jewett - ??? |Maggie - Other homework to catch up on. I'm still not feeling 100%; Kevin - This is all *very* new to me; Hayley - Still pretty confused; Jewett - ??? | Maggie - low; Kevin - low; Hayley - low; Jewett - ???
*Sep 21* |Maggie - Brainstormed with Kevin about shortest trip; Kevin - I spent a lot of time looking over the assignment and trying to get a general idea of what each person will be doing and started thinking about how we could do it; Hayley - Looked over the user stories and epics on zenhub; Jewett - ??? |Maggie - Figure out how to do shortest trip. I realize now I didn't understand the original task; Hayley - rewrite some of the user stories/tasks so they're more clear; Kevin - Watch some tutorials on javascript; Jewett - ??? |Maggie - I'm really sick; Hayley - I'm still trying to get caught up on everything since my wedding last weekend; Kevin - I'm not familiar with javascript; Jewett - I'm confused with what my responsiblity is |Maggie - low; Hayley - medium; Kevin - medium; Jewett - ???

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *#62* cumulative distance
* *#43* shortest path
* *#45* column selection


#### Incomplete user stories / epics in Sprint Backlog 
Issues 50, 51 - We were not able to display the map on the web page.  We can create the map but we are not sure how to get home.jsx to recognize it.

#### What went well
We were able to create a drop down menu and display the selected information.  This was a tough epic and we are proud that we got it.

#### Problems encountered and resolutions
Our limited experience with javascript was a large problem.  We overcame it with help from the class TAs.

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time |We will set up a group meeting time outside of class that each of us will always attend unless advanced notice is given to the other group members.  |At times we collaborated on the same portion of code, but only one person committed it, so our work is not being accurately represented on GitHub. We will make sure our commits are more evenly-distributed and reflect the work done by each member.  | We have now seen some code for JavaScript. We can use HackerRank and other websites to better familiarize ourselves with this new language.
What we did well |The majority of our team communicated well and knew when/what others were working on.  | Our commits and github activity reflect our individual work.  | We acquired the skills necessary to implement the user stories. 
What we need to work on |We need to make sure that every team member is attending meetings, responding to slack/text messages, and doing the work that they are responsible for.  |We should start working earlier so that by the last week we are testing and polishing our code.  |Every team member should be learning the skills required for all parts so they can help others when needed.
What we will change next time |Each member will attend the sprint planning meeting and attend daily scrum meetings. We will use slack as our primary means of communication.  |We will start working and communicating sooner. We will make each member aware of any events that may impede progress. | At the start of the sprint, each member will walk the team through their work from the previous sprint.  
