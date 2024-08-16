package com.fabulousseries.fabulousyaml;

import java.util.ArrayList;
import java.util.List;

public interface IYaml {

    List<String> getKeys(String parentKey);
    int getInt(String value);
    boolean getBoolean(String value);
    String getString(String value);
    ArrayList getList(String value);
    void setInt(String key, int value);
    void setBoolean(String key, boolean value);
    void setString(String key, String value);
    void setList(String key, ArrayList value);

}