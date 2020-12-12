package com.jarroyo.sharedcodeclient.domain.base

interface BaseRequest {
    fun validate(): Boolean
}
