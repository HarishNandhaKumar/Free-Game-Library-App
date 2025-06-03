```mermaid

classDiagram
class Controller {
  -model: ItemModelImpl
  -DATABASE: String
  +getSingleGame(): String
  +getAllGamesList(): String
  +addAllGamesToList(): String
  +removeAllGamesList(): String
  +addGameToList(): void
  +removeGameFromList(): String
  +printGame(): void
  +printGamesList(): void
  +filterGames(): String
  +sortGames(): String
  +loadGamesList(): void
}

class Display {
  -prettyPrint(): void
  -prettySingle(): void
  -writeXmlData(): void
  -writeJsonData(): void
  -writeCSVData(): void
  +write(): void
}

class Filter {
  +filterSingle(): Stream<FreeGameItem>
  -getFilter(): boolean
  -filterString(): boolean
  -filterInt(): boolean
}

class Formats {
    <<enumeration>>
    JSON
    XML
    CSV
    PRETTY
    +containsValues(value: String): Formats
}

class Operations {
    <<enumeration>>
    EQUALS("==")
    CONTAINS("~=")
    GREATER_THAN(">")
    LESS_THAN("<")
    GREATER_EQUAL(">=")
    LESS_EQUAL("<=")
    NOT_EQUAL("!=")
    +getOperator(): String
    +getOperatorFromStr(): Operations
}

class XmlWrapper {
    -games: Collection<FreeGameItem>
    +XmlWrapper(): void
}

class NetUtils {
    -BASE_URL: String
    +getGamesList(): String
    +searchGameByName(): JSONObject
    +main(): void
}

class FreeGameItem {
    -id: int
    -title: String
    -thumbnail: String
    -shortDescription: String
    -gameUrl: String
    -genre: String
    -platform: String
    -publisher: String
    -developer: String
    -releaseDate: String
    -freetogameProfileUrl: String
    +getId(): int
    +getTitle(): String
    +getThumbnail(): String
    +getShortDescription(): String
    +getGameUrl(): String
    +getGenre(): String
    +getPlatform(): String
    +getPublisher(): String
    +getDeveloper(): String
    +getReleaseDate(): String
    +getFreetogameProfileUrl(): String
    +toString(): String
    +equals(): boolean
    +hashCode(): int
}

class GamesDatabase {
    +GamesDatabase(): void
}

class ItemModel {
    <<interface>>
    DATABASE: String
    +getItems(): List<FreeGameItem>
    +addItem(): void
    +removeItem(): void
    +saveList(): void
    +loadListJson(): void
}

class ItemModelImpl {
    -gameList: List<FreeGameItem>
    -gamesMap: Map<String, FreeGameItem>
    -tempGamesList: List<FreeGameItem> 
    +getItems(): List<FreeGameItem>
    +getGameFromMap(): FreeGameItem
    +checkGameExists(): Boolean
    +checkGameList(): Boolean
    +updateTempGameList(): void
    +getTempGamesList(): List<FreeGameItem>
    +addItem(): void
    +getGameList(): List<FreeGameItem>
    +removeItem(): void
    +saveList(): void
    +loadListJson(): void
    +loadListXml(): void
    +isUtf8Encoded(): boolean
    +isValidUtf8(): boolean
    +loadListCsv(): void
    +createDatabase(): ItemModelImpl
    +searchByName(): FreeGameItem
    +writeRecords(): void
}

 class Sorting {
    +sortItems(): List<FreeGameItem>
    -getComparatorByField(): Comparator<FreeGameItem>
}

class CommandInputView {
        - commandField: JTextField
        + CommandInputView()
        + getCommandText(): String
        + setCommandText(text: String): void
        + clearCommandText(): void
}

class ExportButtonView {
    - exportButton: JButton
    - loadListButton: JButton
    - fileChooser: JFileChooser
    + ExportButtonView()
    + addExportListener(): void
    + setLoadListButtonListener(): void
    + showSaveDialog(): int
    + getSelectedFile(): File
    + getFileExtension(): String
    + getFileFilter(): FileNameExtensionFilter
}

class MainView {
    - commandInputView: CommandInputView
    - searchAddRemoveView: SearchAddRemoveView
    - sortFilterView: SortFilterView
    - resultDisplayView: ResultDisplayView
    - exportButtonView: ExportButtonView
    - controller: Controller
    - searchButtonListener(): void
    - addButtonListener(): void
    - listButtonListener(): void
    - removeButtonListener(): void
    - filterButtonListener(): void
    - sortButtonListener(): void
    - addAllButtonListener(): void
    - exportButtonListener()): void
    - removeAllButtonListener(): void
    + loadListButtonListener(): void
}

class ResultDisplayView {
    - resultArea: JTextArea
    + getResultText(): String
    + setResultText(): void
    + clearResultText(): void
}

    class SearchAddRemoveView {
    - searchButton: JButton
    - addButton: JButton
    - removeButton: JButton
    + SearchAddRemoveView()
    + getSearchButton(): JButton
    + getAddButton(): JButton
    + getRemoveButton(): JButton
    + setSearchListener(): void
    + setAddListener(): void
    + setRemoveListener(): void
}

class SortFilterView {
    - sortButton: JButton
    - addAllButton: JButton
    - removeAllButton: JButton
    - filterButton: JButton
    - listButton: JButton
    + SortFilterView()
    + getSortButton(): JButton
    + getAddAllButton(): JButton
    + getRemoveAllButton(): JButton
    + getFilterButton(): JButton
    + getListButton(): JButton
    + setListButtonListener(): void
    + setFilterButtonListener(): void
    + setSortButtonListener(): void
    + setAddAllButtonListener(): void
    + setRemoveAllButtonListener(): void
}

    Controller --> ItemModelImpl : uses
    Controller --> Display : uses
    Controller --> Filter : uses
    Controller --> Sorting : uses
    Controller --> NetUtils : uses
    Controller --> GamesDatabase : uses
    ItemModelImpl --> ItemModel : implements
    MainView --> CommandInputView : contains
    MainView --> SearchAddRemoveView : contains
    MainView --> SortFilterView : contains
    MainView --> ResultDisplayView : contains
    MainView --> ExportButtonView : contains
    MainView --> Controller : uses
    Filter --> Operations : uses
    Display --> Formats : uses
    Display --> XmlWrapper : uses
    ItemModelImpl --> FreeGameItem : contains
  


    ```