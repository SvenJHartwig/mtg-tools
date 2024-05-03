package de.tholor.web.shared

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.util.Supplier
import kotlin.reflect.full.companionObject

@Suppress("unused")
class Logging(val log: Logger) : Logger by log {
    inline fun debug(crossinline supplier: () -> String) {
        log.debug(Supplier { supplier.invoke() })
    }

    inline fun debug(t: Throwable, crossinline supplier: () -> String) {
        log.debug(Supplier { supplier.invoke() }, t)
    }

    inline fun info(crossinline supplier: () -> String) {
        log.info(Supplier { supplier.invoke() })
    }

    inline fun info(t: Throwable, crossinline supplier: () -> String) {
        log.info(Supplier { supplier.invoke() }, t)
    }

    inline fun warn(crossinline supplier: () -> String) {
        log.warn(Supplier { supplier.invoke() })
    }

    inline fun warn(t: Throwable, crossinline supplier: () -> String) {
        log.warn(Supplier { supplier.invoke() }, t)
    }

    inline fun error(crossinline supplier: () -> String) {
        log.error(Supplier { supplier.invoke() })
    }

    inline fun error(t: Throwable, crossinline supplier: () -> String) {
        log.error(Supplier { supplier.invoke() }, t)
    }
}

@Suppress("unused")
inline fun <reified T : Any> T.logger(): Lazy<Logging> =
    lazy { Logging(LogManager.getLogger(unwrapCompanionClass(T::class.java))) }

fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return if (ofClass.enclosingClass != null && ofClass.enclosingClass.kotlin.companionObject?.java == ofClass) {
        ofClass.enclosingClass
    } else {
        ofClass
    }
}