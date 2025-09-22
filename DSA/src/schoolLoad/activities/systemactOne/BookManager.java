package DSA.src.schoolLoad.activities.systemactOne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookManager {

    private static final String url = "jdbc:mysql://localhost:3306/javaproject";
    private static final String username = "root";
    private static final String password = "";

    public static double accountBalance = 0.0;
        static Scanner scanner = new Scanner(System.in);

    public static void setAccountBalance(double balance) {
        accountBalance += balance;
        System.out.println("Account balance set to: PHP " + accountBalance);
        BookMain.main(null); 
    }//for accountBalance
    
    public static double getAccountBalance() {
        return accountBalance;
    }

    
    public static void showAllBooksFirst(){
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            System.out.println("\n========== ALL BOOKS ==========");
            System.out.println("?");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("published_year");
            String summary = resultSet.getString("summary");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int genreId = resultSet.getInt("genre_id");

            System.out.println("Book ID      : " + id);
            System.out.println("Title        : " + title);
            System.out.println("Author       : " + author);
            System.out.println("Year         : " + year);
            System.out.println("Price        : PHP " + price);
            System.out.println("Quantity     : " + quantity + " pcs");
            System.out.println("Genre        : " + getGenreName(genreId));
            System.out.println("Summary      : " + summary);
            System.out.println("==========================================");
        }
    } catch (Exception e) {
            e.printStackTrace();
    }
    }//for showAllBooksFirst


    // Method to create a new book
    // Parameters: title, author, publishedYear, summary, price, genre_id
    public static void createBooks(String title, String author, int publishedYear, String summary, double price, int quantity, int genre_id) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO books (title, author, published_year, summary, price, quantity, genre_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ptsmt = conn.prepareStatement(sql);
            ptsmt.setString(1,title);
            ptsmt.setString(2, author);
            ptsmt.setInt(3,publishedYear);
            ptsmt.setString(4, summary);
            ptsmt.setDouble(5, price);
            ptsmt.setInt(6, quantity);
            ptsmt.setInt(7, genre_id);

            int rowsInserted = ptsmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("===========================");
                System.out.println("Book inserted successfully!");
                System.out.println("===========================");
            } else {
                System.out.println("======================");
                System.out.println("Failed to insert book!");
                System.out.println("======================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    // Method to view all books
    // This method retrieves all books from the database and prints their details
    public static void viewAllbooks () {
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            System.out.println("\n========== ALL BOOKS ==========");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("published_year");
            String summary = resultSet.getString("summary");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int genreId = resultSet.getInt("genre_id");

            System.out.println("Book ID      : " + id);
            System.out.println("Title        : " + title);
            System.out.println("Author       : " + author);
            System.out.println("Year         : " + year);
            System.out.println("Price        : PHP " + price);
            System.out.println("Quantity     : " + quantity + " pcs");
            System.out.println("Genre        : " + getGenreName(genreId));
            System.out.println("Summary      : " + summary);
            System.out.println("------------------------------------");
        }
        System.out.print("Press [0] to return to the main menu: ");
    
            String choice = scanner.nextLine();
            try {
                if (choice.matches("\\d+")) { // Only digits
                int back = Integer.parseInt(choice);

                    if (back == 0) {
                        BookMain.main(null); 
                    } else {
                        System.out.println("Invalid choice, try Again.");
                        viewAllbooks();
                    }
                    
                } else {
                    // Not a number
                    System.out.println("Invalid choice. Returning to main menu.");
                    viewAllbooks();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Returning to main menu.");
                viewAllbooks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // Method to displayBooksByGenre book details
    public static void displayBooksByGenre(int genreId) {
    String genreName = getGenreName(genreId);  // You already have this

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT * FROM books WHERE genre_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, genreId);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("\n========== " + genreName.toUpperCase() + " BOOKS ==========");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            int year = rs.getInt("published_year");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String summary = rs.getString("summary");

            System.out.println("Book ID : " + id);
            System.out.println("Title   : " + title);
            System.out.println("Author  : " + author);
            System.out.println("Year    : " + year);
            System.out.println("Price   : PHP " + price);
            System.out.println("Quantity: " + quantity + " pcs");
            System.out.println("Summary : " + summary);
            System.out.println("------------------------");
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Back [0]: ");
        String input = scanner.nextLine();
        try {
            if (input.matches("\\d+")) { // Only digits
            int choice = Integer.parseInt(input);

                if (choice == 0) {
                    BookMain.showBooksByGenre(); 
                } else {
                System.out.println("========================");
                System.out.println("Invalid choice,try again");
                BookMain.showBooksByGenre();
            }

            } else {
                // Not a number
                System.out.println("========================");
                System.out.println("Invalid choice,try again");
                BookMain.showBooksByGenre();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Returning to main menu.");
            BookMain.main(null);
        }
        scanner.close();
    } catch (Exception e) {
        e.printStackTrace();
       }
    }

    // Method to update book details
   public static boolean bookExists(int id) {
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String checkSql = "SELECT COUNT(*) FROM books WHERE id = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setInt(1, id);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public static boolean updateBooks(int id, String newTitle, String newAuthor, int newPublishedYear, String newSummary, double newPrice, int newQuantity, int newGenre) {
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String sql = "UPDATE books SET title = ?, author = ?, published_year = ?, summary = ?, price = ?, quantity = ?, genre_id = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newTitle);
        pstmt.setString(2, newAuthor);
        pstmt.setInt(3, newPublishedYear);
        pstmt.setString(4, newSummary);
        pstmt.setDouble(5, newPrice);
        pstmt.setInt(6, newQuantity);
        pstmt.setInt(7, newGenre);
        pstmt.setInt(8, id);

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

        

    public static void buyBook(int bookId, int quantity) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                int availableQuantity = rs.getInt("quantity");

                if (quantity > availableQuantity) {
                    System.out.println("============================================");
                    System.out.println("Not enough stock available.");
                    System.out.println("Available quantity: " + availableQuantity);
                    System.out.println("returning to main menu...");
                    BookMain.main(null);
                    return;
                }

                double totalCost = price * quantity;
                if (totalCost > accountBalance) {
                    System.out.println("============================================");
                    System.out.println("Not enough balance to complete the purchase.");
                    System.out.println("Your current balance: PHP " + accountBalance);
                    System.out.println("returning to main menu...");
                    BookMain.main(null);
                }

                if(accountBalance > totalCost) {
                    
                    // Update the book quantity and account balance
                    String updateSql = "UPDATE books SET quantity = quantity - ? WHERE id = ?";
                    PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                    updatePstmt.setInt(1, quantity);
                    updatePstmt.setInt(2, bookId);
                    updatePstmt.executeUpdate();

                    accountBalance -= totalCost;
                    System.out.println("============================================");
                    System.out.println("Purchase successful!");
                    System.out.println("Book ID: " + bookId);
                    System.out.println("Book Title: " + rs.getString("title"));
                    System.out.println("Quantity left: " + (availableQuantity - quantity) + " pcs");
                    System.out.println("You bought " + quantity + "x " + "of " + rs.getString("title"));
                    System.out.println("Total cost: PHP " + totalCost);
                    System.out.println("Change: PHP " + accountBalance);
                    BookMain.main(null);
                }
            } else {
                System.out.println("Book not found.");
                System.out.println("Returning to main menu...");
                BookMain.main(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //for buyBook


    public static void searchBook(String title) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){

                do {
                    System.out.println("============================================");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("Author: " + rs.getString("author"));
                    System.out.println("Published Year: " + rs.getInt("published_year"));
                    System.out.println("Summary: " + rs.getString("summary"));
                    System.out.println("Price: PHP " + rs.getDouble("price"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Genre: " + getGenreName(rs.getInt("genre_id")));
                } while (rs.next());
                System.out.println("============================================");
                System.out.println("Book consisting '" + title + "' has been found.");
                System.out.println("Returning to main menu...");
                BookMain.main(null);
            } else {
                System.out.println("No book found with: '" + title + "'");
                System.out.println("Returning to main menu...");
                BookMain.main(null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //for searchBook

    public static void removeBook(int bookId) {
            try (Connection connec = DriverManager.getConnection(url, username, password)) {
            String sqlDrop = "DELETE FROM books WHERE id = ?";
            PreparedStatement pstmtt = connec.prepareStatement(sqlDrop);
            pstmtt.setInt(1, bookId);
            int rowsAffected = pstmtt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("============================================");
                System.out.println("Book with ID " + bookId + " has been removed successfully.");
                System.out.println("Returning to main menu...");
                BookMain.main(null);
            } else {
                System.out.println("No book found with ID " + bookId + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void removeShowingAllBooks(){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("========== ALL BOOKS ==========");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("published_year");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String summary = rs.getString("summary");

                System.out.println("Book ID : " + id);
                System.out.println("Title   : " + title);
                System.out.println("Author  : " + author);
                System.out.println("Year    : " + year);
                System.out.println("Price   : PHP " + price);
                System.out.println("Quantity: " + quantity + " pcs");
                System.out.println("Genre   : " + getGenreName(rs.getInt("genre_id")));
                System.out.println("Summary : " + summary);
                System.out.println("------------------------");
            }

            BookMain.removeABook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //for removeBook


    // Method to get genre name based on genre ID
    // This method returns the genre name as a string based on the genre ID
    public static String getGenreName(int genreId) {
            return switch (genreId) {
            case 1 -> "Horror";
            case 2 -> "Fantasy";
            case 3 -> "Romantic";
            case 4 -> "Fiction";
            case 5 -> "Mystery";
            case 6 -> "Thriller";
            case 7 -> "Sci-Fi";
            default -> "Unknown";
        };
    }

}//for public class bookmanager

