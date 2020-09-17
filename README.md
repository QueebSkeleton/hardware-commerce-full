# Hardware-Commerce (Web Application)

An E-Commerce Web Application built and customized for selling construction hardware.
(Will support ordering in a future commit)

**Note**: Project's latest commits are on the `dev` branch. If you want to clone and deploy
this project, clone the said branch instead.

## Project Stack

Note that the project is monolithic in nature, and is layered on the server
i.e. Repository Layer, Service Layer, and Model-View-Controller (MVC) as facilitated
by Spring MVC Framework.

1. **Java** (v. 11)
  - Spring
    - Spring Core (Dependency Injection and IoC Container)
    - Spring Boot (Bootstraping and Starter Templates, create .JAR)
    - Spring Data JPA (Repository Layer)
    - Spring Security (future commit, Basic Authentication, OAuth2 with Facebook, Google)
2. **Thymeleaf** (integrated into Spring MVC)
  - For View Generation
3. **H2 Database** (Development)
  - In-memory database setup for easier development. DDL Statements are automatically
  created and executed by Hibernate ORM Framework (Spring Data JPA).
4. **PostgreSQL Database** (Production)

## Deploy

If you want to clone this project and create a basic setup;

Clone this repository

`git clone https://github.com/QueebSkeleton/hardware-commerce-full.git -b name_of_branch`

Build the project using maven, spring-boot-maven-plugin plugin, with the `repackage` goal.
This will create a .jar file on the target folder of the project.

*Note that you do not have to install maven. This project has a maven executable on the root
folder.*

`mvnw spring-boot:repackage`

Or, run it using the spring-boot-maven-plugin plugin also, with the `run` goal.

`mvnw spring-boot:run`

If in case you have repackaged the project, locate the .jar file created on the `target` folder
of the project, then simply run it as a jar.

`java -jar target/hardware-commerce-full.jar`

Then access the homepage on

`http://localhost:8080`

You can also access the administrator module (most features are not yet implemented) on

`http://localhost:8080/admin/login`

## Planned Features

1. Front Store
  - Landing Page, with Top Products shown
  - Main Store, paginated and sorted
  - Shopping Cart
  - Checkout with Billing Address and Payment Information
    - Supported Payment Methods: Paypal, Cash-On-Delivery, GCash
2. Administrator Module
  - Dashboard
  - Product Inventory
    - Create-Update-Delete Products
    - Manage Stocks (with SKU or Stock-Keeping-Unit)
    - Product Sales Charts
    - Product Analytics (soon)
  - Orders Management
    - Create Custom Order
    - Manage Orders
    - Sales Charts
