package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import com.sun.net.httpserver.HttpsServer
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.method.MethodValidationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundHandler(
        notFoundException: NotFoundException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            error = HttpStatus.NOT_FOUND.name,
            status = HttpStatus.NOT_FOUND.value(),
            message =  notFoundException.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerHandler(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequestHandler(
        methodArgumentNotValidException: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {
        val errosValidation = HashMap<String, String?>()

        methodArgumentNotValidException.bindingResult.fieldErrors.forEach {
            error ->
            errosValidation[error.field] = error.defaultMessage
        }
        return ErrorView(
            error = HttpStatus.BAD_REQUEST.name,
            status = HttpStatus.BAD_REQUEST.value(),
            message = errosValidation.toString(),
            path = request.servletPath
        )
    }
}