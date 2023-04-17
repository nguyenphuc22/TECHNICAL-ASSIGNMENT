# TECHNICAL-ASSIGNMENT

## Fossil's Technical Test #1 - File Explorer

An application that allows users to quickly browse and manage the files on your mobile device.

Sample UX:

![](Image/image1.png)

MUST HAVE features: File explorer app can display all folder, sub folder & files in these folders.
REQUIREMENT

- You can follow sample UX or feel free to apply your own design UX & UI.
- Complete all MUST HAVE features. 
- Other improvements or new features are a huge plus.
- You need to keep in mind that the app may extend its features in the near future to be fulfilled as a file explorer app so the app architecture should be scalable & flexible.
- You are free to apply all technologies, frameworks or programming languages to complete this app.

## My Solution

### Feature

|                          Permission                          |                             Sort                             |                            Search                            |                           OpenFile                           |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![Permission](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/Permission.gif) | ![sort](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/sort.gif) | ![search](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/search.gif) | ![openFile](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/openFile.gif) |



### Architect

In this project. I use MVVM architecture. This architecture will help me divide the data-logic-view into separate parts. Because this project in the future will be able to support different types of data such as data stored in the cloud. So in Respository and Core, I decided to structure it as an interface class. Objects like View - ViewModel will work through this interface. Thus, later supporting the cloud or data from new sources will be easy

 ![diagram](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/diagram.png)

- Components: These include single views, configuring a small component of a screen. Programmers can assemble them into a complete screen

- Core: These include single views, configuring a small component of a screen. Programmers can assemble them into a complete screen

- Model: Objects store information and work methods. Below is the File and Folder object diagram designed according to the composite pattern.

  ![classdiagram](/Users/nguyenphuc/Documents/GitHub/TECHNICAL-ASSIGNMENT/classdiagram.png)

- Repository: This is where to query data

- Routing: This is where to query data

- Screen: This is where to query data

- Util: Files that support the feature

- ViewModel: State storage, information sharing, UI logic control