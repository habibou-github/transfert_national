package com.TransfertNational.demo.Services;

import com.TransfertNational.demo.Entities.Admin;

public interface AdminService {
    void signUp(Admin admin) throws Exception;
    Boolean logIn(String adminName, String password) throws Exception;

}
