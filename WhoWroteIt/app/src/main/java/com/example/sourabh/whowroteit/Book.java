package com.example.sourabh.whowroteit;

public class Book {
    String bookTitle;
    String bookAuthor;
    int bookImgRes;

    public Book(String bookTitle, String bookAuthor, int bookImgRes) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookImgRes = bookImgRes;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookImgRes() {
        return bookImgRes;
    }

    public void setBookImgRes(int bookImgRes) {
        this.bookImgRes = bookImgRes;
    }
}
