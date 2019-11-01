package com.babic.filip.coreui.scope

import android.arch.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.qualifier.named

class ScopeRetainer : ViewModel(), KoinComponent {

    private lateinit var currentScope: String

    fun initializeScope(scopeId: String) {
        this.currentScope = scopeId
        getKoin().getOrCreateScope(currentScope, named(currentScope))
    }

    fun scope() = getKoin().getScope(currentScope)

    override fun onCleared() {
        this.getKoin().getScope(currentScope).close()
        super.onCleared()
    }
}