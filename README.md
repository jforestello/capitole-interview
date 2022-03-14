# mytheresa-interview
This repository is created for the technical interview with `Mytheresa`.

## Requirements
 - Create a REST API endpoint, that given a list of products, applies a range of discounts, and allows to filter them
 - The products in the category `boots` have a `30%` discount
 - The products with sku equals to `000003` have a `15%` discount
 - Only one discount can be applied at the same time; giving priority to the bigger
 - The products can be filtered by `category` or `max price`

## Decisions
 - This repository is made with a clean architecture and applying the SOLID principles
 - The `PingController` allows for a heartbeat in case of using a Service Discovery structure
 - The `ProductController` has one endpoint with two methods:
   - `GET`: Search the stored products and returns them with all the discounts calculated. It can be filtered by category or maximum price, and it will, at most, return 5 elements unsorted
     - The items limit can be changed in the property inside the resources folder
     - It will always return 200 and an array
   - `POST`: Stores all the products provided, and returns their prices with all the discounts applied
     - It will always return 202 and an array with the provided elements already calculated
 - Each layer has its own model with all the entities required by them; in order to encapsulate the layer specific behaviour, and prevent any manipulation in the business model (domain)
 - Only the entities inside the `domain` package are allowed to be known by all layers; and every contract is located there as well
 - Inside the `usecase` and `infrastructure` packages is segregated by feature, to prevent a lot of classes and files with different scopes to be mixed
 - Both `usecase` and `infrastructure` has a `bootstrap` file; which handles all the injection for said package. This is in order to facilitate the bean creation of every class

##Instructions
 - In order to run only the test for the project, you can use
    ```
    ./gradlew test
    ```

 - If you wish to run the test with a coverage report using jacoco, you can use
    ```
    ./gradlew test jacocoTestReport
    ```
   After that, you can find the coverage report in the folder
    ```
   build/reports/jacoco/tests/index.html
    ```
 - To run the project you can run
    ```
   ./gradlew bootRun
   ```