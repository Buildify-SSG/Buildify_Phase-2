package com.wareflow.buildify.util;

import java.util.ArrayList;
import java.util.List;

public class ListWrapper<T> {
    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
