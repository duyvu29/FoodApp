package com.example.foodapp.other;

public class StoreModel {
    private int imageStore;
    private String nameHintStore;

    public StoreModel(int imageStore, String nameHintStore) {
        this.imageStore = imageStore;
        this.nameHintStore = nameHintStore;
    }

    public int getImageStore() {
        return imageStore;
    }

    public void setImageStore(int imageStore) {
        this.imageStore = imageStore;
    }

    public String getNameHintStore() {
        return nameHintStore;
    }

    public void setNameHintStore(String nameHintStore) {
        this.nameHintStore = nameHintStore;
    }
}
