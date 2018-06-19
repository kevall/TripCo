# Sprint 5 - *T16* - *Off the (ASCII) Chart*

## Goal

### Reliable first release with clean code!

## Definition of Done

* Ready for demo / customer release.
* Sprint Review and Restrospectives completed.
* Product Increment release `v5.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>5.0.0</version>`.
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


## Metrics

Statistic | Planned | Completed
--- | ---: | ---:
Tasks |  24   | 24
Story Points |  48  | 48 


Statistic | Start | End
--- | ---: | ---:
Overall Test Coverage | 31% | 48% 
Smells | 15 | 11 
Duplication | 10 | 0 
Technical Debt Ratio | 18.1% | 2% 

## Plan

Epics planned for this release.

* Test Coverage #280
* Destination Selection #169
* Optimization & Selection #170
* Google Maps API #282
* Clean Code #281

## Daily Scrums

Date | Tasks done now | Tasks done next | Impediments | Coverage | Smells | Duplication | Technical Debt Ratio
:--- | :--- | :--- | :--- | ---: | ---: | ---: | ---:
*date* | *issue numbers only* | *issue numbers only* | *High* | *#* | *#* | *#* | *%*
11/15 | None | 291 | Medium | 31% | 15 | 10 | 18.1%
11/17 | 291 | 186 | Medium | 31% | 15 | 10 | 18.1%
11/27 | 291 | 186 | High | 31% | 15 | 10 | 18.1%
11/29 | 186 | 88, 187, 188 | High | 31% | 15 | 10 | 18.1%
12/1 | 88, 187, 188 | 295 | High | 31% | 15 | 10 | 18.1%
12/4 | 295 | 276, 277, 296, 297, 298, 299 | High | 31% | 15 | 10 | 18.1%
12/6 | 276, 277, 296, 297, 298, 299 | Remaining | High | 38% | 15 | 10 | 18.1%
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* Test Coverage
* Optimization & Selection: This was left over from Sprint 4 
* Google Maps API
* Clean Code 

#### Incomplete user stories / epics in Sprint Backlog 
* Destination Selection: This was left over from Sprint 4, but we couldn't get "reorder destinations"
* Select Starting Location
* Mobile Capabilities (optional)
* Fast Response (optional)
* Continuous Delivery (optional)

#### What went well
* We were able to go over and refine our code to make it look better.
* The tests we added helped to improve our accuracy and stats in code climate.
* We were able to add features that we didn't have in the last sprint.

#### Problems encountered and resolutions
* We had a hard time figuring out how to get 3-opt and the map working.

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | We will focus on epics that we individual do well at | We'll update the scrum report as we go | We will use IntelliJ and Code Climate extensively to improve our code coverage and readability
What we did well | Our prioritization of specific epics helped us get them done simultaneously | Following zenhub and dealing with premade issues worked well | We used Code Climate to review the status of our code more often
What we need to work on | Find time to meet more often during the sprint | Make sure that we update scrum page as we go | Use Code Climate and IntelliJ even more to review our code's status
What we will change next time | We will work on epics as a whole team | We will move issue by issue in zenhub, and update our logs each scrum respectively | Use Code Climate to report the status of our code each scrum
