# Final Project for CS 5004 - (Free Game Library)
Project Name: Free Game Library

## Team Members

-  **Harish Nandha Kumar**: [GitHub Profile](https://github.com/HarishNandhaKumar)
-  **Kaden Casanave**: [GitHub Profile](https://github.com/kadencas)
-  **Ling Liu**: [GitHub Profile](https://github.com/linonion)
-  **Xing Liu**: [GitHub Profile](https://github.com/xingliu5210)
-  **Yuang LI**: [GitHub Profile](https://github.com/nononoononon)

## Description

This project retrieves information about free games from the API (https://www.freetogame.com/api)，It allows users to search for games, add or remove games from their list, sort the selected items based on user input, filter the game list accordingly, load different lists from the user's local storage, and export the data in various formats.


**each game items includes details such as:**
- "short_description"
- "thumbnail"
- "game_url"
- "release_date"
- "freetogame_profile_url"
- "genre"
- "publisher"
- "developer"
- "id"
- "title"
- "platform"

**Key Features:**
- **Search for games**: Users can search for games based on various criteria.
- **Add or remove games**: Users can add games to or remove games from their list.
- **Sort games**: Users can sort the selected game items based on different criteria provided by the user.
- **Filter games**: Users can filter the game list to display only those that match specific criteria.
- **Load game lists**: Users can load different game lists from their local storage.
- **Export game list**: Users can export the game list in various formats such as JSON, XML, or CSV.

### Design Documents and Manuals

- Design Document： [Design Document](link design doc)
- User Manual： [User Manual](Manual/README.md)

### How to Run the Application

##### Prerequisites:

- Java Development Kit (JDK)
```bash
    Version: 11 or higher
```
- Gradle
```bash
    Version: 7.4.2 or higher
```

##### Compilation and Running

- Build the project using Gradle:
```bash
    ./gradlew build
```
- Run the application:
```bash
    ./gradlew run
```

### Repository Structure

- `data/` - Directory for storing local list databases.
- `designDocuments/` - Directory containing design documents.
- `manuals/` - Directory containing user manuals.
- `src/` - Source code directory, includes:
  - `java/` - Contains the main application code, organized using the MVC pattern:
    - `controller/` - Contains the controller classes.
    - `view/` - Contains the view classes, including:
    - `model/` - Contains model classes, including:
      - `formatter/` - Classes for data formatting, including:
      - `net/` - Classes for network-related functionality.
      - Other model implementations:
        - `FreeGameItem.java`
        - `GamesDatabase.java`
        - `ItemModel.java`
        - `ItemModelImpl.java`
        - `Sorting.java`
  - `test/` - Contains test cases for the application.
- `build/` - Build output directory.
- `README.md` - This README file.


### Feature Implementation

We have implemented the following features as per the project requirements:

###### Bare Minimum Components
- **Graphical User Interface**: Implemented using Java Swing.
- **View All Items**: Users can view all items in the collection in a logical order.
- **Build a List**: Users can build a list of items from the collection.
- **Save the List**: Users can save the list using file formats such as .xml, .json, or .csv.

###### Additional/Optional Features
- **Load and Modify Lists**: Users can load previously saved lists of items and modify them.
- **Search for Items**: Users can search for items in the collection.
- **Sort Items**: Users can sort items in the collection based on various criteria.
- **Filter Items**: Users can filter items in the collection to display only those that match specific criteria.
- **Online API Access**: The original item list is fetched from the FreeToGame API.
- **Persist Modifications**: Users can modify a local copy of an item, with changes remaining persistent across sessions.
