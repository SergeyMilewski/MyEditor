package com.sergey.myeditor;

/**
 * Created by sergey on 01.02.17.
 */

public interface Presenter<T extends UI> {

    void attacheUI(T ui);

    void detachUI();
}