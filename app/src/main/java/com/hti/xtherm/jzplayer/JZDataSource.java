package com.hti.xtherm.jzplayer;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class JZDataSource {
    public static final String URL_KEY_DEFAULT = "URL_KEY_DEFAULT";
    public int currentUrlIndex;
    public HashMap<String, String> headerMap;
    public boolean looping;
    public Object[] objects;
    public String title;
    public LinkedHashMap urlsMap;

    public JZDataSource(String str) {
        this.urlsMap = new LinkedHashMap();
        this.title = "";
        this.headerMap = new HashMap<>();
        this.looping = false;
        this.urlsMap.put(URL_KEY_DEFAULT, str);
        this.currentUrlIndex = 0;
    }

    public JZDataSource(String str, String str2) {
        this.urlsMap = new LinkedHashMap();
        this.title = "";
        this.headerMap = new HashMap<>();
        this.looping = false;
        this.urlsMap.put(URL_KEY_DEFAULT, str);
        this.title = str2;
        this.currentUrlIndex = 0;
    }

    public JZDataSource(Object obj) {
        this.urlsMap = new LinkedHashMap();
        this.title = "";
        this.headerMap = new HashMap<>();
        this.looping = false;
        this.urlsMap.put(URL_KEY_DEFAULT, obj);
        this.currentUrlIndex = 0;
    }

    public JZDataSource(LinkedHashMap linkedHashMap) {
        this.urlsMap = new LinkedHashMap();
        this.title = "";
        this.headerMap = new HashMap<>();
        this.looping = false;
        this.urlsMap.clear();
        this.urlsMap.putAll(linkedHashMap);
        this.currentUrlIndex = 0;
    }

    public JZDataSource(LinkedHashMap linkedHashMap, String str) {
        this.urlsMap = new LinkedHashMap();
        this.title = "";
        this.headerMap = new HashMap<>();
        this.looping = false;
        this.urlsMap.clear();
        this.urlsMap.putAll(linkedHashMap);
        this.title = str;
        this.currentUrlIndex = 0;
    }

    public Object getCurrentUrl() {
        return getValueFromLinkedMap(this.currentUrlIndex);
    }

    public Object getCurrentKey() {
        return getKeyFromDataSource(this.currentUrlIndex);
    }

    public String getKeyFromDataSource(int i) {
        int i2 = 0;
        for (Object obj : this.urlsMap.keySet()) {
            if (i2 == i) {
                return obj.toString();
            }
            i2++;
        }
        return null;
    }

    public Object getValueFromLinkedMap(int i) {
        int i2 = 0;
        for (Object obj : this.urlsMap.keySet()) {
            if (i2 == i) {
                return this.urlsMap.get(obj);
            }
            i2++;
        }
        return null;
    }

    public boolean containsTheUrl(Object obj) {
        if (obj != null) {
            return this.urlsMap.containsValue(obj);
        }
        return false;
    }

    public JZDataSource cloneMe() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.urlsMap);
        return new JZDataSource(linkedHashMap, this.title);
    }
}
