package com.pavanboyapati.libraryAPI.libraryExceptions;

public class BookAddException extends RuntimeException{

    private String errorCd;

    public String getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }

    public String getErrorTxt() {
        return errorTxt;
    }

    public void setErrorTxt(String errorTxt) {
        this.errorTxt = errorTxt;
    }

    private String errorTxt;

    public BookAddException(String errorCd, String errorTxt){
        super();
        setErrorCd(errorCd);
        setErrorCd(errorTxt);
    }
}
