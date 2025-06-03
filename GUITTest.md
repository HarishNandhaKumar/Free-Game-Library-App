# GUI Test Report

## Overview
This report outlines the results of the GUI tests conducted on the application. Each test case includes a description, the steps taken, the expected outcome, the actual outcome, and any relevant screenshots.

## Test 1: search button
Steps:
1. Type in the game name.
2. Locate the button labeled "search".
3. Click the button.
Expected Outcome: If the game name exists, display the info of the game. If the game does not exist, display "no game found". (not case sensitive)
Actual Outcome: Same as expected.
Status: Passed
Screenshots if game exists:
![searchtest1](GUIimg/searchtest1.png)
![searchtest2](GUIimg/searchtest2.png)
Screenshots if game does not exist:
![searchtest3](GUIimg/searchtest3.png)

## Test 2: filter button
Steps:
1. Click the button.
2. Select the filter parameter.
3. Select a filter type.
4. Type in the filter feature.
5. Click Ok.
Expected Outcome: Display the filtered result. If filtered result is null, display an empty list [ ]. If click "cancel", display "filter incomplete". If filter parameter is a numeric value, filter type compare the numeric value. If parameter is a string, it will be in alphabetical order.
Actual Outcome: Same as expected.
Status: Passed

Screenshots when filter canceled:
![filtercancel](GUIimg/filtercancel.png)

Result of different filter parameters:
Screenshots when the filter parameter is "id", filter type is "==", feature is "1"。
![filterid4](GUIimg/filterid4.png)

Screenshots when the filter parameter is "title", filter type is "==", feature is "Overwatch 2"。
![filtertilt==](GUIimg/filtertilt_equal.png)

Screenshots when the filter parameter is "thumbnail", filter type is "==", feature is "https://www.freetogame.com/g/292/thumbnail.jpg"。
![thumnail](GUIimg/thumnail.png)

Screenshots when the filter parameter is "short description", filter type is "==", feature is "Take the battle royale genre and add  mystical powers and you have CRSED: F.O.A.D. (Aka Cuisine Royale: Second Edition)"。
![shortdes==](GUIimg/shortdes_equal.png)

Screenshots when the filter parameter is "game url", filter type is "==", feature is "https://www.freetogame.com/open/second-life"。
![gameurl==](GUIimg/gameurl_equal.png)

Screenshots when the filter parameter is "genre", filter type is "==", feature is "social"。
![social==](GUIimg/social_equal.png)

Screenshots when the filter parameter is "platform", filter type is "==", feature is "pc (windows)"。
![platform==](GUIimg/platform_equal.png)

Screenshots when the filter parameter is "publisher", filter type is "==", feature is "snail games"。
![publisher==](GUIimg/publisher_equal.png)

Screenshots when the filter parameter is "developer", filter type is "==", feature is "SEGA"。
![developer==](GUIimg/developer_equal.png)

Screenshots when the filter parameter is "date", filter type is "==", feature is "2022-10-18"。
![date==](GUIimg/date_equal.png)

Result of different filter type:

Screenshots when the filter parameter is "date", filter type is "==", feature is "2022-10-18"。
![date==](GUIimg/date_equal.png)

Screenshots when the filter parameter is "date", filter type is "~=", feature is "2022"。It display all games in 2022.
![date~=2022](GUIimg/date_2022.png)

Screenshots when the filter parameter is "date", filter type is "!=", feature is "2022-01-12"。
![date!=2022-01-12](GUIimg/date_2022-01-12.png)

Screenshots when the filter parameter is "date", filter type is ">", feature is "2024-06-22"。
![date>2024-06-22](GUIimg/date_gre_2024-06-22.png)

Screenshots when the filter parameter is "date", filter type is ">=", feature is "2024-06-22"。
![date>=2024-06-22](GUIimg/date_gre2024-06-22.png)

Screenshots when the filter parameter is "date", filter type is "<", feature is "2001-01-04"。
![date<2001-01-04](GUIimg/date_sma_2001-01-04.png)

Screenshots when the filter parameter is "date", filter type is "<=", feature is "2001-01-04"。
![date<=2001-01-04](GUIimg/date_smae2001-01-04.png)

Screenshots when the filter parameter is "title", filter type is "==", feature is "overwatch 2"。
![filtertilt==](GUIimg/filtertilt_equal.png)

Screenshots when the filter parameter is "title", filter type is "~=", feature is "world"。
![filtertitle~=](GUIimg/filtertitlecon.png)

Screenshots when the filter parameter is "title", filter type is "!=", feature is "overwatch 2"。
![title!=](GUIimg/title_note.png)

Screenshots when the filter parameter is "title", filter type is ">", feature is "XDefiant"。
![title>XDefiant](GUIimg/title_g_XDefiant.png)

Screenshots when the filter parameter is "title", filter type is ">=", feature is "zula"。
![filter>=zula](GUIimg/filter_ge_zula.png)

Screenshots when the filter parameter is "title", filter type is "<", feature is "A.V.A Global"。
![title<AGlobal](GUIimg/title_s_AGlobal.png)

Screenshots when the filter parameter is "title", filter type is "<=", feature is "A.V.A Global"。
![title<=AGlobal](GUIimg/title_se_AGlobal.png)

## Test 3: add/remove/list button
Steps for add button:
1. Locate the button labeled "add".
2. Click the button.
3. Click OK.
4. Locate the button labeled "list".
5. Click the button and choose the format to display.
Expected Outcome: When click the list, display the game added.
Actual Outcome: Same as expected.
Status: Passed

Screenshots of adding "Overwatch 2" and choose json as display format.
![alt text](GUIimg/listjson.png)

Screenshots of adding "Overwatch 2" and choose csv as display format.
![alt text](GUIimg/listcsv.png)

Screenshots of adding "Overwatch 2" and choose xml as display format.
![alt text](GUIimg/llistxml.png)

Screenshots of adding "Overwatch 2" and choose pretty as display format.
![alt text](GUIimg/listpretty.png)


Steps for remove button:
1. Locate the button labeled "remove".
2. Click the button.
3. Click OK.
4. Locate the button labeled "list".
5. Click the button and choose the format to display.
Expected Outcome: When click the list, display the game not removed.
Actual Outcome: Same as expected.
Status: Passed
Screenshots of removing "Overwatch 2" from the list.
![alt text](GUIimg/removeresult.png)

## Test 4: Add all/Remove all button
Steps for Add All button:
1. filtered the game.
2. Locate the button labeled "Add All"
3. Click the button.
Expected Outcome: If added successfully, it will display "Done".
Actual Outcome: Same as expected.
Status: Passed

Screenshot when filtered id<=2 and clicked add all.
![alt text](GUIimg/addall1.png)
Screenshot when filtered id<=2, clicked add all, and click list.
![alt text](GUIimg/addall2.png)


Steps for Remove All button:
1. Locate the button labeled "Remove All"
2. Click the button.
Expected Outcome: If added successfully, it will display "Removed All Games from Main List". If the game list is already empty, it will display "Games List is already empty".
Actual Outcome: Same as expected.
Status: Passed

Screenshot when clicked removed all and the game list is not empty.
![alt text](GUIimg/removeall.png)

Screenshot when clicked removed all but the game list is already empty.
![alt text](GUIimg/removeallempty.png)

## Test 5: Sort button
Steps:
1. Locate the button labeled "sort" and click.
2. Select the sort parameter
3. Select a sort type
4. Click Ok.
Expected Outcome: Display the orderd result. If click "cancel", display "Sort Option is null". If sort parameter is a numeric value, it will be sorted by the numeric value. If parameter is a string, it will be in alphabetical order.
Actual Outcome: Same as expected.
Status: Passed

Screenshot when sort canceled:
![alt text](GUIimg/sortnull.png)

Result of different sort parameters:

Screenshots when the sort parameter is "id", filter type is "ascending".
![alt text](GUIimg/sortida.png)
Screenshots when the sort parameter is "id", filter type is "descending".
![alt text](GUIimg/sortidd.png)

Screenshots when the sort parameter is "title", filter type is "ascending".
![alt text](GUIimg/sorttitlea.png)
Screenshots when the sort parameter is "title", filter type is "descending".
![alt text](GUIimg/sorttitled.png)

Screenshots when the sort parameter is "genre", filter type is "ascending".
![alt text](GUIimg/sortgenrea.png)
Screenshots when the sort parameter is "genre", filter type is "descending".
![alt text](GUIimg/sortgenred.png)

Screenshots when the sort parameter is "publisher", filter type is "ascending".
![alt text](GUIimg/sortpuba.png)
Screenshots when the sort parameter is "publisher", filter type is "descending".
![alt text](GUIimg/sortpubd.png)

Screenshots when the sort parameter is "developer", filter type is "ascending".
![alt text](GUIimg/sortdeva.png)
Screenshots when the sort parameter is "developer", filter type is "descending".
![alt text](GUIimg/sortdevd.png)

Screenshots when the sort parameter is "release_date", filter type is "ascending".
![alt text](GUIimg/sortdatea.png)
Screenshots when the sort parameter is "release_date", filter type is "descending".
![alt text](GUIimg/sortdated.png)

## Test 5: Load button
Steps:
1. Locate the button labeled "Load List" and click.
2. Select the file format
3. Select the databse file.
Expected Outcome: If the file is load successfully, it will display the file when click list.
Actual Outcome: Same as expected.
Status: Passed

Screenshots:
![alt text](GUIimg/loadcsv.png)
![alt text](GUIimg/loadjson.png)
![alt text](GUIimg/loadxml.png)

## Test 6: Export button
Steps:
1. Locate the button labeled "export" and click.
2. Select the file format
3. Type in a valid file name and choose the file path.
4. Click Save
Expected Outcome: If the file is saved successfully, it will pop a window and report "Export Success". The file will be saved in the selected path.
Actual Outcome: Same as expected.
Status: Passed

Screenshots:
![alt text](GUIimg/export_done.png)
![alt text](GUIimg/exportfile.png)