package com.babic.filip.coreui.scope

import android.arch.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ScopeRetainer : ViewModel(), KoinComponent {

    private lateinit var currentScopeId: String

    fun initializeScope(scopeId: String) {
        this.currentScopeId = scopeId
        getKoin().getOrCreateScope(currentScopeId, named(currentScopeId))
    }

    val scope: Scope
        get() = getKoin().getScope(currentScopeId)

    override fun onCleared() {
        scope.close()
        super.onCleared()
    }
}