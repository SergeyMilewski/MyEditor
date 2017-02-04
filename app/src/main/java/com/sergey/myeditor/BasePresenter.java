package com.sergey.myeditor;

/**
 * Created by sergey on 01.02.17.
 */

public abstract class BasePresenter<T extends UI> implements Presenter<T> {

    protected T ui;

    @Override
    public void attacheUI(T ui) {
        this.ui = ui;
    }

    @Override
    public void detachUI() {
        ui = null;
    }
}
