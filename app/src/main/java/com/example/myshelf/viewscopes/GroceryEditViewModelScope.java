package com.example.myshelf.viewscopes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class GroceryEditViewModelScope implements ViewModelStoreOwner {
    private final ViewModelStore viewModelStore = new ViewModelStore();
    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }

    public void clear() {
        viewModelStore.clear();
    }

}
