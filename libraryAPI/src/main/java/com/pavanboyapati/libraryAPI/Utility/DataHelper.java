package com.pavanboyapati.libraryAPI.Utility;

import com.pavanboyapati.libraryAPI.domain.Library;

import java.time.LocalDate;
public class DataHelper {

    static {
        Library book3 = new Library(12341, "Computer Networks: Principles, Technologies and Protocols for Network Design", 470869828, "Natalia Olifer, Victor Olifer", LocalDate.parse("2005-12-30"));
        Library book2 = new Library(12342, "Fuzzy Logic: The Revolutionary Computer Technology That Is Changing Our World", 671875353, "McNeill, Paul Freiberger ", LocalDate.parse("1994-04-15"));
        Library book1 = new Library(12343, "Wired For War: The Robotics Revolution And Conflict In The 21st Century", 1594201986, "P.W. Singer ", LocalDate.parse("2009-02-09"));

    }

}
