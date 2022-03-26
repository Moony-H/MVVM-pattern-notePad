package com.moony.mvvm_pattern_notepad.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moony.mvvm_pattern_notepad.R
import com.moony.mvvm_pattern_notepad.Tag.FragmentTags
import java.lang.IllegalArgumentException

class NavigationViewModel:ViewModel() {
    private val _currentPageTag=MutableLiveData(FragmentTags.PAGE_SCHEDULE)
    val currentPageTag: LiveData<FragmentTags>
        get()=_currentPageTag


    fun setCurrentPage(menuItemId:Int):Boolean{
        val pageTag=getPageTag(menuItemId)
        changeCurrentPage(pageTag)
        return true

    }
    private fun getPageTag(page_num:Int):FragmentTags{
        return when(page_num){
            R.id.item_page_schedule-> FragmentTags.PAGE_SCHEDULE
            R.id.item_page_subject-> FragmentTags.PAGE_SUBJECT
            R.id.item_page_record-> FragmentTags.PAGE_RECORD
            else -> throw IllegalArgumentException("bottom navi view not found: fragment tag")
        }

    }

    private fun changeCurrentPage(pageType: FragmentTags) {
        if (_currentPageTag.value == pageType)
            return

        _currentPageTag.value = pageType
    }

}