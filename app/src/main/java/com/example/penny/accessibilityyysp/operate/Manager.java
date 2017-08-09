package com.example.penny.accessibilityyysp.operate;

/**
 * Created on 2017/8/7 0007.
 * by penny
 */

public class Manager implements Strategy {
    private Strategy mDelegate;

    public Manager(Strategy pDelegate){
        this.mDelegate = pDelegate;
    }

    @Override
    public void enterDifferentPage() {
        mDelegate.enterDifferentPage();
    }

    @Override
    public void enterDetailPage() {
        mDelegate.enterDetailPage();
    }

    @Override
    public void collectionData() {
        mDelegate.collectionData();
    }

    @Override
    public void requestUploadData() {
        mDelegate.requestUploadData();
    }
}
