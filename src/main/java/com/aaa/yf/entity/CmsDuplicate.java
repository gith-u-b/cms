package com.aaa.yf.entity;


/**
 * CmsDuplicate entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsDuplicate  implements java.io.Serializable {


    // Fields    

     private Integer duplicateId;
     private String duplicateUrl;
     private String duplicateTable;
     private String duplicateUserName;
     private String duplicateDatetime;


    // Constructors

    /** default constructor */
    public CmsDuplicate() {
    }

    
    /** full constructor */
    public CmsDuplicate(String duplicateUrl, String duplicateTable, String duplicateUserName, String duplicateDatetime) {
        this.duplicateUrl = duplicateUrl;
        this.duplicateTable = duplicateTable;
        this.duplicateUserName = duplicateUserName;
        this.duplicateDatetime = duplicateDatetime;
    }

   
    // Property accessors

    public Integer getDuplicateId() {
        return this.duplicateId;
    }
    
    public void setDuplicateId(Integer duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getDuplicateUrl() {
        return this.duplicateUrl;
    }
    
    public void setDuplicateUrl(String duplicateUrl) {
        this.duplicateUrl = duplicateUrl;
    }

    public String getDuplicateTable() {
        return this.duplicateTable;
    }
    
    public void setDuplicateTable(String duplicateTable) {
        this.duplicateTable = duplicateTable;
    }

    public String getDuplicateUserName() {
        return this.duplicateUserName;
    }
    
    public void setDuplicateUserName(String duplicateUserName) {
        this.duplicateUserName = duplicateUserName;
    }

    public String getDuplicateDatetime() {
        return this.duplicateDatetime;
    }
    
    public void setDuplicateDatetime(String duplicateDatetime) {
        this.duplicateDatetime = duplicateDatetime;
    }
   








}