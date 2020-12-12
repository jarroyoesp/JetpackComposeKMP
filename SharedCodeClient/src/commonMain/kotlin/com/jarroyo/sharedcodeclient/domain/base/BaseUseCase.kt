package com.jarroyo.sharedcodeclient.domain.base



abstract class BaseUseCase<R : BaseRequest, T> {

    protected var request: R? = null

    suspend fun execute(request: R? = null): Response<T> {
        this.request = request

        val validated = request?.validate() ?: true
        if (validated) return run()
        return Response.Error(IllegalArgumentException())
    }

    abstract suspend fun run(): Response<T>
}
