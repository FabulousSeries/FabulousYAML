package com.fabulousseries.fabulousyaml;

import java.util.ArrayList;

public interface IYaml {

    int getInt(String value);
    boolean getBoolean(String value);
    String getString(String value);
    ArrayList getList(String value);
    void setInt(String key, int value);
    void setBoolean(String key, boolean value);
    void setString(String key, String value);
    void setList(String key, ArrayList value);
}