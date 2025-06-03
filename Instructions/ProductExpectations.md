# Final Project Expectations

The final product you are building is an application that focuses on managing a 'collection of items'. For example, you can build an application that manages recipes allowing clients to add, edit, delete, and view recipes. You can also build an application that manages a collection of books, movies, or any other item you can think of. Other examples that are also valid, but not as direct are: an application that manages inventory in a story, or an application that manages fishes in an aquarium.  You can also manage digital assets such as stocks and trades, so there are many options for this project.

However, in order to keep the projects similar, we are going to have a set of requirements that all projects must meet, and a set of requirements that vary based on the size of the project group. This document highlights both, along with how we will grade your final product code and design documents. 


## Documentation Requirements

All projects must have the following documentation:

* UML Class Diagrams for the initial and final design of the project. To be stored in the [../DesignDocuments/](../DesignDocuments/) directory. You should also include any additional files you used for design, such as mockups of your interface, etc. 
  
* Fully documented methods/classes (all code) using Javadoc format
  * While generating the webpages java doc is not required, you are welcome to. You should also store them in the design documents directory. 

* An application "manual" that highlights how to use the application, ideally with screen shots. The manual should be stored in the [../manual](../manual) directory.

* A README.md file that includes the following information:
  * The name of the project
  * The names of the team members
  * A brief description of the project
  * How to compile and run the project
  * Any other information you think is relevant
    * Essentially, if we can read the README.md file, can we figure out where everything is stored. So you probably want links to the manual and design documents.
  * The README.md file should be stored in the root directory of the project.


## Testing and Style Requirements

* All code must be tested using JUnit tests.
* The tests need to be broken up into logical groupings, so that it is easy to determine what is being tested.
* For style, you should follow the same guidelines that have been used in the previous projects.
* If you wish to run your own [style check](https://checkstyle.org/), use the [../checkstyle-rules.xml](../checkstyle-rules.xml) file as your basis. 
* Your code **MUST** follow good design patterns, proper use of inheritance, and your code should be DRY (Don't Repeat Yourself)


## Feature Requirements for all projects

A bare minimum, all projects need to have some aspect of the following components:


* Graphical User Interface (Can be a website or java swing/awt)
* Be able to view all items in the collection - in a logical order
* Be able to build a list of items from the collection
* Be able to save out that list using a file format we covered in the course such as .xml, .json, or .csv.

### Additional/Optional Features
With that said, there are a number of other features you may want to add to your project. Here are some ideas (You  may have others)

* Be able to load in lists of items / previously saved lists, and modify them.
* Be able to search for items in the collection
* Be able to sort items in the collection
* Be able to filter items in the collection
* Have your original item list come from an online API/online access 
* Include images for your items
* Have the ability to modify a local copy of an item, and those modifications remain persistent across sessions.
* If your items have geographic data, be able to display them on a map.
* If your items have temporal data, be able to display them on a timeline.
* If your items have numeric data, be able to display them in a graph.

> [!TIP]
> The extra features can vary greatly depending on the project you select! We encourage you to think and plan in cycles. Give yourself goals on which features you are going to implement, and then work together on making the system works with the added feature before the next feature is added. This will help you keep on track and not get overwhelmed by the project.

### Group Size vs. Features to Implement

The rubric below defines you have to implement a number of features based on your group size. This helps manage the larger groups against the smaller groups. Here are the group differentiations:

* Group of 3 
  * To meet expectations, you must implement all required features, plus one more. 
  * To exceed expectations, you must implement all required features, plus three more.
* Group of 4
  * To meet expectations, you must implement all required features, plus two more.
  * To exceed expectations, you must implement all required features, plus four more.
* Group of 5
  * To meet expectations, you must implement all required features, plus three more.
  * To exceed expectations, you must implement all required features, plus five more.

  
> [!WARNING]
> It is up to the team to present the additional features both in clear documentation, and in the demonstration. If we cannot see/easily find the additional features, we cannot grade them.


#### Comments on a GUI

You are free to use Java Swing/AWT or if you are feeling adventurous, using the Spring framework to create a web application. If you are using Java Swing/AWT, you should have a GUI that is easy to use and understand. If you are using Spring, you should have a web application that is easy to navigate and use. So no matter what you choose, make sure your GUI is user friendly and easy to use.


#### Databases?
You may be tempted to use a database especially if you are using Spring. You should NOT use one for this project. We specifically want to see how you handle files, and databases are something you learn in a later course. Assume the project (even if a website) is always being run locally. 


## Getting Started

You will be using a team repository for this project. In the canvas assignment, you will be given a link that will generate the repository. However, unlike individual assignments, you will see the option to create a team. You should create a team with your group members ** if the team doesn't already exist **, or just join the already created team.

Team names should *MATCH* group names! So Group 1, Group 2, etc. 

> [!CAUTION]
> You need to be careful on creating/joining teams! It is very difficult to switch you to a different team, and causes issues with github. So make sure you are in the right team before you start working on the project.

#### Github Flow

Github recommends the following flow when working on a group project.

1. Create a branch
2. Make changes
3. Create a pull request
4. Address review comments
5. Merge your pull request (ideally another team member does this)
6. Delete your branch

[Read More](https://docs.github.com/en/get-started/using-github/github-flow)

In either case, part of this projects involves a lot of communication with group members as you are all working on the same code base. While we won't enforce a flow, make sure to use the tools to your advantage. 

## Grading Rubric
The following rubric will be used for grading. As long as your github has the latest version, we will go there to grade your team. There is no submission on canvas (or gradescope) for the final project. Grading will start after the hard cut off date, and no submissions after that date will be accepted.

| Category                                                                        | Exceeds Expectations(4)                                                                                            | Meets Expectations (3)                                                                                                  | Approaching Expectations (2)                                                      | Learning Expectations (1)                                                                    |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| Code and Implementation                                                         | Code uses proper inheritance, is dry, follows good design patterns (MVC,etc)                                       | Makes use of inheritance and dry, but weak design pattern use                                                           | Uses inheritance, but not dry. Weak overall design                                | Code is poorly designed overall, overly complicated, repeats itself, doesn't make use of OOP |
| Conceptual Design (UML)                                                         | Pre/Post UML design is accurate, clear, and helps better understand the application                                | Either pre/post is clear, with minor mistakes                                                                           | One of the two UML diagrams is omitted, but one included as minor mistakes        | Diagram has heavy mistakes                                                                   |
| Testing                                                                         | Shows **FULL** test coverage, including edge cases, file formats, and documents how GUI was tested                 | Full code over, edge cases, but no documentation on how gui was tested                                                  | Full code coverage, missing some edge cases                                       | Lacks full code coverage, poor testing in general.                                           |
| Code Style                                                                      | Follows style guidelines including keeping fields private, public/protected methods, final classes, etc            | Follows most style guidelines, but may miss more nonstandard ones (private constructors in utility classes for example) | Missing key style elements, poor use of Strings vs enums, bad variable naming etc | Code is difficult to read due to bad style                                                   |
| Features Implemented (reminder, needs to be clear which features are implement) | Implements exceeds level of features for group size                                                                | Implements meets level of features for group size                                                                       | Only implements minimum required features                                         | Less than required features implemented                                                      |
| Documentation                                                                   | Full manual with screenshot, highlighting which features are implemented, full javadoc comments, readme filled out | Manual is confusing to read/find features, but javadoc is complete                                                      | Manual is missing or javadoc is missing.                                          | minimal javadoc.                                                                             |
