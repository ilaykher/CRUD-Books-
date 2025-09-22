package DSA.src.schoolLoad.activities.systemactOne;

import java.util.Scanner;


public class BookMain {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choices = -1;
    
        while (true) {
            System.out.println("=============================");
            System.out.println("Welcome to CRUD Books Program");
            System.out.println("=============================");
            System.out.println("Please select an option:");
            System.out.println("[1] Set Account Balance");
            System.out.println("[2] Insert Book");
            System.out.println("[3] View All Books");
            System.out.println("[4] View Books by Genre");
            System.out.println("[5] Update Book");
            System.out.println("[6] Buy a Book");
            System.out.println("[7] Search a Book");
            System.out.println("[8] Remove a Book");
            System.out.println("[9] Exit System");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine().trim();
            
            // Validate that input contains only digits
            if (!choice.matches("\\d+")) {
                System.out.println("=====================================================");
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;  // back to start of loop to prompt again
            }
            
            // By parsing, the string will become an int 
            try {
                choices = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;
            }
            
            if (choices < 1 || choices > 9) {
                System.out.println("===========================================================");
                System.out.println("Choice out of range. Please enter a number between 1 and 9.");
                continue; 
            }
    
            switch (choices) {
                case 1:
                    accountBalance();
                    break;
                case 2:
                    insertBook();
                    break;
                case 3:
                    BookManager.viewAllbooks();
                    break;
                case 4:
                    showBooksByGenre();
                    break;
                case 5:
                    updateBooks();
                    break;
                case 6:
                    buyBooks();
                    break;
                case 7:
                    searchABook();
                    break;
                case 8:
                    BookManager.removeShowingAllBooks();
                    break;
                case 9:
                    System.out.println("Thank you for using the Book Management System!");
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }//while loop
        }//for main method

    

    static void accountBalance() {
            System.out.print("Enter account balance, press [B] to go back:");
            String balanceInput = scanner.nextLine(); // Read as string to handle any input

            if (balanceInput.equalsIgnoreCase("B")) {
                main(null); 
                return;
            }

            if (!balanceInput.matches("[0-9]+(\\.[0-9]{1,2})?")) { // Check if it matches a number format
                System.out.println("Invalid balance format. Please enter a valid amount.");
                accountBalance(); // Try again
                return;
            }

            double balance = Double.parseDouble(balanceInput);

            if (balance < 0) {
                System.out.println("Balance cannot be negative. Please enter a valid amount.");
                accountBalance(); // Try again
                return;
            }

            BookManager.setAccountBalance(balance);
            System.out.println("Account balance set to: PHP " + balance);
        }


        // Method to insert a new book
        // This method prompts the user for book details and calls the BookManager to insert the book
    static void insertBook() {
            System.out.println("============================================");
            System.out.println("Please enter the details of the book you want to add!");

            String title = "";
            while (title.trim().isEmpty()) {
                System.out.print("Enter book title: ");
                title = scanner.nextLine();
                if (title.trim().isEmpty()) {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                }
            }

            String author = "";
            while (author.trim().isEmpty()) {
                System.out.print("Enter book author: ");
                author = scanner.nextLine();
                if (author.trim().isEmpty()) {
                    System.out.println("Author cannot be empty. Please enter a valid author.");
                }
            }

            int publishedYear = 0;
            while (true) {
                System.out.print("Enter book published year: ");
                if (scanner.hasNextInt()) {
                    publishedYear = scanner.nextInt();
                    if (publishedYear > 0) break;
                    else System.out.println("Published year must be positive.");
                } else {
                    System.out.println("Invalid input. Please enter a valid year.");
                    scanner.next(); // discard invalid input
                }
            }
            scanner.nextLine();  // consume newline

            String summary = "";
            while (summary.trim().isEmpty()) {
                System.out.print("Enter book summary: ");
                summary = scanner.nextLine();
                if (summary.trim().isEmpty()) {
                    System.out.println("Summary cannot be empty. Please enter a valid summary.");
                }
            }

            double price = 0.0;
            while (true) {
                System.out.print("Enter book price: PHP ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price >= 0) break;
                    else System.out.println("Price cannot be negative.");
                } else {
                    System.out.println("Invalid input. Please enter a valid price.");
                    scanner.next(); // discard invalid input
                }
            }

            int quantity = 0;
            while (true) {
                System.out.print("Enter book quantity: ");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                    if (quantity >= 0) break;
                    else System.out.println("Quantity cannot be negative.");
                } else {
                    System.out.println("Invalid input. Please enter a valid quantity.");
                    scanner.next(); // discard invalid input
                }
            }

            int genre_id = 0;
            while (true) {
                System.out.print("Enter book genre ( [1] Horror, [2] Fantasy, [3] Romantic, [4] Fiction, [5] Mystery, [6] Thriller, [7] Sci-Fi ): ");
                if (scanner.hasNextInt()) {
                    genre_id = scanner.nextInt();
                    if (genre_id >= 1 && genre_id <= 7) break;
                    else System.out.println("Invalid genre. Please select a number between 1 and 7.");
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // discard invalid input
                }
            }
            scanner.nextLine(); // consume newline

            System.out.println("=============================");
            System.out.print("[1] to confirm, [0] to cancel: ");
            int condition = -1;
            while (true) {
                if (scanner.hasNextInt()) {
                    condition = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (condition == 0 || condition == 1) break;
                    else System.out.print("Invalid input. Please enter 1 to confirm or 0 to cancel: ");
                } else {
                    System.out.print("Invalid input. Please enter 1 to confirm or 0 to cancel: ");
                    scanner.next(); // discard invalid input
                }
            }

            if (condition == 1) {
                BookManager.createBooks(title, author, publishedYear, summary, price, quantity, genre_id);
                System.out.println("Book inserted successfully!");
            } else {
                System.out.println("Insert cancelled. Returning to menu...");
            }
            System.out.println("============================================");
            BookMain.main(null); // Return to main menu after insertion
        }


        // Method to show books by genre
        // This method prompts the user to select a genre and displays books of that genre
    static void showBooksByGenre() {
            System.out.println("============================================");
            System.out.println("Please select a genre to view books:");
            System.out.println("[1] Horror");
            System.out.println("[2] Fantasy");
            System.out.println("[3] Romantic");
            System.out.println("[4] Fiction");
            System.out.println("[5] Mystery");
            System.out.println("[6] Thriller");
            System.out.println("[7] Sci-Fi");
            System.out.println("[8] Back");
            System.out.print("Enter your choice: ");

            String genreChoice = scanner.nextLine();
            if (genreChoice.matches("\\d+")) { // Only digits
                int choice = Integer.parseInt(genreChoice);

                if (choice >= 1 && choice <= 7) {
                    BookManager.displayBooksByGenre(choice);
                } else if (choice == 8) {
                    main(null);
                } else {
                    System.out.println("Invalid choice. Try again.");
                    showBooksByGenre();
                }
            } else {
                System.out.println("=============================================z");
                System.out.println("Invalid choice. Try again.");
                showBooksByGenre();
            }

            }

        // Method to update books
        // This method prompts the user to enter the book ID and new details for updating
    static void updateBooks() {
        BookManager.showAllBooksFirst();
        
        int bookId = 0;
        while (true) {
            System.out.print("Select a Book ID to update: ");
            if (scanner.hasNextInt()) {
                bookId = scanner.nextInt();
                scanner.nextLine();
                if (bookId > 0) break;
                else System.out.println("Book ID must be positive.");
            } else {
                System.out.println("Invalid input. Please enter a valid book ID.");
                scanner.next(); // discard invalid input
            }
        }

        // ✅ Check if book exists before continuing
        if (!BookManager.bookExists(bookId)) {
            System.out.println("No book found with the given ID, try again");
            updateBooks();
            return; // stop here
        }

        System.out.print("Enter new book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter new book author: ");
        String newAuthor = scanner.nextLine();

        int newPublishedYear = 0;
        while (true) {
            System.out.print("Enter new published year: ");
            if (scanner.hasNextInt()) {
                newPublishedYear = scanner.nextInt();
                scanner.nextLine();
                if (newPublishedYear > 0) break;
                else System.out.println("Published year must be positive.");
            } else {
                System.out.println("Invalid input. Please enter a valid year.");
                scanner.next();
            }
        }

        System.out.print("Enter new summary: ");
        String newSummary = scanner.nextLine();

        double newPrice = 0.0;
        while (true) {
            System.out.print("Enter new price: PHP ");
            if (scanner.hasNextDouble()) {
                newPrice = scanner.nextDouble();
                scanner.nextLine();
                if (newPrice >= 0) break;
                else System.out.println("Price cannot be negative.");
            } else {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.next();
            }
        }

        int newQuantity = 0;
        while (true) {
            System.out.print("Enter new quantity: ");
            if (scanner.hasNextInt()) {
                newQuantity = scanner.nextInt();
                scanner.nextLine();
                if (newQuantity >= 0) break;
                else System.out.println("Quantity cannot be negative.");
            } else {
                System.out.println("Invalid input. Please enter a valid quantity.");
                scanner.next();
            }
        }

        int newGenre = 0;
        while (true) {
            System.out.print("Enter new book genre ( [1] Horror, [2] Fantasy, [3] Romantic, [4] Fiction, [5] Mystery, [6] Thriller, [7] Sci-Fi ): ");
            if (scanner.hasNextInt()) {
                newGenre = scanner.nextInt();
                scanner.nextLine();
                if (newGenre >= 1 && newGenre <= 7) break;
                else System.out.println("Invalid genre. Please choose a number between 1 and 7.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        int condition = -1;
        while (true) {
            System.out.println("=============================");
            System.out.print("[1] to confirm, [0] to cancel: ");
            if (scanner.hasNextInt()) {
                condition = scanner.nextInt();
                scanner.nextLine();
                if (condition == 0 || condition == 1) break;
                else System.out.println("Please enter 1 to confirm or 0 to cancel.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        if (condition == 1) {
            boolean updated = BookManager.updateBooks(bookId, newTitle, newAuthor, newPublishedYear, newSummary, newPrice, newQuantity, newGenre);
            if (updated) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Update failed.");
            }
        } else {
            System.out.println("Update cancelled.");
        }

        System.out.println("Returning to main menu...");
        BookMain.main(null);
    }




        // Method to buy books
        // This method displays all books and allows the user to select a book to buy
    static void buyBooks() {
            BookManager.showAllBooksFirst();
            System.out.println("Balance: PHP " + BookManager.getAccountBalance());
            System.out.print("Enter the ID of the book you want to buy or press [B] to go back: ");
            String bookIdInputString = scanner.nextLine();

            if (bookIdInputString.equalsIgnoreCase("B")) {
                main(null); // Go back to main menu
            }         

            if (bookIdInputString.matches("\\d+")) { // Only digits
                int bookId = Integer.parseInt(bookIdInputString);
                System.out.print("Enter the quantity you want to buy: ");
                String quantityString = scanner.nextLine();

                if (quantityString.matches("\\d+")) {
                    int quantity = Integer.parseInt(quantityString);

                    System.out.print("[1] to confirm, [0] to cancel: ");
                    String input = scanner.nextLine();  // Read input as String

                    try {
                        int confirmationBuyBook = Integer.parseInt(input);  // Parse to int

                        if (confirmationBuyBook == 1) {
                            BookManager.buyBook(bookId, quantity);
                        } else if (confirmationBuyBook == 0) {
                            System.out.println("===================");
                            System.out.println("Purchase cancelled!");
                            System.out.println("Returning to main menu...");
                            main(null);
                        } else {
                            System.out.println("======================================================");
                            System.out.println("Type a valid input (0 or 1). Returning to main menu...");
                            main(null);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("============================================================");
                        System.out.println("Invalid input. Please type 0 or 1. Returning to main menu...");
                        main(null);
                    }

                 
                    
                } else {
                    System.out.println("=====================================");
                    System.out.println("The quantity you entered is not valid: " + quantityString);
                    System.out.println("Please enter a valid quantity.");
                    buyBooks();
                }
            } else {
                System.out.println("============================================");
                System.out.println("The ID you entered is not valid: " + bookIdInputString);
                System.out.println("Please enter a valid book ID.");
                buyBooks(); 
            }
            
           
        }
        

    static void searchABook(){
            BookManager.showAllBooksFirst();
            System.out.print("Enter the title of the book you want to search(example: The Shining): ");
            String title = scanner.nextLine();
            BookManager.searchBook(title);

            
        }

    static void removeABook(){
            int bookId = -1;

            while (true) {
                System.out.print("Enter the ID of the book you want to remove: ");
                String input = scanner.nextLine();

                // Validate if input is digits only and positive
                if (input.matches("\\d+")) {
                    bookId = Integer.parseInt(input);

                    if (bookId > 0) {
                        break; // valid ID, exit loop
                    } else {
                        System.out.println("Book ID must be a positive number. Please try again.");
                    }
                } else {
                    System.out.println("====================================================");
                    System.out.println("Invalid input. Please enter a valid numeric Book ID.");
                }
            }

            // Confirm removal
            while (true) {
                System.out.println("==================");
                System.out.print("[1] Confirm, [0] Cancel: ");
                String confirmation = scanner.nextLine();

                if (confirmation.equals("1")) {
                    BookManager.removeBook(bookId);
                    break;
                } else if (confirmation.equals("0")) {
                    System.out.println("Operation cancelled. Returning to main menu...");
                    break;
                } else {
                    System.out.println("========================================================");
                    System.out.println("Invalid input. Please enter 1 to confirm or 0 to cancel.");
                }
            }
        }
}// for main method
