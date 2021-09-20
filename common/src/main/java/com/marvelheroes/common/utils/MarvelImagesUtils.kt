package com.marvelheroes.common.utils

fun getLandscapeImageURL(path: String?, ext: String?): String {
    return if (path == null || ext == null) {
        ""
    } else {
        "$path/landscape_xlarge.$ext"
    }
}
