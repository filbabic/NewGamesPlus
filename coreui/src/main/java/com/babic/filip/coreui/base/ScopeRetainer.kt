package com.babic.filip.coreui.base

import android.arch.lifecycle.ViewModel
import org.koin.core.KoinContext

class ScopeRetainer(private val koinContext: KoinContext) : ViewModel() {

    private lateinit var currentScope: String

    fun initializeScope(scopeId: String) {
        if (this::currentScope.isInitialized && currentScope == scopeId) {
            //we've already bound the scope
            throw IllegalStateException("The scope: '$scopeId' is already bound")
        }

        this.currentScope = scopeId
        koinContext.createScope(currentScope)
    }

    override fun onCleared() {
        koinContext.getScope(currentScope).close()
        super.onCleared()
    }
}