package com.project.bookstore.web.book.dto;

import com.project.bookstore.domain.books.Books;
import lombok.Getter;

import java.util.Date;

@Getter
public class BookListDto {
    private String isbn;
    private String bookName;
    private String bookAut;
    private String bookTrans;
    private Date bookDate;
    private String bookCov;
    private int bookPri;
    private String bookGen;
    private String bookDet;

    public BookListDto(Books entity) {
        this.isbn = entity.getIsbn();
        this.bookName = entity.getBookName();
        this.bookAut = entity.getBookAut();
        this.bookTrans = entity.getBookTrans();
        this.bookDate = entity.getBookDate();
        this.bookCov = entity.getBookCov();
        this.bookPri = entity.getBookPri();
        this.bookGen = entity.getBookGen();
        this.bookDet = entity.getBookDet();
    }
}
