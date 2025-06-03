##  Functions
    Required Features:

	1.	Graphical User Interface
        •	Utilize ResultsPanel, SearchFilterSortPanel, ImportExportPanel, and ItemDetailPanel classes to display and handle the user interface.

	2.	View All Items
	    •   Use the viewItems method of ItemController to fetch the list of items from ItemModel and display them in ResultsPanel.

	3.	Build a List of Items
        •	Use the buildList method of ItemController to select items from ResultsPanel and update the list in ItemModel.

	4.	Save List
        •	Use the saveList method of ItemController to call ItemModel and save the list.

    Optional Features:

	1.	Load and Modify Lists
	    •	Use the loadList method of ItemController to load the item list from a file and update ItemModel.
	2.	Search for Items
	    •	Use the searchItems method of ItemController to search for items from ItemSearchModel and display them in ResultsPanel.
	3.	Sort Items
	    •	Use the sortItems method of ItemController to sort items from ItemSearchModel and display them in ResultsPanel.
	4.	Filter Items
	    •	Use the filterItems method of ItemController to filter items from ItemSearchModel and display them in ResultsPanel.
	5.	Fetch Original Item List from Online API
	    •	Add methods in ItemModel to fetch data from an online API, and call ItemController to update ItemModel.
	6.	Include Item Images
	    •	Display item thumbnails in ResultsPanel and ItemDetailPanel.
        
	7.	Modify Local Item Copy
	    •	Use methods in ItemController to update items in ItemModel and persist the modifications.
	8.	Display Temporal Data

	    •	If items have temporal data, use timeline components in ItemDetailPanel to display them.

![demo](DesignDocuments/Ux_demo/demo.png)
## Timeline
### Week 1: Basic Methods Development

#### Goals:

	•	API Retrieval
	•	Display Everything in the List
	•	Filter the List

#### Tasks:

API Retrieval:

	1.	Create Item Class:
        •	Define the Item class with attributes:
        •	id: int
        •	title: String
        •	thumbnail: String
        •	short_description: String
        •	game_url: String
        •	genre: String
        •	platform: String
        •	publisher: String
        •	developer: String
        •	release_date: String
        •	freetogame_profile_url: String


	2.	Implement API Retrieval/ItemModel:
        •	Create methods in ItemModel to fetch data from the online API.
        •	Implement getItems() and addItem(item: Item) methods in ItemModel.

Display List:

	1.	Implement ItemModel:
        •	Implement buildList(selectedItems: List<Item>): void - Build a list.
        •	Implement saveList(fileFormat: String, filePath: String): void - Save the list in .json.
        •	Implement loadList(filePath: String): void - Load a list from a file.

Filter List:

	•	Implement ItemSearchModel:
	•	Implement filterItems(criteria: String): List<Item> - Filter items based on criteria.

### Week 2-3: MVP Development

Goals:

	•	Develop the graphical user interface (GUI).
	•	Integrate basic functionality from Week 1.

#### Tasks:

Create ResultsPanel:

	1.	Implement displayItems(items: List<Item>): void - Display a list of items.
	2.	Implement showError(message: String): void - Display an error message.

Create SearchFilterSortPanel:

	1.	Implement getSearchKeyword(): String - Retrieve the search keyword from the user.
	2.	Implement getSortCriteria(): String - Retrieve the sort criteria from the user.
	3.	Implement getFilterCriteria(): String - Retrieve the filter criteria from the user.
	4.	Implement showError(message: String): void - Display an error message.

Create ImportExportPanel:

	1.	Implement getImportFilePath(): String - Retrieve the import file path.
	2.	Implement getExportFilePath(): String - Retrieve the export file path.
	3.	Implement showError(message: String): void - Display an error message.

Create ItemDetailPanel:

	1.	Implement displayItemDetails(item: Item): void - Display item details.
	2.	Implement getItemInput(): Item - Get item input for modification.
	3.	Implement showError(message: String): void - Display an error message.

Integrate Basic Functionality:

	1.	Enhance the ResultsPanel to handle large datasets.
	2.	Allow users to select multiple items from the ResultsPanel.
	3.	Integrate buildList(selectedItems: List<Item>) method in ItemController.
	4.	Integrate search functionality with ItemController.
    5.  Finish all the basic functional

#### Deliverable:

	•	Fully developed GUI components with basic functionality for item display, search, and list building.

### Week 3: Full Integration and Testing

#### Goals:

	•	Integrate all components developed in Week 1 and Week 2.
	•	Perform thorough testing and debugging.

#### Tasks:

Implement ItemController:

	1.	Implement viewItems(): void - Fetch and display items.
	2.	Implement buildList(selectedItems: List<Item>): void - Build a list of selected items.
	3.	Implement saveList(fileFormat: String, filePath: String): void - Save the list.
	4.	Implement loadList(filePath: String): void - Load a list from a file.
	5.	Implement searchItems(): void - Search for items and update display.
	6.	Implement sortItems(): void - Sort items and update display.
	7.	Implement filterItems(): void - Filter items and update display.
	8.	Implement showItemDetails(item: Item): void - Display item details.
	9.	Implement importList(): void - Import a list.
	10.	Implement exportList(): void - Export a list.
	11.	Implement updateItemDetails(): void - Update item details.

Integrate Components:

	1.	Connect view components (ResultsPanel, SearchFilterSortPanel, ImportExportPanel, ItemDetailPanel) to the controller.
	2.	Ensure user interactions trigger appropriate controller methods and update views.

Testing and Debugging:

	1.	Perform thorough testing of all functionalities.
	2.	Fix any identified bugs and ensure stable integration.

Deliverable:

	•	Fully integrated and tested application with model, view, and controller components working together.

### Week 4: Product Presentation and Final Testing

#### Goals:

	•	Prepare for product presentation.
	•	Finalize testing and debugging.

#### Tasks:

Product Presentation:

	1.	Create a presentation outlining the project’s features, functionalities, and implementation.
	2.	Prepare a demonstration of the application.

Final Testing and Debugging:

	1.	Perform final testing of all functionalities.
	2.	Fix any remaining bugs.
	3.	Ensure the project is stable and ready for submission.

Deliverable:

	•	Presentation and demonstration prepared.
	•	Final project submission, including all documentation.


## UML

```mermaid
        classDiagram
        class Item {
            -id: int
            -title: String
            -thumbnail: String
            -short_description: String
            -game_url: String
            -genre: String
            -platform: String
            -publisher: String
            -developer: String
            -release_date: String
            -freetogame_profile_url: String
        }

        class ItemModel {
            -items: List~Item~
            +getItems(): List~Item~
            +addItem(item: Item): void
            +removeItem(item: Item): void
            +saveList(fileFormat: String, filePath: String): void
            +loadList(filePath: String): void
        }

        class ItemSearchModel {
            +searchItems(keyword: String): List~Item~
            +sortItems(criteria: String): List~Item~
            +filterItems(criteria: String): List~Item~
        }

        class ResultsPanel {
            +displayItems(items: List~Item~): void
            +showError(message: String): void
        }

        class SearchFilterSortPanel {
            +getSearchKeyword(): String
            +getSortCriteria(): String
            +getFilterCriteria(): String
            +showError(message: String): void
        }

        class ImportExportPanel {
            +getImportFilePath(): String
            +getExportFilePath(): String
            +showError(message: String): void
        }

        class ItemDetailPanel {
            +displayItemDetails(item: Item): void
            +getItemInput(): Item
            +showError(message: String): void
        }

        class ItemController {
            -itemModel: ItemModel
            -searchModel: ItemSearchModel
            -resultsPanel: ResultsPanel
            -searchFilterSortPanel: SearchFilterSortPanel
            -importExportPanel: ImportExportPanel
            -itemDetailPanel: ItemDetailPanel
            +ItemController(itemModel: ItemModel, searchModel: ItemSearchModel, resultsPanel: ResultsPanel, searchFilterSortPanel: SearchFilterSortPanel, importExportPanel: ImportExportPanel, itemDetailPanel: ItemDetailPanel)
            +viewItems(): void
            +buildList(selectedItems: List~Item~): void
            +saveList(fileFormat: String, filePath: String): void
            +loadList(filePath: String): void
            +searchItems(): void
            +sortItems(): void
            +filterItems(): void
            +showItemDetails(item: Item): void
            +importList(): void
            +exportList(): void
            +updateItemDetails(): void
        }

        ItemController --> ItemModel
        ItemController --> ItemSearchModel
        ItemController --> ResultsPanel
        ItemController --> SearchFilterSortPanel
        ItemController --> ImportExportPanel
        ItemController --> ItemDetailPanel
        ItemModel --> Item
    ```