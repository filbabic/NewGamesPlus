package com.babic.filip.coreui.scope

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ScopeRetainerFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ScopeRetainer() as T
}