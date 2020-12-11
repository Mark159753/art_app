package com.example.framework_datasourcess.mapper

interface IMapper<FROM, TO> {

    fun map(from: FROM):TO
}