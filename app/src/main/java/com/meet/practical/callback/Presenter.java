package com.meet.practical.callback;

public interface Presenter<T> {

    void createView(T view);

    void destroyView();
}
