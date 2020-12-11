package com.example.home_component.domain.interactor

interface UseCase<T> {

    fun execute():T
}