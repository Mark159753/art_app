package com.example.core.exception

import java.io.IOException

class NoConnectivityException:IOException() {

    override val message: String?
        get() = "No Internet Connection"
}