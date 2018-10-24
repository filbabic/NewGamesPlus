package com.babic.filip.coreui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.koin.core.KoinContext

class ScopeRetainerFactory(private val koinContext: KoinContext) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ScopeRetainer(koinContext) as T
}