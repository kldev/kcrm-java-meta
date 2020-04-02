package dev.kcrm.web.constant;

import java.util.ArrayList;
import java.util.Arrays;

public class UserRoles {
    public static final String Root = "root";
    public static final String User = "user";
    public static final String Admin = "admin";

    public static ArrayList<String> Roles = new ArrayList<>(Arrays.asList(Root, User, Admin));
}
