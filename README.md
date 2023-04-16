# Online store application

This is a web application for an online store with an administrative panel. Users can register and log in to the system, view a list of products, and place orders. The main functions of the system are as follows:

### For Admin

- **Login Panel**: The administrator can log in to the system with a username and password after logging in, he gets ADMIN credentials.
- **Product Management**: The admin can add and edit products, including product title, description, thumbnail image URL, stock availability, price, product type, category.
- **Category Management**: The administrator can add and manage category tree for the products.
- **Product List**: The admin can view a list of all the products, they history and search for a specific product.

### For User:

- **User Registration**: Users can register with the system by providing their login information username, email and a password.
- **Login Panel**: The user can log in to the system with a username and password after logging in, he gets USER credentials.
- **Product List**: Users can view a list of all the products and search for a specific product. Product are paginated and sorted by given parameters.
- **Weather Widget**: The system can display the weather for the city where the user is located.
- **Cart**: Users can add products to their shopping cart.
- **Orders**: Users can view the items in their shopping cart and place an order.
- **Order history**: Users can view history of thier orders and actual status.
- **User details**: User can edit thier details and also add profile image.

### General description:

- The application is built using Spring Boot, Spring Data (Hibernate), Spring Security.
- The application is divided Repository, Service, and Controller layers with the appropriate logic in each layer.
- Each Service layer has unit tests.
- Access to the application and its functionality is secured using Spring Security (Basic auth). 