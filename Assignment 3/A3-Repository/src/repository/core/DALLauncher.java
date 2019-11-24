/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.ArrayList;

/**
 *
 * @author Airi
 */
public class DALLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BookRepositoryException {
        Session session = null;
        session = new Session();
        session.login("Jasmine", "test123");

        BookRepository b1 = BookRepository.getInstance();
        System.out.println();
        System.out.println("DROP TABLE");
        System.out.println();
        b1.dropBookTable();
        System.out.println("CREATE TABLE");
        System.out.println();

        b1.createBookTable(session);
        ArrayList<Book> books;

        CoverImage cover1 = new CoverImage();
        cover1.setMimeType("image/jpeg");
        cover1.setImagePath("./endofownership_photo_final.jpeg");

        Author author = new Author("Aurelius", "Marcus");
        Book book1 = new Book("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor", "0140449337", author,
                "Penguin Classic", "England", cover1);

        CoverImage cover2 = new CoverImage();
        cover2.setMimeType("image/png");
        cover2.setImagePath("./panda.png");

        Author author2 = new Author("Epictetus", "Unknown");
        Book book2 = new Book("Discourses, Fragments, Handbook", "About things that are within our power and those that are not.", "0199595186",
                author2, "Oxford University Press", "England", cover2);

        Author author3 = new Author("Kishimi", "Ichiro");
        CoverImage cover3 = new CoverImage();
        cover3.setMimeType("image/png");
        cover3.setImagePath("./redpanda.png");
        Book book3 = new Book("Courage to be Happy", "The Courage to be Happy is a profound insight into the way we should live our lives that has already sold more than one million copies in Japan.", "1911630210", author3, "Allen & Unwin", "London, England", cover3);

        System.out.println("Adding book 1 to database");
        b1.addNewBook(session, book1);
        System.out.println("Adding book 2 to database");
        b1.addNewBook(session, book2);
        System.out.println("Adding book 3 to database");
        b1.addNewBook(session, book3);

        System.out.println();
        System.out.println("Book ArrayList: ");
        books = b1.listAllBooks(session);
        for (Book book : books) {
            System.out.println(book);
        }

    }
}
