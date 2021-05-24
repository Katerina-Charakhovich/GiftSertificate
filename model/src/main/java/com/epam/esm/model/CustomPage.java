package com.epam.esm.model;

import lombok.Data;

@Data
public class CustomPage {
    private static final int MIN_PAGE_NUMBER = 0;
    private static final int MAX_PAGE_SIZE = 5;
    private static final int MIN_PAGE_SIZE = 1;
    private int pageNumber;
    private int pageSize;
    private int pageTotal;

    public CustomPage(int pageNumber,int  pageSize,Long pageTotal) {
        this.pageSize=(pageTotal>=MAX_PAGE_SIZE||pageSize>=MAX_PAGE_SIZE)?MAX_PAGE_SIZE:
                (pageSize<MIN_PAGE_SIZE)?MIN_PAGE_SIZE:pageSize;
        int countPage=pageTotal.intValue()/pageSize;
        this.pageNumber = (pageNumber-1<MIN_PAGE_NUMBER)?MIN_PAGE_NUMBER:(pageNumber-1>=countPage)?countPage:pageNumber-1;
        this.pageTotal=pageTotal.intValue();
    }
}
