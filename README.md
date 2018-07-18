# ProjectMOKUROKU
Inventory and Point-Of-Sale application

UPDATE July 2018: this project on infinite hiatus, same idea being implemented as a Native React application

[Some functionality mentioned below has not been implemented but is in the low-fidelity prototype/design and what I hope to achieve]

--Currently the application has been tailored for the very high definition Windows Surface Pro 4, and is not very resizable at the moment, I plan to play with layout after the functionality has been completed--

If you are wondering why this application is named Mokuroku, it is the japanese term for catalog, inventory or list. This application allows user to create an inventory which allows them to create items that they are selling with details such as name, price and stock number. 
The main page in this application shows a list of the items in the inventory and users can click on them which adds them to a sales list to the right of the screen. Once the item is there, user can choose the number of any particular item that is being sold, and a total price is shown on the bottom. Functionality like this is backed up with a sales tab which has a list of sales and their corresponding details; an undo button reverts this sale if any product has been returned.
An inventory tab allows user to manage their inventory, such as adding new items, deleting items and editing item details.

This application is written in Java with JavaFX used for interface design, it also makes use of a small SQLite local database to store data on items and sales history.
