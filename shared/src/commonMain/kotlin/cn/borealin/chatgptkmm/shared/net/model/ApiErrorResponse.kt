package cn.borealin.chatgptkmm.shared.net.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("message")
    override val message: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("param")
    val param: String? = null,
    @SerialName("code")
    val code: String? = null
) : Exception()

@Serializable
data class ApiErrorResponse(
    @SerialName("error")
    val error: ApiError
)