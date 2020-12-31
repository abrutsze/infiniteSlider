package com.example.domian.di

import com.example.domian.interactors.GetDataInteractor
import com.example.domian.usecase.GetDataUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory<GetDataInteractor> { GetDataUseCase(get(),get()) }
}
