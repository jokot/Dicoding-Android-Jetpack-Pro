package com.jokot.app.moviecatalogue.utils

import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.any
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[any()]).then{ invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}