package com.jarroyo.sharedcodeclient.domain.base

import com.jarroyo.sharedcodeclient.domain.base.BaseRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class BaseUseCaseFlow<R : BaseRequest, T> {

    protected var mRequest: R? = null

    suspend fun execute(request: R? = null): Flow<Response<T>> {
        mRequest = request
        return run()
    }

    abstract suspend fun run(): Flow<Response<T>>
}

