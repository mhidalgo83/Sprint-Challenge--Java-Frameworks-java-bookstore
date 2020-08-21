package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    SectionService sectionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void findAll() {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void findBookById() {
        assertEquals("Flatterland", bookService.findBookById(26).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete() {
        bookService.delete(26);
        assertEquals("Flatterlands", bookService.findBookById(26));
    }

    @Test
    public void save() {
        Section fiction = new Section("Fiction");
        sectionService.save(fiction);
        fiction.setSectionid(21);
        String bookName = "Calling Houston Home";
        Book b6 = new Book(bookName, "1885171384134", 2001, fiction);
        bookService.save(b6);
        Book addBook = bookService.save(b6);
        assertNotNull(addBook);
        assertEquals(bookName, addBook.getTitle());
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
        bookService.deleteAll();
        assertEquals(0, bookService.findAll().size());
    }
}