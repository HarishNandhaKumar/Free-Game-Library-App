# Manual

This folder could contain information and screenshot on how to use the program.  If following this, we will get
an idea of what elements you have in the GUI, and to fully use it. 

## Introduction

The Free Game Library Application allows users to look up free games information, list all stored records, and export the records in various formats. This manual will guide through main features, and how to use the application effectively.

## Running Program

After running MainApp.java, the window of `Free Game Library` will show up. 

## Main Features

This application contains four parts - input box, functionality buttons, display area and file (save/load) buttons. 
![Screenshot 2024-08-04 185025](https://github.com/user-attachments/assets/b3188be2-6ea1-4b8c-9f88-c79770944596)

### Search Button

On the top of the window, there is a box for user to type for searching and filtering. After typing the game name, click the `Search` button to search the information about the game and the result will be printed on the display area below buttons. All the game information are obtained through https://www.freetogame.com/api.

If the game is not available through the api, "No game found" will be printed on the display area.
![Screenshot 2024-08-04 185622](https://github.com/user-attachments/assets/d18c427e-1f03-4386-bc5e-e7cd96534070)

If the game exists in the api database, then the game information will be printed on the display area, and the default format is JSON.
![Screenshot 2024-08-04 185802](https://github.com/user-attachments/assets/6ed500b4-08f5-498d-a7f9-e13757f4cb64)

### List Button

The `List` button locates on the right cornor of the second row of buttons. This button allows user to print out current game list to further edit the list.

At the beginning, the list should be empty.
![Screenshot 2024-08-04 193349](https://github.com/user-attachments/assets/1cbdcd17-4bd6-4669-9f5a-2458f22d71d5)

After adding or filtering games, the `list` button will print out the most updated list to the display screen.

### Add and Add All Buttons

`Add` button locates on the first row of the buttons while `Add All` is on the second row. The difference between `Add` and `Add All` is that `Add` handles the situations when the user only needs to add a single game item (per time) to the game, while `Add All` will add all free games that are retrieved from the api database. 

![Screenshot 2024-08-04 190159](https://github.com/user-attachments/assets/eb8ae186-7352-4fe3-b57b-c74638053282)

For example, if the user obtains all PC-based free games, and only needs to add one game, such as `Age of Wushu`, then the user needs to click `Add` button instead of `Add All`. Then the user can type in the name of game, in this case, `Age of Wushu`, in the pop-up window and click `OK`.
![Screenshot 2024-08-04 190712](https://github.com/user-attachments/assets/ce9ec479-e622-49f4-b6f5-db8e01f4f095)
Then the information of `Age of Wushu` would be added to the game list.

![Screenshot 2024-08-04 190959](https://github.com/user-attachments/assets/e6e6aa77-97e3-4934-8f3c-10164370227e)

If the user needs to add all PC-based free games, instead of using `Add` button to add games one by one, the user can click `Add All` button. After clicking, the display area will print `Done`.
![Screenshot 2024-08-04 191624](https://github.com/user-attachments/assets/d7d5ca74-bf5c-4bef-beae-eb5ef7608166)

Then when the user need to display current game list, all the PC-based free games will be printed on the display screen.
![Screenshot 2024-08-04 191751](https://github.com/user-attachments/assets/0e31cf00-3423-4ccd-9316-4559433577af)

### Remove and Remove All Buttons

`Remove` button is on the first row and `Remove All` button is on the second row. Similar to adding function, `Remove` is responsible for removing a single free game on the current game list and `Remove All` is responsible for removing all games on the game list. 

As demonstrated on above example, the user's current game list include all PC-based free games. If the user only needs to remove game `Pixel Worlds`, then the user needs to click `Remove` button, type `Pixel Worlds` and click `OK`.

![Screenshot 2024-08-04 192831](https://github.com/user-attachments/assets/f9d2d404-3768-45b4-a98d-126898a19050)

After the game is successfully removed, the screen will print `Removed Game`. In this case, `Pixel Worlds` will not be on the list anymore.
![Screenshot 2024-08-04 193015](https://github.com/user-attachments/assets/82cf3e0b-3349-4db5-a745-08486bd7e2b0)

Instead, if the user needs to remove all the games on the list, the user needs to click `Remove All` button. After clicking the button, the screen will print `Removed All Games from Main List`.

![Screenshot 2024-08-04 193241](https://github.com/user-attachments/assets/0f2ebe16-564f-41f5-b734-48c9a1d62252)

Then When the user needs to display the current game list, the `Empty list` shall be printed out.
![Screenshot 2024-08-04 193349](https://github.com/user-attachments/assets/1cbdcd17-4bd6-4669-9f5a-2458f22d71d5)

### Filter and Sort Buttons

The `Filter` button allows the user to filter games obtained through the api. For example, when the user is only interested in shooter games, the user should click `Filter` button, then there will be a pop-up window for the user to choose the filter parameter. In this case, the user should choose `genre` and click `OK`.

![Screenshot 2024-08-04 195501](https://github.com/user-attachments/assets/23ae028f-885b-4496-bdb7-a9484e73bc63)

After that, there will be another pop-up window for the user to choose filter type. `==` means `Equals`, `~=` means `Contains`, `<=` means `Less_Equal`, `>=` means `Greater_Equal`, `!=` means `Not_Equal`, `>` means `Greater_Than`, `<` means `Less_Than`. If the user is sure about the genre name, then the user can choose `==` and click `OK`.
![Screenshot 2024-08-04 195723](https://github.com/user-attachments/assets/56becd23-4e94-49ce-9a62-27139dcd1a9a)

After clicking `OK`, there will be another window for the user to enter the value, in this case, `Shooter`.
![Screenshot 2024-08-04 200431](https://github.com/user-attachments/assets/f0d05b34-956c-4614-9927-70000ec71537)

After clicking `OK`, the screen will print out all the Shooter games.
![Screenshot 2024-08-04 200543](https://github.com/user-attachments/assets/c49a83bd-309f-4abf-ace6-481eecb9e721)

Regarding Sorting function, it requires the user to choose Sort parameter Sort and Type.
![Screenshot 2024-08-04 201241](https://github.com/user-attachments/assets/c6c9df4a-f879-4130-a2cf-fa863cb3aed2)
![Screenshot 2024-08-04 201324](https://github.com/user-attachments/assets/10d8003d-3192-42eb-af10-34c82f508c91)

For example, if the user wants to sort the shooter games based on their IDs (in ascending order). Then the user needs to choose `id` as the parameter and `ascending` as the type. Then, the sorted list shall be able to be printed on the screen as demonstrated as below.

![Screenshot 2024-08-04 201600](https://github.com/user-attachments/assets/06649ce9-372c-47a4-aa73-713a59398893)

### Load List and Export Buttons

After the user create a list, such as the sorted shooter games, then user can click `Export` button to save all the data to a formatted file. On this `Export List` window, the user can choose a file path and a file type to export the file. The file types include XML, JSON, CSV and TXT.
![Screenshot 2024-08-04 202135](https://github.com/user-attachments/assets/3a6410d9-b289-4fa3-a452-ad5f5941f39b)
![Screenshot 2024-08-04 202314](https://github.com/user-attachments/assets/8fb14e14-823b-4020-b596-09d6ef1902dd)

For example, the user wants to save the data to a JSON file, called `shooterGames`, then the user should type the file name and choose JSON as the file type.
![Screenshot 2024-08-04 202627](https://github.com/user-attachments/assets/f70fa482-1036-4f82-a5e6-178769fe6a68)

After clicking `Save`, there should be a notification window indiciating the process is successful.

![Screenshot 2024-08-04 202706](https://github.com/user-attachments/assets/c3494966-e2e8-4fd0-a9b0-e22abf4b4565)

For `Load List` button, it allows the user to load a file that contains the game information.
![Screenshot 2024-08-04 201903](https://github.com/user-attachments/assets/3a8e9fe2-c05b-4c7c-ae84-76ba051f07d1)
After proceeding to the directory that contains the files, we can see that `shooterGames.json` exists. Upon openning the file, the user shall be able to get a sorted list.
![Screenshot 2024-08-04 203326](https://github.com/user-attachments/assets/3ac95e70-2386-48e4-a99d-030b813c6787)