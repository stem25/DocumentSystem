package controller;

import dao.PersonDao;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("ecm")
public class MyApplication extends Application {
    public MyApplication(){
        PersonDao personDao = new PersonDao();
        personDao.loadFromXmlToDB();
    }
}
